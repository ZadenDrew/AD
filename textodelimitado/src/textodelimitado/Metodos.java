package textodelimitado;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {

   

    public Metodos() {
    }

   

    public void escribeFichero(String[] cod, String[] desc, Double[] prezo, PrintWriter escribir) {
        for (int i = 0; i <= 2; i++) {

            escribir.print(cod[i]);
            escribir.print("\t");
            escribir.print(desc[i]);
            escribir.print("\t");
            escribir.println(prezo[i]);
        }

    }

    public void leerFichero(BufferedReader leer) throws IOException {
        String lineas;
        String[] linea;

        Product p =new Product();
        while ((lineas = leer.readLine()) != null) {
            linea = lineas.split("\t");
            p.setCodigo(linea[0]);
            p.setDescripcion(linea[1]);
            p.setPrezo(Double.parseDouble(linea[2]));
            System.out.println(p.getCodigo());
            System.out.println(p.getDescripcion());
            System.out.println(p.getPrezo().toString());

        }

    }

}
