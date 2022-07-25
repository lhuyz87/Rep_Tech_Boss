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
            
            
            
            
            def json = '''[
   {
      "line":1,
      "elements":[
         {
            "line":39,
            "name":"3-Validar que el sistema permita poder realizar busquedas no existentes",
            "description":"",
            "id":"productos-y-soluciones-techboss;3-validar-que-el-sistema-permita-poder-realizar-busquedas-no-existentes;;2",
            "type":"scenario",
            "keyword":"Scenario Outline",
            "steps":[
               {
                  "result":{
                     "duration":8132717300,
                     "status":"passed"
                  },
                  "line":32,
                  "name":"que accedo a la p\u00e1gina principal de Tech Boss",
                  "match":{
                     "location":"ProductosDefinition.que_accedo_a_la_p\u00e1gina_principal_de_Tech_Boss()"
                  },
                  "keyword":"Given "
               },
               {
                  "result":{
                     "duration":2235827700,
                     "status":"passed"
                  },
                  "line":33,
                  "name":"selecciono opcion buscar",
                  "match":{
                     "location":"ProductosDefinition.selecciono_opcion_buscar()"
                  },
                  "keyword":"And "
               },
               {
                  "result":{
                     "duration":5651431300,
                     "status":"passed"
                  },
                  "line":34,
                  "name":"ingreso el valor a buscar asasasasas",
                  "match":{
                     "arguments":[
                        {
                           "val":"asasasasas",
                           "offset":27
                        }
                     ],
                     "location":"ProductosDefinition.ingreso_el_valor_a_buscar(String)"
                  },
                  "keyword":"And "
               },
               {
                  "result":{
                     "duration":214366900,
                     "status":"passed"
                  },
                  "line":35,
                  "name":"valido que se muestre mensaje de informativo No Results Found",
                  "match":{
                     "arguments":[
                        {
                           "val":"No Results Found",
                           "offset":46
                        }
                     ],
                     "location":"ProductosDefinition.valido_que_se_muestre_mensaje_de_informativo(String)"
                  },
                  "keyword":"Then "
               }
            ],
            "tags":[
               {
                  "name":"@BusquedaInexistente"
               },
               {
                  "name":"@Regresion"
               }
            ]
         }
      ],
      "name":"Productos y Soluciones TechBoss",
      "description":"",
      "id":"productos-y-soluciones-techboss",
      "keyword":"Feature",
      "uri":"src/test/resources/features/1-ProductoSolucionesTechBoss.feature",
      "tags":[
         
      ]
   }
]'''
            
	def parsedJson = new groovy.json.JsonSlurper().parseText(json)

			def ids = []
			parsedJson.each { item ->
   			item.elements.each { element ->
   			element.steps.each{ step ->
   			step.result.each{ result ->
      		if (result.status == 'passed') {
        	ids << item.id
      			}
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
