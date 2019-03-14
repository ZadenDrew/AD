package primitiveutfchars;

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

    public void tratarFicheiro(FileOutputStream pfw, FileInputStream pfl) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        DataOutputStream salida = null;
        DataInputStream entrada = null;
        String cadena = "Esto es una cadena";
        String cadena2 = "Hola y adiós";
        String cadena3 = "Ya es hora";
        try {

            in = new BufferedInputStream(pfl);
            out = new BufferedOutputStream(pfw);

            salida = new DataOutputStream(out);

            salida.writeUTF(cadena);
            System.out.println("writeUTF escribiu:" + cadena);
            int size = salida.size();
            System.out.println("writeUTF escribiu:" + size + " bytes");

            salida.writeChars(cadena2);
            System.out.println("writeChars escribiu:" + cadena2);
            int size2 = salida.size() - size;
            System.out.println("writeChars escribiu:" + size2 + " bytes");

            salida.writeUTF(cadena3);
            System.out.println("writeUTF escribiu:" + cadena3);
            int size3 = salida.size() - size - size2;
            System.out.println("writeUTF escribiu:" + size3 + " bytes");
            System.out.println("bytes bytes totais escritos:" + salida.size() + " bytes");

            salida.close();
            entrada = new DataInputStream(in);
            int sizer = entrada.available();
            System.out.print("lemos a primeira cadea en UTF:" + entrada.readUTF());
            System.out.println("\no numero de bytes lidos e :" + (sizer - entrada.available()) + " bytes");
            System.out.println("bytes restantes por ler :" + entrada.available() + " bytes");
            System.out.print("lemos a segunda cadea:");
            sizer = entrada.available();
            for (int i = 0; i < cadena2.length(); i++) {
                System.out.print(entrada.readChar());
            }
            System.out.println("\no numero de bytes lidos e :" + (sizer - entrada.available()) + " bytes");
            System.out.println("bytes restantes por ler :" + entrada.available() + " bytes");
            System.out.print("lemos a terceira cadea  mediante readUTF:" + entrada.readUTF() + "\n");
            salida.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }
}
