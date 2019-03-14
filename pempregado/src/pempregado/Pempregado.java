package pempregado;

/**
 *
 * @author oracle
 */
public class Pempregado {

    public static void main(String[] args) {

        BaseDatos bd = new BaseDatos();
        bd.connect();
        bd.listaEmpregados();
        bd.disconnect();
    }

}
