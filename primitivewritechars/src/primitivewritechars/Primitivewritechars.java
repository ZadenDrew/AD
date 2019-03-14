package primitivewritechars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Primitivewritechars {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream pfl = new FileInputStream("/home/oracle/NetBeansProjects/primitivewritechars/texto4.txt");
        FileOutputStream pfw = new FileOutputStream("/home/oracle/NetBeansProjects/primitivewritechars/texto4.txt");

        Metodos m = new Metodos();
        m.tratarFicheiro(pfl,pfw);
    }

}
