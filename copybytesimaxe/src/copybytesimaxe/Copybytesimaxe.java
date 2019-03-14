
package copybytesimaxe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author oracle
 */
public class Copybytesimaxe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileInputStream fi;
        FileOutputStream fo;
        File pf = new File("/home/oracle/NetBeansProjects/copybytesimaxe/texto1.txt");
        File pf2 = new File("/home/oracle/NetBeansProjects/copybytesimaxe/foto2.jpg");
        Metodos met = new Metodos();
        //met.copiar(pf,pf2);
        met.copiarBuffer(pf, pf2);
    }
    
}
