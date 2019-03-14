package copycaracteres;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {

    public void tratarFicheiros(FileReader leer, FileWriter escribe) throws IOException {
        int c = leer.read();
        while (c != -1) {
            System.out.print((char) c);
            escribe.write(c);
            c = leer.read();
        }
    }
  
}
