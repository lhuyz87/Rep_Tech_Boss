/**
 * @author choucair
 * @version 2.0
 */
package tech.test.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class PageObjectUtil {

    // singleton
    private static PageObjectUtil obj = null;
    private long wdwTimeOut = 5L;

    private PageObjectUtil() {
    }

    public static PageObjectUtil getInstancia() {
        instanciar();
        return obj;
    }

    private synchronized static void instanciar() {
        if (obj == null) {
            obj = new PageObjectUtil();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    // singleton

    /**
     * selenium; lista los elementos DOM encontrados mediante el xpath, para hacer
     * click en un elemento mediante la posicion
     *
     * @param webDriver driver del page actual
     * @param xpath     xpath a usar
     * @param pos       posicion del elemento de la lista a usar
     */
    public void seleniumClick(WebDriver webDriver, final String xpath, int pos) {
        By by = By.xpath(xpath);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(1.5);
            }
        }
    }

    public boolean seleniumClick_EC(WebDriver webDriver, WebDriverWait wdw, final String path, int pos) {
        boolean res = false;
        By by = By.xpath(path);

        if (wdw == null) {
            wdw = new WebDriverWait(webDriver, wdwTimeOut, 1L);
        }
        try {
            wdw.until(ExpectedConditions.elementToBeClickable(by));
            res = true;
            List<WebElement> lista = webDriver.findElements(by);
            if (!lista.isEmpty()) {
                WebElement we = lista.get(pos);
                if (we.isDisplayed()) {
                    we.click();
                    //sleep(1.5);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.getStackTrace();
        }
        return res;
    }

    public void escribirConJS(WebDriver driver, WebElement element, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value='" + value + "';", element);
    }

    public void darClicConJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
    }


    public void seleccionarListaConJS(WebDriver driver, String xpath, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + xpath + "').options[document.getElementById('" + xpath + "').selectedIndex]." + value + ";");
    }


    public void seleniumEscribir(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);
            if (we.isDisplayed()) {
                //we.click();
                //sleep(0.25);
                //we.clear();
                we.sendKeys(valor);
                if (key != null) {
                    //	sleep(1.50);
                    we.sendKeys(key);
                }
                //sleep(1);
            }
        }
    }

    public void seleniumEscribir(WebDriver webDriver, final String path, String valor) {
        By by = By.xpath(path);
        WebElement element = webDriver.findElement(by);
        element.sendKeys(valor);

    }

    public void seleniumEscribirEspecial(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(0.25);
                we.sendKeys(Keys.HOME);

                for (int i = 0; i < 20; i++) {
                    we.sendKeys(Keys.DELETE);
                }

                we.sendKeys(valor);

                if (key != null) {
                    sleep(1.50);
                    we.sendKeys(key);
                }

                sleep(1);
            }
        }
    }

    public void seleniumEscribirEnCampos(WebDriver webDriver, final String path1, final String path2, int posDiv,
                                         int pos, String val, Keys key) {
        By by = By.xpath(path1);
        By by2 = By.xpath(path2);
        List<WebElement> listar = webDriver.findElements(by);

        if (!listar.isEmpty()) {
            List<WebElement> we = listar.get(posDiv).findElements(by2);

            if (!we.isEmpty()) {
                WebElement ww = we.get(pos);

                if (ww.isDisplayed()) {
                    ww.click();
                    sleep(0.25);
                    ww.clear();
                    ww.sendKeys(val);

                    if (key != null) {
                        sleep(1.50);
                        ww.sendKeys(key);
                    }

                    sleep(1);
                }
            }
        }
    }

    public void seleniumEscribir2(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(0.25);
                we.clear();
                we.sendKeys(valor);

                if (key != null) {
                    sleep(3);
                    we.sendKeys(key);
                }

                sleep(1);
            }
        }
    }

    public int cantidadVentanas(WebDriver webDriver) {
        List<String> lista = new ArrayList<String>(webDriver.getWindowHandles());

        return lista.size();
    }

    public void cambiarVentana(WebDriver webDriver, int pos) {
        sleep(3);
        List<String> lista = new ArrayList<String>(webDriver.getWindowHandles());

        if (lista.size() > pos) {
            webDriver.switchTo().window(lista.get(pos));
        }
    }

    public void seleccionarComboTexto(WebDriver webDriver, final String path, String valor) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        for (WebElement we : lista) {
            //System.out.println(we.getText().trim());
            if (we.getText().trim().equals(valor)) {
                we.click();
                break;
            }
        }
    }

    public boolean buscarDom(WebDriver webDriver, final String path, int pos) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);
        WebElement we = null;

        if (!lista.isEmpty()) {
            we = lista.get(pos);

            if (we.isDisplayed()) {
                return true;
            }
        }

        return false;
    }

    public WebElement seleniumWebElement(WebDriver webDriver, final String path, int pos) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            return lista.get(pos);
        }

        return null;
    }

    public WebElement seleniumWebElementLast(WebDriver webDriver, final String path) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);
        int i = lista.size() - 1;

        if (!lista.isEmpty()) {
            return lista.get(i);
        }

        return null;
    }

    public void sleep(double seg) {
        long miliseg = (new Double(seg * 1000)).longValue();

        try {
            // System.out.println("sleep:\t\t" + seg + " seg... <--> " + miliseg +
            // "miliseg...");
            Thread.sleep(miliseg);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<WebElement> seleniumListaByXpath(WebDriver webDriver, final String path, String value) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);


        return lista;
    }

    public List<WebElement> seleniumListaByTagName(WebDriver webDriver, final String path) {
        By by = By.tagName(path);
        List<WebElement> lista = webDriver.findElements(by);

        return lista;
    }

    public int seleniumPosicionDom(WebDriver webDriver, final String path, String valor) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getText().equals(valor)) {
                return i;
            }
        }

        return 0;
    }

    public void seleniumClear(WebDriver webDriver, final String path, int pos) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.clear();
                sleep(0.5);
            }
        }
    }

    public void seleniumDobleClick(WebDriver webDriver, WebElement we) {
        Actions actions = new Actions(webDriver);
        actions.doubleClick(we).build().perform();
        sleep(1.5);
    }

    public WebElementFacade serenityWebElement(PageObject page, final String path, int pos) {
        List<WebElementFacade> lista = page.findAll(path);
        sleep(1);

        if (!lista.isEmpty()) {
            return lista.get(pos);
        }

        return null;
    }

    public List<WebElementFacade> serenityListaByXpath(PageObject page, final String path) {
        List<WebElementFacade> lista = page.findAll(path);
        sleep(1);

        return lista;
    }

    public void seleniumClickJesus(WebDriver webDriver, final String path, int pos, int ejeX, int ejeY) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(we, ejeX, ejeY).click().build().perform();
            sleep(1.5);
        }
    }

    public void cargando(WebDriver webDriver, WebDriverWait wdw, final String path) {
        System.out.print("cargando:");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                try {
                    while (!wdw.until(ExpectedConditions.invisibilityOfElementLocated(by))) {
                        System.out.println("cargando...");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.getStackTrace();
                }

                sleep(1);
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public void cargando2(WebDriver webDriver, final String path, int cantidad, long wdwTimeOut) {
        System.out.print("cargando 2:");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        /*
         * while (lista.size() == pos) { lista = webDriver.findElements(by); sleep(1); }
         */

        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < (int) wdwTimeOut; i++) {
                sleep(1);

                if (lista.size() == cantidad) {
                    lista = webDriver.findElements(by);
                } else {
                    break;
                }
            }

            sleep(1);
        }

        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public void toolkitPegarEnWebElement(WebDriver webDriver, final String path, int pos, String valor, int keyEvent) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(0.25);
                we.clear();
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(valor), null);

                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);

                    if (keyEvent != 0) {
                        sleep(1.50);
                        robot.keyPress(keyEvent);
                        robot.keyRelease(keyEvent);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }

                sleep(1);
            }
        }
    }

    public void escribirEnObjetosHidden(WebDriver webDriver, final String pathHidden, final String pathInput, int pos,
                                        String valor) {
        WebElement elemHidden = webDriver.findElement(By.xpath(pathHidden));
        String js = "arguments[0].style.visibility='visible'";
        ((JavascriptExecutor) webDriver).executeScript(js, elemHidden);
        List<WebElement> lista = webDriver.findElements(By.xpath(pathInput));

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);
            we.sendKeys(Keys.HOME);

            for (int i = 0; i < 15; i++) {
                we.sendKeys(Keys.DELETE);
            }

            we.sendKeys(valor, Keys.ENTER);
            sleep(3);
        }
    }

    public WebElement seleniumBuscarDom(List<WebElement> lista, String valor) {
        for (WebElement we : lista) {
            if (we.getText().trim().equals(valor)) {
                return we;
            }
        }

        return null;
    }

    public void cargandoAE(WebDriver webDriver, final String path, long wdwTimeOut) {
        System.out.print("dom 'espere' AE:");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        for (int a = 0; a < 2; a++) {
            for (int i = 0; i < (int) wdwTimeOut; i++) {
                sleep(1);

                if (lista.size() == 1) {
                    lista = webDriver.findElements(by);
                } else {
                    break;
                }
            }

            sleep(1);
        }

        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public void cargandoExtra(WebDriver webDriver, final String path, long wdwTimeOut, int tam) {
        System.out.print(path + "\nesperar carga DOM: ");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        for (int i = 0; i < (int) wdwTimeOut; i++) {
            sleep(1);

            if (lista.size() != tam + 1) {
                lista = webDriver.findElements(by);
            } else {
                break;
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    /**
     * Metodo espera a que elemento sea visibile en el dom, segun su xpath.
     *
     * @param driver
     * @param tiempo_seg
     * @param xpath
     */
    public void esperar_visibilidad_elemento(WebDriver driver, int tiempo_seg, final String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, tiempo_seg);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    // EPI

    /**
     * Metodo pintar
     *
     * @param webDriver
     * @param xpath
     */
    public void paintXPath(WebDriver webDriver, String xpath, int pos) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement element = seleniumWebElement(webDriver, xpath, pos);
        js.executeScript("arguments[0].setAttribute('style', 'background: #F9F93E!important')", element);
    }

    /**
     * Metodo pintar los click
     *
     * @param webDriver
     * @param xpath
     * @param pos
     */
    public void seleniumClickPaint(WebDriver webDriver, final String xpath, int pos) {
        paintXPath(webDriver, xpath, pos);
        By by = By.xpath(xpath);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(1.5);
            }
        }
    }

    /**
     * Metodo Pintar los Input
     *
     * @param webDriver
     * @param path
     * @param pos
     * @param valor
     * @param key
     */
    public void seleniumEscribirPaint(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
        paintXPath(webDriver, path, pos);
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(0.25);
                we.clear();
                we.sendKeys(valor);

                if (key != null) {
                    sleep(1.50);
                    we.sendKeys(key);
                }

                sleep(1);
            }
        }
    }

    /**
     * Metodo Pintar option
     *
     * @param webDriver
     * @param path
     * @param valor
     */
    public void seleccionarComboPaint(WebDriver webDriver, final String path, String valor) {
        paintXPath(webDriver, path, 0);
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        for (WebElement we : lista) {
            if (we.getText().trim().equals(valor)) {
                we.click();
                break;
            }
        }
    }

    /*
     * Metodo pintar resultado correcto
     *
     * @param webDriver
     * @param path
     * @param pos
     * @return
     */

    public void underlineTextGood(WebDriver webDriver, final String xpath, int pos) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement element = seleniumWebElement(webDriver, xpath, pos);
        js.executeScript(
                "arguments[0].setAttribute('style', 'text-decoration-line: underline overline; text-decoration-color: #33FF42;text-decoration-style: double;')",
                element);
    }

    /*
     * Metodo pintar resultado incorrecto
     *
     * @param webDriver
     * @param path
     * @param pos
     * @return
     */
    public void underlineTextBad(WebDriver webDriver, final String xpath, int pos) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement element = seleniumWebElement(webDriver, xpath, pos);
        js.executeScript(
                "arguments[0].setAttribute('style', 'text-decoration-line: underline overline; text-decoration-color: #FF4633;text-decoration-style: double;')",
                element);
    }

    /*
     * Metodo pintar resultado salida
     *
     * @param webDriver
     * @param path
     * @param pos
     * @return
     */
    public void underlineTextNeutral(WebDriver webDriver, final String xpath, int pos) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement element = seleniumWebElement(webDriver, xpath, pos);
        js.executeScript(
                "arguments[0].setAttribute('style', 'text-decoration-line: underline overline; text-decoration-color: #F9F93E;text-decoration-style: double;')",
                element);
    }

    /**
     * Metodo Obtener texto de un dom
     *
     * @param webDriver
     * @param path
     * @param pos
     * @return
     */
    public String seleniumGetTexto(WebDriver webDriver, final String path, int pos) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);
        String texto;
        texto = lista.get(pos).getText();
        return texto;
    }

    /**
     * Verifica si existe un dom especifico
     *
     * @param webDriver
     * @param path
     * @param pos
     * @return
     */
    public String verificarExistencia(WebDriver webDriver, final String path, int pos) {
        String r = "";
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (lista.isEmpty()) {
            r = "";
        } else {
            lista.get(0);
            r = "OK";
        }
        return r;
    }

    /**
     * Metodo mover scroll de acuerdo a la ubicacion de un dom
     *
     * @param webDriver
     * @param xpath
     */
    public void scroll(WebDriver webDriver, String xpath, int pos) {
        WebElement scroll = seleniumWebElement(webDriver, xpath, pos);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", scroll);
    }

    /**
     * @param webDriver
     * @param xpath
     */
    public void verificarCarga(WebDriver webDriver, String xpath) {
        WebDriverWait wdw = new WebDriverWait(webDriver, 120);
        wdw.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    /**
     * METODO PARA ESCRIBIR EN UNA ETIQUETA INPUT
     *
     * @param webDriver
     * @param path
     * @param pos
     * @param valor
     * @param key
     */
    public void seleniumWrite(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);
            this.seleniumWaitPathClickable2(webDriver, we, 100);
            if (we.isDisplayed()) {
                we.click();
                sleep(0.4);
                we.clear();
                we.sendKeys(valor);
                if (key != null) {
                    we.sendKeys(key);
                }
            }
        }
    }

    /**
     * METODO QUE ESPERA QUE UNA ETIQUETA ESTE CLICKABLE
     *
     * @param webDriver
     * @param path
     * @param time
     */
    public void seleniumWaitPathClickable2(WebDriver webDriver, WebElement path, int time) {
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(ExpectedConditions.elementToBeClickable(path));
    }

    /**
     * @param webDriver
     * @param path
     * @param pos
     */
    public void seleniumClicked(WebDriver webDriver, final String path, int pos) {

        By by = By.xpath(path);
        int count = 60;
        while (count > 0) {
            List<WebElement> lista = webDriver.findElements(by);
            if (!lista.isEmpty()) {
                WebElement we = lista.get(pos);
                if (we.isDisplayed() && isClickable(we, webDriver)) {
                    we.click();
                    sleep(1.5);
                    count = -1;
                }
            }
            count--;
        }
    }

    /**
     * @param el
     * @param driver
     * @return
     */
    public boolean isClickable(WebElement el, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 6);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param webDriver
     * @param path
     * @param time
     */
    public void seleniumWaitPathAppear(WebDriver webDriver, String path, int time) {
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
    }

    /*
     *
     * @param webDriver
     * @param path
     */

    public void seleniumDobleClicked2(WebDriver webDriver, WebElement we) {

        Actions actions = new Actions(webDriver);
        actions.doubleClick(we).build().perform();
        sleep(0.5);
    }

    public void seleniumDobleClicked(WebDriver webDriver, String path) {
        sleep(0.25);
        int count = 6000;
        while (count > 0) {
            List<WebElement> witem = webDriver.findElements(By.xpath(path));
            if (!witem.isEmpty()) {
                Actions actions = new Actions(webDriver);
                actions.doubleClick(witem.get(0)).build().perform();
                sleep(0.5);
                count = -1;
            }
            count--;
        }
    }

    /**
     * @param webDriver
     * @param path
     * @param time
     */
    public void loading(WebDriver webDriver, final String path, double time) {
        System.out.print("loading:");
        sleep(2.5);
        long t1 = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(webDriver, 900);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(path)));
        sleep(time);
        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public void seleniumWaitPathClickable(WebDriver webDriver, String path, int time) {
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
    }

    public void seleniumEscribirSinClear(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
        By by = By.xpath(path);
        List<WebElement> lista = webDriver.findElements(by);

        if (!lista.isEmpty()) {
            WebElement we = lista.get(pos);

            if (we.isDisplayed()) {
                we.click();
                sleep(0.25);
                we.sendKeys(valor);

                if (key != null) {
                    sleep(1.50);
                    we.sendKeys(key);
                }
            }
        }
    }

    /**
     * SI EXISTE UN VALOR EN LA TABLA
     *
     * @param webDriver
     * @param path      -
     * @param pos       -POSICION DEL PATH
     * @param valor     TEXTO PARA BUSQUEDA
     * @return
     */
 

   
    
    public void seleniumClick(WebElement element, WebDriverWait wdw) {
//		wdw.until(ExpectedConditions.elementToBeClickable(element));
        sleep(0.50);
        element.click();
        sleep(0.50);
    }

    /**
     * selenium-pwe; escribimos usando el WebElement
     *
     * @param item
     * @param wdw
     * @param valor
     * @param key
     */
    public void seleniumEscribir(WebElement item, WebDriverWait wdw, String valor, Keys key) {
        sleep(0.50);
        wdw.until(ExpectedConditions.elementToBeClickable(item));
        sleep(0.50);
        item.clear();
        item.sendKeys(valor);
        sleep(0.50);
        item.sendKeys(key);
    }

    /**
     * metodo-pwe el cual hace click en Membership
     *
     * @param webDriver
     * @param id
     * @param valor
     * @param wdw
     */
    public void seliumClickMembership(WebDriver webDriver, final String id, final String valor, WebDriverWait wdw) {
        By by = By.xpath(id);
        WebElement ul = webDriver.findElement(by);
        wdw.until(ExpectedConditions.elementToBeClickable(ul));
        List<WebElement> liList = ul.findElements(By.tagName("li"));
        for (WebElement li : liList) {
            List<WebElement> alist = li.findElements(By.tagName("a"));
            boolean ite_encontrado = false;
            for (WebElement items : alist) {
                if (items.getText().equals(valor.trim())) {
                    items.click();
                    ite_encontrado = true;
                    break;
                }
            }
            if (ite_encontrado)
                break;
        }
    }

    /**
     * selenium-pwe,seleccionar combo por WebElement
     *
     * @param witem
     * @param contenido
     * @param path
     * @param wdw
     */
    public void seleccionarCombo(WebElement witem, String contenido, String path, WebDriverWait wdw) {
        wdw.until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(path)));
        Select dropdowntv = new Select(witem);
        dropdowntv.selectByVisibleText(contenido);
        //sleep(0.5);
    }

    public void seleccionarCombo(WebDriver driver, String path, String value) {
        waitForElementVisible(driver, path);
        Select objselect = new Select(driver.findElement(By.xpath(path)));
        try {
            objselect.selectByVisibleText(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            objselect.selectByVisibleText(value.toUpperCase());
        }
    }
    
    public void seleccionarElementoLista(WebDriver driver, String path, int index) {
    	new Select(driver.findElement(By.xpath(path))).selectByIndex(index);
    }

    public void seleccionarComboByValue(WebDriver driver, String path, int value) {
        Select objselect = new Select(driver.findElement(By.xpath(path)));
        objselect.selectByValue(value + "");
    }

    /**
     * selenium-pwe,hace click en el dia del datepicker-calendar
     *
     * @param webDriver
     * @param _idinilaboral
     * @param diahoy
     */
    public void seleniumClickDate(WebDriver webDriver, String _idinilaboral, int diahoy) {
        By by = By.xpath(_idinilaboral);
        WebElement table = webDriver.findElement(by);
        List<WebElement> alist = table.findElements(By.tagName("a"));
        WebElement actual = alist.get(diahoy);
        actual.click();
    }

    /**
     * selenium-pwe,retorna si esta vacio o no
     *
     * @param webDriver
     * @param id
     * @return
     */
    public boolean seleniumEmpty(WebDriver webDriver, final String id) {
        By by = By.xpath(id);
        WebElement item = webDriver.findElement(by);
        if (item.getAttribute("value") != "" && item.getAttribute("value") != null) {
            return true;
        } else
            return false;
    }

    /**
     * selenium,funcion cargando
     *
     * @param webDriver
     * @param wdw
     * @param path
     */
    public void cargandoPWE(WebDriver webDriver, WebDriverWait wdw, final String path) {
        System.out.println("cargando:true");
        By by = By.xpath(path);
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        while (true) {
            if (wdw.until(ExpectedConditions.invisibilityOfElementLocated(by))) {
                System.out.println("cargando invisible...");
                break;
            }
            System.out.println("cargando de verdad...");
        }
        sleep(2);
    }

    /***
     * Metodo para seleccionar un option de un select
     *
     * @param webDriver
     * @param path
     * @param valor
     */
    public void seleniumComboSelect(WebDriver webDriver, final String path, String valor) {
        WebElement select = webDriver.findElement(By.xpath(path));
        if (select.isEnabled()) {
            Select oSelect = new Select(select);
            List<WebElement> elementCount = oSelect.getOptions();
            int iSize = elementCount.size();
            int i = 0;
            String busca = "buscando";
            while (i <= iSize && busca.equals("buscando")) {
                String sValue = elementCount.get(i).getText().trim();

                if (sValue.contains(valor)) {
                    busca = "encontro";
                    elementCount.get(i).click();
                    sleep(0.2);
                }
                i++;
            }
        }
    }


    public String seleniumComboForNumber(WebDriver webDriver, final String path, int valor) {
        WebElement select = webDriver.findElement(By.xpath(path));
        String sValue = "";
        if (select.isEnabled()) {
            Select oSelect = new Select(select);
            List<WebElement> elementCount = oSelect.getOptions();
            int iSize = elementCount.size();

            String busca = "buscando";

            sValue = elementCount.get(valor).getText().trim();
            elementCount.get(valor).click();


        }


        return sValue;
    }


    /**
     * selenium; lista los elementos DOM encontrados mediante el xpath, luego espera
     * a que el elemento este disponible, para hacer click en un elemento mediante
     * la posicionF
     *
     * @param webDriver
     * @param wdw
     * @param xpath
     */
    public void seleniumClickUntil(WebDriver webDriver, WebDriverWait wdw, final String xpath) {
        System.out.print("seleniumClickUntil:");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(xpath);
        wdw.until(ExpectedConditions.elementToBeClickable(by));
        sleep(0.5);
        WebElement we = webDriver.findElement(by);
        we.click();
        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public String obtenerAtributo(WebDriver webDriver, WebDriverWait wdw, final String xpath, String atributo) {
        System.out.print("obtenerAtributo");
        sleep(1.5);
        String Valoratributo;
        By by = By.xpath(xpath);
        sleep(0.5);
        WebElement we = webDriver.findElement(by);
        Valoratributo = we.getAttribute(atributo);
        return Valoratributo;
    }

    public String obtenerTexto(WebDriver webDriver, final String xpath) {
        By by = By.xpath(xpath);
        WebElement we = webDriver.findElement(by);
        return we.getText();
    }

    /**
     * @param webDriver
     * @param wdw
     * @param path
     * @param valor
     * @param key
     */
    public void seleniumEscribirUntil(WebDriver webDriver, WebDriverWait wdw, final String path, String valor,
                                      Keys key) {
        System.out.print("seleniumEscribirUntil:");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(path);
        wdw.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(0.5);
        WebElement we = webDriver.findElement(by);
        we.click();
        we.clear();
        we.sendKeys(valor);
        sleep(2.0);
        if (key != null) {
            we.sendKeys(key);
        }

        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public void insertarKey(WebDriver webDriver, final String path, Keys key) {
        By by = By.xpath(path);
        WebElement we = webDriver.findElement(by);
        if (key != null) {
            we.sendKeys(key);
        }
    }

    /*
     *
     * @param driver
     * @param wdw
     * @param rpta01
     *
     */
    public void seleniumUntil(WebDriver driver, WebDriverWait wdw, final String path) {
        System.out.print("seleniumUntil:");
        long t1 = System.currentTimeMillis();
        By by = By.xpath(path);
        wdw.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(0.5);
        // scroll hasta el dom
        WebElement we = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", we);
        long t2 = System.currentTimeMillis();
        System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
    }

    public void waitForPageLoad(WebDriver driver) {

        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    public WebElement waitForElementVisible(WebDriver driver, final String path) {
        By by = By.xpath(path);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
            return null;
        }
    }

    public WebElement waitForElementVisible2(WebDriver driver, final String path) {
        By by = By.xpath(path);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        try {
            WebElement element = driver.findElement(by);
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("No se encontró el elemento: " + path);
            return null;
        }
    }

    public boolean waitForElementClickable(WebDriver driver, final String path, String x) {
        By by = By.xpath(path);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
            return false;
        }
    }


    public WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
            return null;
        }
    }


    public boolean isElementDisplayed(WebDriver driver, String path) {
        By by = By.xpath(path);
        try {
            WebElement element = driver.findElement(by);
            if (element.isDisplayed()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void removeInputAllBackspace(WebElement element, int nroRetry) {
        for (int i = 0; i < nroRetry; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }

        if (element.getAttribute("value").length() > 0) {
            for (int i = 0; i < nroRetry; i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }
    }

    public WebElement convertirAWebElement(WebDriver driver, String path) {
        By by = By.xpath(path);
        return driver.findElement(by);
    }

    public void seleccionarTexto(WebDriver driver, String path) {
        WebElement element = driver.findElement(By.xpath(path));
        element.sendKeys(Keys.chord(Keys.HOME, Keys.SHIFT, Keys.END));
    }

    public void irAlElemento(WebDriver driver, String path) {
        WebElement element = driver.findElement(By.xpath(path));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void irAlElemento(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void irAlElementoJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void irAltopPagina(WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 0);");
    }

    public String extraerNumeroCadena(String cadena) {
        char[] cadena_div = cadena.toCharArray();
        String numeros = "";
        for (int i = 0; i < cadena_div.length; i++) {
            if (Character.isDigit(cadena_div[i])) {
                numeros += cadena_div[i];
            }
        }
        return numeros;
    }

    public void seleccionarFecha(WebDriver driver, String fecha) {
        int dia = Integer.parseInt(fecha.split("-")[0]);
        String mes = fecha.split("-")[1];
        String anio = fecha.split("-")[2];
        mes = GeneralUtil.obtenerMes(mes);

        List<WebElement> elements;

        /******************************       AÑO      **************************/

        elements = driver.findElements(By.cssSelector(".Calendar__yearText"));

        for (WebElement year : elements) {
            if (!year.getText().isEmpty() && !year.getText().equals(anio + "")) {
                year.click();
                elements = driver.findElements(By.xpath("//*[@class='Calendar__yearSelectorText']"));
                waitForElementVisible(driver, elements.get(0));
                for (WebElement x : elements) {
                    if (x.getText().equals(anio)) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", x);
                        break;
                    }
                }
                break;
            }
        }


        /******************************       MES      **************************/

        waitForElementVisible(driver, driver.findElement(By.cssSelector(".Calendar__monthText")));
        elements = driver.findElements(By.cssSelector(".Calendar__monthText"));

        for (WebElement m : elements) {
            if (!m.getText().isEmpty() && m.getText() != mes) {
                m.click();
                elements = driver.findElements(By.cssSelector(".Calendar__monthSelectorItem"));
                waitForElementVisible(driver, elements.get(0));
                for (WebElement x : elements) {
                    if (x.getText().equals(mes)) {
                        x.click();
                        break;
                    }
                }
                break;
            }
        }

        /******************************       DIAS      **************************/

        waitForElementVisible(driver, driver.findElements(By.cssSelector(".-shown > .Calendar__weekRow .Calendar__day")).get(10));
        elements = driver.findElements(By.cssSelector(".-shown > .Calendar__weekRow .Calendar__day"));

        for (WebElement d : elements) {
            if (!d.getText().isEmpty()) {
                if (d.getText().equals(dia + "")) {
                    d.click();
                    break;
                }
            }
        }

        /**********************************************************************/

    }

    
    
    public void seleccionarMes(WebDriver driver,String xpathMes, String fecha) {
        String mes = fecha.split("-")[1];
         mes = GeneralUtil.obtenerMes(mes);

      String mesMostrado=obtenerTexto(driver, xpathMes);
        
        while(mesMostrado.compareTo(mes)!=0) {
        	seleniumClick(driver, "//span[text()='Next']",0);
        	sleep(1);
        	mesMostrado=obtenerTexto(driver, xpathMes);
        	System.out.println("Mes Mostrado "  + mesMostrado);
        }
  
    }

    public void uploadFile(WebDriver driver, String xpath, String path) {
        WebElement element = driver.findElement(By.xpath(xpath));
        File file = new File(path);
        element.sendKeys(file.getAbsolutePath());
    }

    public String fechaFormatoLatinoLargo() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = date.format(formatter);
        return text;
    }

    public String fechaFormatoLatino() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = date.format(formatter);
        return text;
    }

    public String fechaFormatoLatino(int sumaDias) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date = date.plusDays(sumaDias);
        String text = date.format(formatter);

        return text;
    }
    
    public void seleccionarListali(WebDriver driver, String xpath, String value) {
    	
    	WebElement countryUL= driver.findElement(By.xpath(xpath));
    	List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
    	for (WebElement li : countriesList) {
    		System.out.println(li.getText() + "vs "  + " Valor"  + value);
    	if (li.getText().equals(value)) {
    	     li.click();
    	     break;
    	   }
    	}
    }
}
