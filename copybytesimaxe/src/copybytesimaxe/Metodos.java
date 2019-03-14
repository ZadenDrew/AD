package copybytesimaxe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void copiar(File pf, File pf2) {
        try {
            InputStream in = new FileInputStream(pf);
            OutputStream out = new FileOutputStream(pf2, true);

            int len;

            while ((len = in.read()) != -1) {
                out.write(len);
            }

            in.close();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void copiarBuffer(File pf, File pf2) {
        try {
            InputStream in = (new FileInputStream(pf));
            OutputStream out = (new FileOutputStream(pf2, true));
            BufferedInputStream ler = new BufferedInputStream(in);
            BufferedOutputStream escribir = new BufferedOutputStream(out);

            int len;

            while ((len = ler.read()) != -1) {
                escribir.write(len);
            }
            ler.close();
            escribir.close();
            in.close();
            out.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
