package baserelacionalb;



/**
 *
 * @author oracle
 */
public class BaserelacionalB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BaseDatos bd = new BaseDatos();

        bd.mostrarProductos();
        bd.actualizaPre("p2", 8.0);
        bd.inserFila("p4", "martelo", 20.0);
        bd.borrafila("p3");
    }

}
