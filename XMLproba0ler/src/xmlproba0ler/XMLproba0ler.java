package xmlproba0ler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import static java.lang.String.valueOf;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class XMLproba0ler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory infact = XMLInputFactory.newInstance();

        XMLStreamReader xtw = infact.createXMLStreamReader(new FileReader("autores.xml"));
        while (xtw.hasNext()) {
            int eventType = xtw.next();
            switch (eventType) {

                // handle start element
                case XMLStreamReader.START_ELEMENT:
                    String elementName = xtw.getLocalName();
                    System.out.println("<" + elementName + ">");
                    if (elementName == "autor") {
                        // handle attribute
                        QName attributeName = xtw.getAttributeName(0);
                        String nomedoatributo = valueOf(attributeName);
                        String contido = xtw.getAttributeValue(0);
                        System.out.println(nomedoatributo + " = ' " + contido + "'");
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    String elementEnd = xtw.getLocalName();
                    System.out.println("</" + elementEnd + ">");
                    break;
                case XMLStreamReader.CHARACTERS:
                    String charac = xtw.getText();
                    System.out.println(charac);
                    break;
                case XMLStreamReader.COMMENT:
                    String coment = xtw.getText();
                    System.out.println(coment);
                    break;
                case XMLStreamReader.DTD:
                    String dtd = xtw.getText();
                    System.out.println(dtd);
                    break;

            }

        }
        xtw.close();
    }

}
