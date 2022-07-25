import java.text.SimpleDateFormat
import groovy.json.JsonSlurper
import java.io.File 
import groovy.json.JsonOutput

def defDateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def defDate = new Date()
def defTimestamp = defDateFormat.format(defDate).toString()

//def jsonSlurper = new JsonSlurper()

def test() {
  def config = getConfig()
  echo 'Variable...'
  echo "${config}"
  echo "${config.class}"
}

//def getConfig() {
//  new JsonSlurper().parseText(readFile("${WORKSPACE}/target/cucumber/counter.json"))
//}

def getConfig() {
   def pjson = new groovy.json.JsonSlurper().parse(new File("${WORKSPACE}/target/cucumber/counter.json"))
   assert pjson instanceof Map
   pjson
}

pipeline {
  agent any
  // agent {label 'Slave_1'}
  tools {
    maven 'M3'
    jdk 'jdk8.221'
  }

  stages {

    stage('Build') {
      steps {
        bat("mvn clean install -DskipTests")
        bat("mvn clean verify")
      }
    }

    stage('Ejecutar Pruebas') {
      steps {
        script {
          try {

            bat("mvn test -Dcucumber.options=\"src/test/resources/features/ --tags \'${ESCENARIO}\' --glue tech.test.definition --plugin junit:target/cucumber/result.xml --plugin json:target/cucumber/counter.json\"")
            bat("mvn serenity:aggregate")
            echo 'Ejecucion de pruebas sin errores...'
          } catch (ex) {
            echo 'Finalizo ejecucion con fallos...'
            error('Failed')
          }
        }
      }
    }

    stage('Reporte') {
      steps {
        script {
          try {
            //bat ("echo ${WORKSPACE}")
            bat("echo ${defTimestamp}")
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "${WORKSPACE}/target/site/serenity", reportFiles: 'index.html', reportName: 'Evidencias de Prueba', reportTitles: 'Reporte de Pruebas'])
            echo 'Reporte realizado con exito'
          } catch (ex) {
            echo 'Reporte realizado con Fallos'
            error('Failed')
          }
        }
      }
    }

    stage('Extract_Result') {
      steps {
        script {
          try {
            bat("echo ${defTimestamp}")
            bat ("echo ${WORKSPACE}")
            File fl = new File("${WORKSPACE}/target/cucumber/counter.json")
            println("${WORKSPACE}/target/cucumber/counter.json")
            def jsonSlurper = new JsonSlurper()
            def obj = jsonSlurper.parseText(fl.text)
            println("Archivo: ${obj}")
            
            
          	def json_str = JsonOutput.toJson(obj)
			println("Archivo: ${json_str}")
			//json_str.each { println it }
            echo 'Se extrae reporte'

			def ids = []
			json_str.each { item ->
   			item.elements.each { elements ->
   			item.elements.steps.result.each{ result
      		if (result.status == 'passed') {
        	ids << item.id
      			}
   		 	 }
   		  }
		}
		
		println ids

			//def valor = json_str.line.elements[0].line
			//println("Valor: ${valor}")
			//println(json_str['status'])

          } catch (Exception e) {
         	 println("Exception: ${e}")
            echo 'Archivo no existe'
            error('Failed')
          }
        }
      }
    }

  }

}
