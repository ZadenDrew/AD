package primitivewriteutf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author oracle
 */
public class PrimitiveWriteUTF {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream pfl = new FileInputStream("/home/oracle/NetBeansProjects/primitiveWriteUTF/texto3.txt");
        FileOutputStream pfw = new FileOutputStream("/home/oracle/NetBeansProjects/primitiveWriteUTF/texto3.txt");

        Metodos m = new Metodos();
        m.tratarFicheiro(pfl, pfw);
    }

}
