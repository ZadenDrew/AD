package aleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author oracle
 */
public class Aleatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile raw = new RandomAccessFile("aleatorio.txt", "rw");
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3,4,5};
        Metodos m = new Metodos();
        m.escribeRandom(raw,cod,desc,prezo);
        m.leerRandom(raw);
        raw.close();
    }

}
