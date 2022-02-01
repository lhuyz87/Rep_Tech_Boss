package tech.test.definition;

import com.ibm.icu.impl.InvalidFormatException;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import tech.test.util.*;
import yape.test.step.ProductosStep;
import yape.test.step.RealizarContactoStep;
import yape.test.step.SolucionesStep;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ContactoDefinition {
    @Steps
    private RealizarContactoStep realizarContactoStep;

 
    @Given("^selecciono opción contacto$")
    public void selecciono_opción_contacto() {
    	realizarContactoStep.seleccionarOpcionContacto();
 
    }

    @And("^llenar el formulario con información faltante: \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void llenar_el_formulario_con_información_faltante(String nombre, String correo, String mensaje) {
    	realizarContactoStep.llenarInformacionContacto(nombre, correo, mensaje);

    }
    
    @Given("^al seleccionar opción enviar no debe permitir el envio$")
    public void selecciono_enviar_contacto() {
    	realizarContactoStep.seleccionarEnviar();
 
    }
    
    
    
    
    


}
