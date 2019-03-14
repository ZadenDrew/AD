package primitivewritechars;

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

    public void tratarFicheiro(FileInputStream pfl, FileOutputStream pfw) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        DataOutputStream salida = null;
        DataInputStream entrada = null;
        String cadena = "Esto es una cadena";
        String cadena2 = "Hola y adiós";
     
        try {

            in = new BufferedInputStream(pfl);
            out = new BufferedOutputStream(pfw);

            salida = new DataOutputStream(out);

            salida.writeChars(cadena);
            System.out.println("writeChars escribiu:" + cadena);
            int size = salida.size();
            System.out.println("writeChars escribiu:" + size + " bytes");
            salida.writeChars(cadena2);
            System.out.println("writeChars escribiu:" + cadena2);
            size = salida.size() - size;
            System.out.println("writeChars escribiu:" + size + " bytes");
            System.out.println("bytes bytes totais escritos:" + salida.size() + " bytes");
            salida.close();
            entrada = new DataInputStream(in);
            int size2 = entrada.available();
            System.out.print("lemos a primeira cadea:");
            for (int i = 0; i < cadena.length(); i++) {
                System.out.print(entrada.readChar());
            }
            System.out.println("\no numero de bytes lidos e :" + size2 + " bytes");
            System.out.println("bytes restantes por ler :" + entrada.available() + " bytes");
            System.out.print("lemos a segunda cadea:");
            size2 = entrada.available();
            for (int i = 0; i < cadena2.length(); i++) {
                System.out.print(entrada.readChar());
            }
            System.out.println("\no numero de bytes lidos e :" + size2 + " bytes");
            System.out.println("bytes restantes por ler :" + entrada.available() + " bytes");
            entrada.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }
}
