package copybytestexto;

import java.io.File;
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

    public void copiar(File pf,File pf2) {
        try {
            InputStream in = new FileInputStream(pf);
            OutputStream out = new FileOutputStream(pf2,true);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
