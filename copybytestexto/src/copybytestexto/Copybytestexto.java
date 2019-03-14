package copybytestexto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author oracle
 */
public class Copybytestexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fi;
        FileOutputStream fo;
        File pf = new File("/home/oracle/NetBeansProjects/copybytestexto/texto1.txt");
        File pf2 = new File("/home/oracle/NetBeansProjects/copybytestexto/texto2.txt");
        Metodos met = new Metodos();
        met.copiar(pf,pf2);

    }

}
