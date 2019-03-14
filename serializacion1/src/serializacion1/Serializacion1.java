package serializacion1;

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
public class Serializacion1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Mclase mc = new Mclase("ola", -7, 2.7E10);
        FileOutputStream pfw = new FileOutputStream("/home/oracle/NetBeansProjects/serializacion1/serial.txt");
        FileInputStream pfr = new FileInputStream("/home/oracle/NetBeansProjects/serializacion1/serial.txt");
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(pfw);
        out.writeObject(mc);
        pfw.close();
        Mclase mi = new Mclase();
        ObjectInputStream in = null;
        in = new ObjectInputStream(pfr);    
        System.out.println(in.readObject());

    }

}
