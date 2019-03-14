package primitivewriteutf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author oracle
 */
public class Metodos {

    public Metodos() {
    }

    public void tratarFicheiro(FileInputStream pfl, FileOutputStream pfw) {
        InputStream in = null;
        OutputStream out = null;
        try {
            String cadena = "Esto es una cadena \n";
            in = new BufferedInputStream(pfl);
            out = new BufferedOutputStream(pfw);

            DataOutputStream salida = new DataOutputStream(out);
            salida.writeUTF(cadena);

            System.out.println("writeUTF escribiu: " + cadena);
            int size = salida.size();
            System.out.println("writeUTF escribiu: " + size + "bytes");
            salida.writeUTF(cadena);
            System.out.println("writeUTF escribiu: " + cadena);
            System.out.println("writeUTF escribiu: " + size + "bytes");
            size = salida.size();
            System.out.println("bytes totais escritos: " + size + "bytes");
            salida.close();

            DataInputStream entrada = new DataInputStream(in);
            System.out.println("lemos a primeira cadea en UTF: " + entrada.readUTF());
            int size2 = entrada.available();
            System.out.println("numero de bytes lidos: " + size2 + "bytes");
            System.out.println("bytes restantes por ler: " + entrada.available() + "bytes");
            System.out.println("lemos a segunda cadea: " + entrada.readUTF());
            entrada.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
