package tech.test.xpath;



public class HomeSeidXpath {

    // singleton
    private static HomeSeidXpath obj = null;

    private HomeSeidXpath() {
    }

    public static HomeSeidXpath getInstancia() {
        instanciar();
        return obj;
    }

    private synchronized static void instanciar() {
        if (obj == null) {
            obj = new HomeSeidXpath();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

   
   
    
    public final String lstServicios="//li[@id='menu-item-564']";
   
    
    
 
    
   
}
