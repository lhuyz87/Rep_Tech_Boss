Feature: Productos y Soluciones TechBoss

  @DetalleProductoSoluciones @Regresion
  Scenario Outline: 1-Validar que se pueda ingresar la detalle de productos y soluciones
    Given que accedo a la página principal de Tech Boss
    And seleccionar los distintos produtos "<producto>"
    Then validar que se pueda navegar en las distintas soluciones "<soluciones>"

    Examples: 
      | producto | soluciones          |
      | Seif     | Minuta Virtual      |
 #     | Seif     | Tickets de Servicio |
 #     | Seif     | Comunicaciones      |
 #     | Seif     | Escoltas            |

  @CamposAtencionAlcliente @Regresion
  Scenario Outline: 2-Validar los campos obligarios en el formulario de atención al cliente
    Given que accedo a la página principal de Tech Boss
    And selecciono opción contacto
    And llenar el formulario con información faltante: "<Nombre>","<Mail>","<Mensaje>"
    Then al seleccionar opción enviar no debe permitir el envio

    Examples: 
      | Nombre | Mail              | Mensaje           |
      | Prueba | Prueba@prueba.com |                   |
      | Prueba |                   | Mensaje de prueba |
      |        | Prueba@prueba.com | Mensaje de prueba |
      |        |                   |                   |

  @BusquedaInexistente @Regresion
  Scenario Outline: 3-Validar que el sistema permita poder realizar busquedas no existentes
    Given que accedo a la página principal de Tech Boss
    And selecciono opcion buscar
    And ingreso el valor a buscar "<valor>"
    Then valido que se muestre mensaje de informativo "No Results Found"

    Examples: 
      | valor      |
      | asasasasas |
