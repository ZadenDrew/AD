
package baserelacionalc;

/**
 *
 * @author oracle
 */
public class BaserelacionalC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     BaseDatos bd=new BaseDatos();
     //Product p5=new Product();
     //bd.insirePrep();
     bd.actuPrep("p5", 8.0);
     bd.consulta();
    }
    
}
