package tech.test.page;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.ClearCookiesPolicy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import tech.test.util.PageObjectUtil;
import tech.test.xpath.HomeTechBossXpath;

import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;



@DefaultUrl("https://techboss.com.co/")
public class PaginaPrincipalPage extends PageObject {
	
	protected HomeTechBossXpath homeTechBossXpath = HomeTechBossXpath.getInstancia();
	
	@Managed(clearCookies = ClearCookiesPolicy.BeforeEachTest)
	private WebDriverWait wdw = null;
	private long wdwTimeOut = 300L;
	


    // util
    protected PageObjectUtil pageObjectUtil = PageObjectUtil.getInstancia();
    public long getWdwTimeOut() {
		return wdwTimeOut;
	}

    public void inicializar() {

        getDriver().get("https://techboss.com.co/");
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
        Serenity.takeScreenshot();

    }
    
    
    public void seleccionarProducto(String producto) {
    	
    	pageObjectUtil.seleniumClick(getDriver(), homeTechBossXpath.lstProductos, 0);
    	pageObjectUtil.sleep(2);
    	pageObjectUtil.seleccionarListali(getDriver(), homeTechBossXpath.lstProductos, producto);
    	
     }
    
    public void seleccionarContacto() {
    	pageObjectUtil.seleniumClick(getDriver(), homeTechBossXpath.lstContactos, 0);
    	pageObjectUtil.sleep(3);
    	pageObjectUtil.seleccionarListali(getDriver(), homeTechBossXpath.lstContactos, "Contacto");
    }

	public void llenarInformacionContacto(String nombre, String correo, String mensaje) {
		
		pageObjectUtil.seleniumEscribir(getDriver(), homeTechBossXpath.txtNombreContacto, nombre);
		pageObjectUtil.seleniumEscribir(getDriver(), homeTechBossXpath.txtCorreoContacto, correo);
		pageObjectUtil.seleniumEscribir(getDriver(), homeTechBossXpath.txtMensajeContacto, mensaje);
	}

	public String[] obtenerSumaSeguridad() {
		
		String sumaSeguridad=pageObjectUtil.obtenerTexto(getDriver(), homeTechBossXpath.lblPreguntaSeguridad);
		System.out.print(" " + sumaSeguridad);
		String[] valoresSuma= sumaSeguridad.split("\\+");
		return valoresSuma;
	}

	public void enviarInformacionContacto() {
		pageObjectUtil.seleniumClick(getDriver(), homeTechBossXpath.btnEnviar, 0);
		
	}

	public void ingresarSumaSeguridad(int suma) {
		pageObjectUtil.seleniumEscribir(getDriver(), homeTechBossXpath.txtSumaSeguridad, suma+"");
		
	}

	public void seleccionoBuscar() {
		pageObjectUtil.seleniumClick(getDriver(), homeTechBossXpath.icnBusqueda, 0);
		pageObjectUtil.sleep(2);
	}

	public void ingresarValorBuscar(String valor) {
		pageObjectUtil.sleep(2);
		pageObjectUtil.seleniumEscribir(getDriver(), homeTechBossXpath.txtBusqueda, valor);
		pageObjectUtil.sleep(2);
		pageObjectUtil.insertarKey(getDriver(), homeTechBossXpath.txtBusqueda, Keys.ENTER);
	}

	public String obtenerMensajeNoBusqueda() {
		
		SoftAssert sa = new SoftAssert();
		
		 sa.assertTrue(pageObjectUtil.isElementDisplayed(getDriver(), homeTechBossXpath.lblMensajeBusqueda));
	     String mensajeMostrado=  pageObjectUtil.obtenerTexto(getDriver(), homeTechBossXpath.lblMensajeBusqueda);
	     return mensajeMostrado;
	}
	
	
	
	
    
  
  

   
}
