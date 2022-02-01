package yape.test.step;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import tech.test.page.*;
import tech.test.util.PageObjectUtil;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

public class ProductosStep {
	@Steps
	private PaginaPrincipalPage paginaPrincipalPage;
	
	

	@Step
    public void abrirPaginaPrincipal() {
        PageObjectUtil.getInstancia().fechaFormatoLatinoLargo();
       
			paginaPrincipalPage.inicializar();
		
    }
	@Step
    public void seleccionarProducto(String producto) {
    	
    	paginaPrincipalPage.seleccionarProducto(producto);
    	
    }
	@Step
	public void seleccionoBuscar() {
		paginaPrincipalPage.seleccionoBuscar();
		
	}
	@Step
	public void ingresaValoraBuscar(String valor) {
		paginaPrincipalPage.ingresarValorBuscar(valor);
		
	}
	@Step
	public void validarMensajeNoBusqueda(String mensajeEsperado) {
		
		String mensajeMostrado = paginaPrincipalPage.obtenerMensajeNoBusqueda();
		System.out.println( mensajeEsperado +" vs  "  + mensajeMostrado);
		Assert.assertEquals(mensajeEsperado, mensajeMostrado);
	}


    


  
}