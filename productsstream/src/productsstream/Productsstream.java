package productsstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author oracle
 */
public class Productsstream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileOutputStream pfw = new FileOutputStream("/home/oracle/NetBeansProjects/productsstream/productos.txt", true);
        FileInputStream pfl = new FileInputStream("/home/oracle/NetBeansProjects/productsstream/productos.txt");

        InputStream in = null;
        OutputStream out = null;
        in = new BufferedInputStream(pfl);
        out = new BufferedOutputStream(pfw);

        DataOutputStream salida = new DataOutputStream(out);
        Product po1 = new Product("cod1", "parafusos", 3.0);
        Product po2 = new Product("cod2", "cravos", 4.0);
        Product po3 = new Product();
        Metodos m = new Metodos();

        m.escribirFicheiro(salida, po1);
        m.escribirFicheiro(salida, po2);

        DataInputStream entrada = new DataInputStream(in);
        salida.close();

        m.leerFicheiro(entrada, po3);
        System.out.println(po3.toString());
        m.leerFicheiro(entrada, po3);
        System.out.println(po3.toString());

        entrada.close();
    }
}
