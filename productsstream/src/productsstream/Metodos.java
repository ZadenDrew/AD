package productsstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {

    public Metodos() {
    }

    public void escribirFicheiro(DataOutputStream salida, Product p) {

        try {

            salida.writeUTF(p.getCodigo());
            salida.writeUTF(p.getDescripcion());
            salida.writeDouble(p.getPrezo());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public Product leerFicheiro(DataInputStream entrada, Product producto) throws IOException {
      
            producto.setCodigo(entrada.readUTF());
            producto.setDescripcion(entrada.readUTF());
            producto.setPrezo(entrada.readDouble());
        
        return producto;

    }

}
