package textodelimitado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author oracle
 */
public class Textodelimitado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        PrintWriter escribir = null;
        BufferedReader leer = null;
       
            File pf = new File("/home/oracle/NetBeansProjects/textodelimitado/delimitado.txt");

            escribir = new PrintWriter(new BufferedWriter(new FileWriter(pf)));
            leer = new BufferedReader(new FileReader(pf));

            String[] cod = {"p1", "p2", "p3"};
            String[] desc = {"parafusos", "cravos", "tachas"};
            Double[] prezo = {3.0, 4.0, 5.0};

            Metodos m = new Metodos();
            m.escribeFichero(cod, desc, prezo, escribir);
            escribir.close();
            m.leerFichero(leer);
            leer.close();
       

    }

}
