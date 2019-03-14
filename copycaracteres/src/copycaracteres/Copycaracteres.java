package copycaracteres;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Copycaracteres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileReader leer = new FileReader(new File("texto10.txt"));
        FileWriter escribe = new FileWriter(new File("texto11.txt"));
        Metodos m = new Metodos();
        m.tratarFicheiros(leer, escribe);
        leer.close();
        escribe.close();
    }

}
