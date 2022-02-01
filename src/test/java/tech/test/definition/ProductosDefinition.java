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
import yape.test.step.SolucionesStep;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProductosDefinition {
    @Steps
    private ProductosStep productosStep;

    @Steps
    private SolucionesStep solucionesStep;
    
    @Given("^que accedo a la página principal de Tech Boss$")
    public void que_accedo_a_la_página_principal_de_Tech_Boss() {
    	productosStep.abrirPaginaPrincipal();
    }

    @Given("^seleccionar los distintos produtos \"([^\"]*)\"$")
    public void seleccionar_los_distintos_produtos(String producto) {
    productosStep.seleccionarProducto(producto);
       
    }
    
    @Then("^validar que se pueda navegar en las distintas soluciones \"([^\"]*)\"$")
    public void validar_que_se_pueda_navegar_en_las_distintas_soluciones(String soluciones) {
        // Write code here that turns the phrase above into concrete actions
    		
    	solucionesStep.seleccionarSolucion(soluciones);
    }
    
    @And("^selecciono opcion buscar$")
    public void selecciono_opcion_buscar() {
        // Write code here that turns the phrase above into concrete actions
        productosStep.seleccionoBuscar();
    }


    @And("^ingreso el valor a buscar \"([^\"]*)\"$")
    public void ingreso_el_valor_a_buscar(String valor) {
        // Write code here that turns the phrase above into concrete actions
    	productosStep.ingresaValoraBuscar(valor);
    }

    @Then("^valido que se muestre mensaje de informativo \"([^\"]*)\"$")
    public void valido_que_se_muestre_mensaje_de_informativo(String mensajeEsperado) {
        // Write code here that turns the phrase above into concrete actions
    	productosStep.validarMensajeNoBusqueda(mensajeEsperado);
    }
    
    
    
    


}
