package xmlwriter;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import serializacion2.Product;

/**
 *
 * @author oracle
 */
public class XMLwriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Product plido = new Product();
        try {
            FileInputStream pfr = new FileInputStream("/home/oracle/NetBeansProjects/ serializacion2/serial2.txt");
            ObjectInputStream in = null;
            in = new ObjectInputStream(pfr);

            XMLOutputFactory fact = XMLOutputFactory.newInstance();

            XMLStreamWriter xtw = fact.createXMLStreamWriter(new FileWriter("products.xml"));
            //escribe a declaracion XML con a Version especificada
            xtw.writeStartDocument("1.0");

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("products");
            while ((plido = (Product) in.readObject()) != null) {

                System.out.println(plido.getCodigo() + " " + plido.getDescripcion() + " " + plido.getPrezo());

                //escribe o tag de inicio de un elemento
                xtw.writeStartElement("product");
                //escribe o tag de inicio de un elemento
                xtw.writeStartElement("codigo");
                //escribe o contido do elemento
                xtw.writeCharacters(plido.getCodigo());
                //escribe o tag de peche do elemento actual
                xtw.writeEndElement();

                //escribe o tag de inicio de un elemento
                xtw.writeStartElement("descripcion");
                //escribe o contido do elemento
                xtw.writeCharacters(plido.getDescripcion());
                //escribe o tag de peche do elemento actual
                xtw.writeEndElement();

                //escribe o tag de inicio de un elemento
                xtw.writeStartElement("prezo");
                //escribe o contido do elemento
                xtw.writeCharacters(String.valueOf(plido.getPrezo()));
                //escribe o tag de peche do elemento actual
                xtw.writeEndElement();
                xtw.writeEndElement();

            }
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();
            xtw.flush();
            xtw.close();
            in.close();
        } catch (XMLStreamException ex) {
            System.out.println("Error: " + ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLwriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
