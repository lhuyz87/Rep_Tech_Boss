package yape.test.step;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import tech.test.page.*;
import tech.test.util.PageObjectUtil;
import tech.test.xpath.HomeSeidXpath;

import java.util.ArrayList;
import java.util.List;

public class SolucionesStep {

	@Steps
	private PrincipalSeifPage principalSeifPage;
	
	

    
       
    public void seleccionarSolucion(String solucion) {
    	
    	principalSeifPage.seleccionarSolucion(solucion);
    	
    }
    
    


  
}