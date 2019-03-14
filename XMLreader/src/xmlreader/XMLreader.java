package xmlreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import serializacion2.Product;

/**
 *
 * @author oracle
 */
public class XMLreader {

    /**
     * @param args the command line arguments
     */
    static int i = 0;

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        ArrayList<Product> productos = new ArrayList<>();
        Product prod = new Product();
        File f = new File("/home/oracle/NetBeansProjects/XMLwriter/products.xml");
        XMLInputFactory infact = XMLInputFactory.newInstance();
        XMLStreamReader xtw = infact.createXMLStreamReader(new FileReader(f));

        while (xtw.hasNext()) {
            int eventType = xtw.next();
            switch (eventType) {
                // handle start element
                case XMLStreamReader.START_ELEMENT:
                    String cod = null;
                    String desc = null;
                    String prez = null;
                    String elementName = xtw.getLocalName();
                    // System.out.println("<" + elementName + ">");
                    if ("codigo".equals(elementName)) {
                        xtw.next();
                       
                        cod = xtw.getText();
//                            System.out.println(cod);
                        prod.setCodigo(cod);
                    }
                    if ("descripcion".equals(elementName)) {
                        xtw.next();
                        desc = xtw.getText();
//                            System.out.println(desc);
                        prod.setDescripcion(desc);
                    }
                    if ("prezo".equals(elementName)) {
                        xtw.next();
                        prez = xtw.getText();
//                            System.out.println(prez);
                        prod.setPrezo(Double.parseDouble(prez));

                    }
                    if (prod.getCodigo() != null && prod.getDescripcion() != null && prod.getPrezo() != 0) {
                        productos.add(prod);
                        prod = new Product();

                    }

                    break;

                case XMLStreamReader.CHARACTERS:
                    String charac = xtw.getText();
                    System.out.println(charac);
                    break;

                case XMLStreamReader.END_ELEMENT:
                    String elementEnd = xtw.getLocalName();
                    // System.out.println("</" + elementEnd + ">");
                    break;
                case XMLStreamReader.COMMENT:
                    String coment = xtw.getText();
                    //System.out.println(coment);
                    break;
                case XMLStreamReader.DTD:
                    String dtd = xtw.getText();
                    //System.out.println(dtd);
                    break;
            }
        }
        xtw.close();
        System.out.println(productos);

    }

}
