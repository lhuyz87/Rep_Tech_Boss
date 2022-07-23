import java.text.SimpleDateFormat
import groovy.json.JsonSlurper;

def defDateFormat = new SimpleDateFormat("yyyyMMddHHmm")
def defDate = new Date()
def defTimestamp = defDateFormat.format(defDate).toString()

def jsonSlurper = new JsonSlurper()

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
            File fl = new File('${WORKSPACE}/target/cucumber/counter.json')
            echo 'Se extrae reporte'
          } catch (ex) {
            echo 'Archivo no existe'
            error('Failed')
          }
        }
      }
    }

  }

}
