package primitiveutfchars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Primitiveutfchars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileOutputStream pfw = new FileOutputStream("/home/oracle/NetBeansProjects/primitiveutfchars/text3.txt");
        FileInputStream pfl = new FileInputStream("primitiveutfchars/text3.txt");

        Metodos m = new Metodos();
        m.tratarFicheiro(pfw, pfl);
    }

}
