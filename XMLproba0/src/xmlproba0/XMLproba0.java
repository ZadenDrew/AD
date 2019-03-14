package xmlproba0;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author oracle
 */
public class XMLproba0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            XMLOutputFactory fact = XMLOutputFactory.newInstance();

            XMLStreamWriter xtw = fact.createXMLStreamWriter(new FileWriter("autores.xml"));
            //escribe a declaracion XML con a Version especificada
            xtw.writeStartDocument("1.0");

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("autores");
            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("autor");
            //escribe un atributo para o lemento actual
            xtw.writeAttribute("codigo", "a1");
            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("nome");
            //escribe o contido do elemento
            xtw.writeCharacters("Alexandre Dumas");
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("titulo");
            //escribe o contido do elemento
            xtw.writeCharacters("El conde de montecristo");
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("titulo");
            //escribe o contido do elemento
            xtw.writeCharacters("Los miserables");
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();
            xtw.writeEndElement();

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("autor");
            //escribe un atributo para o lemento actual
            xtw.writeAttribute("codigo", "a2");
            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("nome");
            //escribe o contido do elemento
            xtw.writeCharacters("Fiodor Dostoyevski");
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("titulo");
            //escribe o contido do elemento
            xtw.writeCharacters("El idiota");
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();

            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("titulo");
            //escribe o contido do elemento
            xtw.writeCharacters("Noches blancas");
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();
            xtw.writeEndElement();

            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();
            xtw.flush();
            xtw.close();
        } catch (XMLStreamException ex) {
            System.out.println("Error: " + ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLproba0.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
