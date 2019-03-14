package serializacion2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author oracle
 */
public class Serializacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos ", "tachas"};
        Double[] prezo = {3.0, 4.0, 5.0};
        FileOutputStream pfw = new FileOutputStream("/home/oracle/NetBeansProjects/ serializacion2/serial2.txt");
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(pfw);
        for (int i = 0; i < 3; i++) {
            Product p = new Product(cod[i], desc[i], prezo[i]);
            out.writeObject(p);
        }
        Product pnull = null;
        out.writeObject(pnull);
        out.close();
        FileInputStream pfr = new FileInputStream("/home/oracle/NetBeansProjects/ serializacion2/serial2.txt");
        ObjectInputStream in = null;
        in = new ObjectInputStream(pfr);
        Product plido;
        while ((plido = (Product) in.readObject()) != null) {

            System.out.println(plido.getCodigo() + " " + plido.getDescripcion() + " " + plido.getPrezo());

        }
        in.close();

    }

}
