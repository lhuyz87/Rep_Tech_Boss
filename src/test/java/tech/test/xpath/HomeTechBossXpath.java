package tech.test.xpath;



public class HomeTechBossXpath {

  
    private static HomeTechBossXpath obj = null;

    private HomeTechBossXpath() {
    }

    public static HomeTechBossXpath getInstancia() {
        instanciar();
        return obj;
    }

    private synchronized static void instanciar() {
        if (obj == null) {
            obj = new HomeTechBossXpath();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

   
   
    
    public final String lstProductos="//li[@id='menu-item-43']";
    public final String lstContactos="//li[@id='menu-item-47']";
    
    //Contacto
    
    public final String txtNombreContacto="//input[@placeholder='Nombre']";
    public final String txtCorreoContacto="//input[@placeholder='Email Address']";
    public final String txtMensajeContacto="//textarea[@placeholder='Mensaje']";
    public final String lblPreguntaSeguridad="//textarea[@placeholder='Mensaje']//following :: span[1]";
    public final String btnEnviar="//button[@name='et_builder_submit_button']";
    public final String txtSumaSeguridad="//input[@class='input et_pb_contact_captcha']";
    
    //busqueda
    
    public final String icnBusqueda="//span[@id='et_search_icon']";
    public final String txtBusqueda="//input[@type='search']";
    public final String lblMensajeBusqueda="//*[@class='not-found-title']";
    
    
    
    
    
  
   
}
