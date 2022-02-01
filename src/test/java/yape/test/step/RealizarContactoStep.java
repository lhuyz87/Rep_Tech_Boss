package yape.test.step;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import tech.test.page.*;
import tech.test.util.PageObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class RealizarContactoStep {
	@Steps
	private PaginaPrincipalPage paginaPrincipalPage;
	
	
	@Step
    public void seleccionarOpcionContacto() {
    	paginaPrincipalPage.seleccionarContacto();
    }
     
	@Step
    public void llenarInformacionContacto(String nombre, String correo, String mensaje) {
    	
    	
    	paginaPrincipalPage.llenarInformacionContacto(nombre,correo, mensaje);
    	String valoresSuma[] = paginaPrincipalPage.obtenerSumaSeguridad();
    	int suma= Integer.parseInt(valoresSuma[0].trim())+ Integer.parseInt(valoresSuma[1].trim());
    	paginaPrincipalPage.ingresarSumaSeguridad(suma);
    	
    }
	@Step
	public void seleccionarEnviar() {
		paginaPrincipalPage.enviarInformacionContacto();
		
	}
    
    


  
}