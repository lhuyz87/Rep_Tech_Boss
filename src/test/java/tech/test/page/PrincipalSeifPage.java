package tech.test.page;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.ClearCookiesPolicy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import tech.test.util.PageObjectUtil;
import tech.test.xpath.HomeSeidXpath;
import tech.test.xpath.HomeTechBossXpath;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;



@DefaultUrl("https://techboss.com.co/")
public class PrincipalSeifPage extends PageObject {
	
	protected HomeSeidXpath homeSeidXpath = HomeSeidXpath.getInstancia();
	
	@Managed(clearCookies = ClearCookiesPolicy.BeforeEachTest)
	private WebDriverWait wdw = null;
	private long wdwTimeOut = 300L;
	


    // util
    protected PageObjectUtil pageObjectUtil = PageObjectUtil.getInstancia();
    public long getWdwTimeOut() {
		return wdwTimeOut;
	}

 
    
    public void seleccionarSolucion(String solucion) {
    	pageObjectUtil.sleep(5);
    	pageObjectUtil.seleniumClick(getDriver(), homeSeidXpath.lstServicios, 0);
    	pageObjectUtil.seleccionarListali(getDriver(), homeSeidXpath.lstServicios, solucion);
    	
    	
    }

    
  
  

   
}
