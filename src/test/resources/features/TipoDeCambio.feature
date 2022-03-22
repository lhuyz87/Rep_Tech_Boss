@regresion
Feature: Realizar tipo de cambio

  Scenario Outline: Validar que se muestre la lista de beneficios y requisitos accediendo con tarjeta
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuenta activa en soles y/o dólares
    When selecciono opcíón 'Cambia tus soles o dólares'
    Then se debe mostrar la lista de beneficios y requisitos del producto

    Examples: 
      | tipo_cliente | tipo_doc | num_doc |
      | PN           | DNI      | xxxxx   |
      | PJ           | DNI      | xxxx    |
      | PN           | CE       | xxxx    |

  Scenario: Validar que se muestre la lista de beneficios y requisitos accediendo con Biometría facial
    #No se muestra en las pantallas de prototipos
    Given ingreso a la web de 'mi negocio BCP' con biometría facial
    And el cliente cuenta con una cuenta activa en soles y/o dólares
    When selecciono opcíón 'Cambia tus soles o dólares'
    Then se debe mostrar la lista de beneficios y requisitos del productos

  Scenario Outline: Validar que se habilite la cotización del tipo de cambio soles a dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuenta activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    Then valido que se habilite la opción 'cotización'

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | tienes_necesitas | cta_origen | cta_destino |
      | PN           | DNI      | xxxxx   | Tengo soles      |            |             |
      | PJ           | DNI      | xxxx    | Necesito Dólares |            |             |
      | PN           | CE       | xxxx    | Tengo soles      |            |             |

  Scenario Outline: Validar que se habilite la cotización del tipo de cambio dólares a soles
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuenta activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    Then valido que se habilite la opción 'cotización'

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | tienes_necesitas | cta_origen | cta_destino |
      | PN           | DNI      | xxxxx   | Tengo dólares    |            |             |
      | PJ           | DNI      | xxxx    | Necesito soles   |            |             |
      | PN           | CE       | xxxx    | Tengo dólares    |            |             |

  Scenario Outline: Validar que se muestre los diferentes tipos de cuenta de Origen y Destino para la compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    #Rango 12:00 a.m - 11:45 p.m
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    Then validar que en la lista de cuenta origen se muestre las cuentas disponibles en soles "<cta_disponible_soles>"
    And validar que en la lista de cuanta destino se muestra las cuentas diponibles en dólares "<cta_disponible_dolares>"
    And validar que los números de cuenta no se muestren ofuscados

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cta_disponible_soles             | cta_disponible_dolares           |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros                   | cuenta ahorros                   |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros                   | cuenta corriente                 |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros                   | cuenta corriente                 |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente                 | cuenta corriente                 |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta ahorros, cuenta corriente | cuenta ahorros, cuenta corriente |

  Scenario Outline: Validar que se muestre los diferentes tipos de cuenta de Origen y Destino para la venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus dólares o soles'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    Then validar que en la lista de cuenta origen se muestre las cuentas disponibles en dólares "<cta_disponible_dólares>"
    And validar que en la lista de cuanta destino se muestra las cuentas diponibles en soles "<cta_disponible_soles>"
    And validar que los números de cuenta no se muestren ofuscados

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cta_disponible_soles             | cta_disponible_dolares           |
      | PN           | DNI      | xxxxx   |  1000 | Tengo dólares    | cuenta ahorros                   | cuenta ahorros                   |
      | PJ           | DNI      | xxxx    |  1000 | Necesito soles   | cuenta corriente                 | cuenta corriente                 |
      | PN           | CE       | xxxx    |  1000 | Tengo dólares    | cuenta ahorros, cuenta corriente | cuenta ahorros, cuenta corriente |

  Scenario Outline: Validar que los montos de la cotización para una compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    Then validar que el tipo de cambio aplicado sea de acuerdo al motor de cambios según el momento
    And si el tipo de cliente "<tipo_cliente>" es persona jurídica se debera consultar por IDC
    And se debe mostrar el monto ahorrado/ganado por el cliente
    And se debe mostrar una opción habilitada para poder regresar a la cotización
    And se debe mostrar la opción habilitada para confirmar la operación

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cta_disponible_soles             | cta_disponible_dolares           |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros                   | cuenta ahorros                   |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente                 | cuenta corriente                 |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta ahorros, cuenta corriente | cuenta ahorros, cuenta corriente |

  Scenario Outline: Validar que los montos de la cotización para una venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    Then validar que el tipo de cambio aplicado sea de acuerdo al motor de cambios según el momento
    And si el tipo de cliente "<tipo_cliente>" es persona jurídica se debera consultar por IDC
    And se debe mostrar el monto ahorrado/ganado por el cliente
    And se debe mostrar una opción habilitada para poder regresar a la cotización
    And se debe mostrar la opción habilitada para confirmar la operación

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      |

  Scenario Outline: Validar que se pueda retornar a la pantalla de cotización para la venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And selecciono la opción regresar
    Then se debe mostrar el mensaje "¿Seguro que deseas volver a cotizar?"
    And se debe habilitar nuevamente el tipo de operación y el ingreso del monto
    And deberá permitir volver a cotizar

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      |

  Scenario Outline: Validar que se pueda retornar a la pantalla de cotización para la compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And selecciono la opción regresar
    Then se debe mostrar el mensaje "¿Seguro que deseas volver a cotizar?"
    And se debe habilitar nuevamente el tipo de operación y el ingreso del monto
    And deberá permitir volver a cotizar

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      |

  Scenario Outline: Validar la confirmación de la venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "<tipo_cta_destino>"
    And selecciono o ingreso la cuenta destino "<cuenta_destino>"
    And ingresar los correos "<correos>" de envio de constancia
    And selecionar la opción siguiente y confirmar la operación
    Then se debe mostrar el mensaje informativo "estamos realizando tu transferencia"
    And se deberá validar la informacion mostrada en los campos: 'Monto Cambiado', 'tipo de cambio', 'cuenta origen', 'cuenta destino', 'número de operación' y 'calificación de la experiencia'
    And se deberá verificar la llegada del correo informativo al cliente
    And se deberá enviar una constancia al correo BCP 'minegocio@bcp.com.pe'
    And la operación deberá registrarse en una base de datos considerando el IDC, número de operacíón y el monto.

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen    | tipo_cta_destino | cuenta_destino   | correos                 |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros   | Propia           | cuenta ahorros   | a@b.com                 |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente | Otros BCP        | cuenta corriente | a@b.com,a@f.com         |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta corriente | Propia           | cuenta ahorros   | a@b.com,a@c.com,a@d.com |

  Scenario Outline: Validar la confirmación de la compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "<tipo_cta_destino>"
    And selecciono o ingreso la cuenta destino "<cuenta_destino>"
    And ingresar los correos "<correos>" de envio de constancia
    And selecionar la opción siguiente y confirmar la operación
    Then se debe mostrar el mensaje informativo "estamos realizando tu transferencia"
    And se deberá validar la informacion mostrada en los campos: 'Monto Cambiado', 'tipo de cambio', 'cuenta origen', 'cuenta destino', 'número de operación' y 'calificación de la experiencia'
    And se deberá verificar la llegada del correo informativo al cliente
    And se deberá enviar una constancia al correo BCP 'minegocio@bco.com.pe'
    And la operación deberá registrarse en una base de datos considerando el IDC, número de operacíón y el monto.

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen    | tipo_cta_destino | cuenta_destino   | correos                 |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros   | Propia           | cuenta ahorros   | a@b.com                 |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente | Otras BCP        | cuenta corriente | a@b.com,a@c.com         |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta corriente | Propia           | cuenta ahorros   | a@b.com,a@c.com,a@d.com |

  Scenario Outline: Validar el rango de monto mínimo en la compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    Then se debe mostrar el mensaje "<mensaje>" resaltado en color rojo
    And no se debe habilitar la opción 'Cotizar Cambio'

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | mensaje           |
      | PN           | DNI      | xxxxx   |   0.9 | Tengo soles      | monto mínimo S/ 1 |
      | PJ           | DNI      | xxxx    |   0.9 | Necesito dólares | monto mínimo $ 1  |
      | PN           | CE       | xxxx    |   0.9 | Tengo soles      | monto mínimo S/ 1 |

  Scenario Outline: Validar el rango de monto mínimo en la venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    Then se debe mostrar el mensaje "<mensaje>" resaltado en color rojo
    And no se debe habilitar la opción 'Cotizar Cambio'

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | mensaje           |
      | PN           | DNI      | xxxxx   |   0.9 | Tengo dólares    | monto mínimo $ 1  |
      | PJ           | DNI      | xxxx    |   0.9 | Necesito soles   | monto mínimo S/ 1 |
      | PN           | CE       | xxxx    |   0.9 | Tengo dólares    | monto mínimo $ 1  |

  Scenario Outline: Validar mensaje de error al no contar con saldo suficiente en la compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And la cuenta origen debe tener un monto menor a "<monto>"
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    Then se debe mostrar el mensaje "<mensaje>" de error
    And se debe mostrar el monto necesario para completar la operación

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto   | tienes_necesitas | mensaje                                                                                                           |
      | PN           | DNI      | xxxxx   |    1000 | Tengo soles      | Tu cuenta de origen no tiene saldo suficiente, puedes transferir saldo entre ellas para llegar al monto requerido |
      | PJ           | DNI      | xxxx    | 1000/TC | Necesito dólares | Tu cuenta de origen no tiene saldo suficiente, puedes transferir saldo entre ellas para llegar al monto requerido |
      | PN           | CE       | xxxx    |    1000 | Tengo soles      | Tu cuenta de origen no tiene saldo suficiente, puedes transferir saldo entre ellas para llegar al monto requerido |

  Scenario Outline: Validar el rango de monto mínimo en la venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And la cuenta origen debe tener un monto menor a "<monto>"
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    Then se debe mostrar el mensaje "<mensaje>" de error
    And se debe mostrar el monto necesario para completar la operación

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto   | tienes_necesitas | mensaje                                                                                                           |
      | PN           | DNI      | xxxxx   |    1000 | Tengo dólares    | Tu cuenta de origen no tiene saldo suficiente, puedes transferir saldo entre ellas para llegar al monto requerido |
      | PJ           | DNI      | xxxx    | 1000*TC | Necesito soles   | Tu cuenta de origen no tiene saldo suficiente, puedes transferir saldo entre ellas para llegar al monto requerido |
      | PN           | CE       | xxxx    |    1000 | Tengo dólares    | Tu cuenta de origen no tiene saldo suficiente, puedes transferir saldo entre ellas para llegar al monto requerido |

  Scenario Outline: Validar que no se pueda seleccionar una cuenta bloqueada o inactiva
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes bloqueada o inactiva en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And la cuenta origen debe tener un monto menor a "<monto>"
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    Then se debe mostrar los mensajes:  "<mensaje1>"  y "<mensaje2>"
    And no se debe permitir continuar con el flujo

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | mensaje1                              | mensaje2                                                   |  |
      | PN           | DNI      | xxxxx   |  1000 | Tengo dólares    | Esta cuenta esta bloqueada o inactiva | Comunicate con BCP al 0-800 para saber más sobre tu cuenta |  |
      | PJ           | DNI      | xxxx    |  1000 | Necesito soles   | Esta cuenta esta bloqueada o inactiva | Comunicate con BCP al 0-800 para saber más sobre tu cuenta |  |
      | PN           | CE       | xxxx    |  1000 | Tengo dólares    | Esta cuenta esta bloqueada o inactiva | Comunicate con BCP al 0-800 para saber más sobre tu cuenta |  |

  Scenario Outline: Validar que no se pueda seleccionar una cuenta mancomunada con co-titular
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes mancomunada con co-titular en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And la cuenta origen debe tener un monto menor a "<monto>"
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    Then se debe mostrar el mensaje "<mensaje>"
    And no se debe permitir continuar con el flujo

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | mensaje                              |
      | PN           | DNI      | xxxxx   |  1000 | Tengo dólares    | Requiere autorización del co-titular |
      | PJ           | DNI      | xxxx    |  1000 | Necesito soles   | Requiere autorización del co-titular |
      | PN           | CE       | xxxx    |  1000 | Tengo dólares    | Requiere autorización del co-titular |

  Scenario Outline: Validar que se muestre la opción de apertura de cuenta para una venta de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en dólares
    And no tenga cuenta en soles
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "Propia"
    Then se debe mostrar mensa informativo "<mensaje>"
    And se debe habilitar la opción 'Abrela Aqui'

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen    | mensaje                                                                                                          |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros   | Recuerda que despúes de abrir tu cuenta deberás volver a cotizar tu tipo de cambio, el cúal podría haber variado |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente | Recuerda que despúes de abrir tu cuenta deberás volver a cotizar tu tipo de cambio, el cúal podría haber variado |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta corriente | Recuerda que despúes de abrir tu cuenta deberás volver a cotizar tu tipo de cambio, el cúal podría haber variado |

  Scenario Outline: Validar que se muestre la opción de apertura de cuenta para una compra de dólares
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "Propia"
    Then se debe mostrar mensa informativo "<mensaje>"
    And se debe habilitar la opción 'Abrela Aqui'

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen    | mensaje                                                                                                          |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros   | Recuerda que despúes de abrir tu cuenta deberás volver a cotizar tu tipo de cambio, el cúal podría haber variado |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente | Recuerda que despúes de abrir tu cuenta deberás volver a cotizar tu tipo de cambio, el cúal podría haber variado |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta corriente | Recuerda que despúes de abrir tu cuenta deberás volver a cotizar tu tipo de cambio, el cúal podría haber variado |

  Scenario Outline: Validar el número máximo de envios de constancia
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes mancomunada con co-titular en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And la cuenta origen debe tener un monto menor a "<monto>"
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "<tipo_cta_destino>"
    And selecciono o ingreso la cuenta destino "<cuenta_destino>"
    And ingresar los correos "<correos>" de envio de constancia
    Then se debe mostrar el mensaje "<mensaje>"
    And no se debe permitir continuar con el flujo

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen | tipo_cta_destino | cuenta_destino | correos                          | mensaje                        |
      | PN           | DNI      | xxxxx   |  1000 | Tengo dólares    | xxxxxxxxxxxxx | Propia           | xxxxxxxxxxxxx  | a@b.com,a@bc.com,a@d.com,a@e.com | Solo puedes ingresar 3 correos |
      | PJ           | DNI      | xxxx    |  1000 | Necesito soles   | xxxxxxxxxxxxx | Otras BCP        | xxxxxxxxxxxxx  | a@b.com,a@bc.com,a@d.com,a@e.com | Solo puedes ingresar 3 correos |
      | PN           | CE       | xxxx    |  1000 | Tengo dólares    | xxxxxxxxxxxxx | Propia           | xxxxxxxxxxxxx  | a@b.com,a@bc.com,a@d.com,a@e.com | Solo puedes ingresar 3 correos |

  Scenario Outline: Validar el formato de correos de constancia
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes mancomunada con co-titular en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And la cuenta origen debe tener un monto menor a "<monto>"
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'dólares a soles'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo dólares' o 'necesito soles' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "<tipo_cta_destino>"
    And selecciono o ingreso la cuenta destino "<cuenta_destino>"
    And ingresar los correos "<correos>" de envio de constancia
    Then se debe mostrar el mensaje "<mensaje>" validando el formato "xxxx@xxxx.com"
    And no se debe permitir continuar con el flujo

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen | tipo_cta_destino | cuenta_destino | correos | mensaje                  |
      | PN           | DNI      | xxxxx   |  1000 | Tengo dólares    | xxxxxxxxxxxxx | Propia           | xxxxxxxxxxxxx  | a.com   | Ingresa un correo válido |
      | PJ           | DNI      | xxxx    |  1000 | Necesito soles   | xxxxxxxxxxxxx | Otras BCP        | xxxxxxxxxxxxx  | @a.com  | Ingresa un correo válido |
      | PN           | CE       | xxxx    |  1000 | Tengo dólares    | xxxxxxxxxxxxx | Propia           | xxxxxxxxxxxxx  | a@b     | Ingresa un correo válido |
      
      
 Scenario Outline: Validar el cierre de sesión
    Given ingreso a la web de 'mi negocio BCP' con "<tipo_cliente>" , "<tipo_doc>" y "<num_doc>"
    And el cliente cuenta con una cuentas ahorros y/o corrientes activa en soles y/o dólares
    And el cliente tiene el segmento Q1N
    And se encuentra dentro del horario de atención "Rango"
    When selecciono opcíón 'Cambia tus soles o dólares'
    And se seleccionamos opción 'continuar'
    And selecciono el tipo de operación 'soles a dólares'
    And ingresar el monto "<monto>" de acuerdo a: 'tengo soles' o 'necesito dólares' "<tienes_necesitas>"
    And ingreso la opción 'Cotizar Cambio'
    And seleccionar la cuenta de origen "<cuenta_origen>"
    And seleciono tipo de cuenta destino "<tipo_cta_destino>"
    And selecciono o ingreso la cuenta destino "<cuenta_destino>"
    And ingresar los correos "<correos>" de envio de constancia
    And selecionar la opción siguiente
    And seleccionar opción 'Cerrar sesión'
    Then se debe mostrar el mensaje informativo "¿Estás seguro de cerrar sesión?"
    And se debe habilitar la opción 'Si'
  	And se debe mostrar la pantalla inicial

    Examples: 
      | tipo_cliente | tipo_doc | num_doc | monto | tienes_necesitas | cuenta_origen    | tipo_cta_destino | cuenta_destino   | correos                 |
      | PN           | DNI      | xxxxx   |  1000 | Tengo soles      | cuenta ahorros   | Propia           | cuenta ahorros   | a@b.com                 |
      | PJ           | DNI      | xxxx    |  1000 | Necesito dólares | cuenta corriente | Otras BCP        | cuenta corriente | a@b.com,a@c.com         |
      | PN           | CE       | xxxx    |  1000 | Tengo soles      | cuenta corriente | Propia           | cuenta ahorros   | a@b.com,a@c.com,a@d.com |
      

