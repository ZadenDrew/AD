package exad1;

import exa15.Exa15;
import exa15.Platos;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author oracle
 */
public class Exad1 {

    /**
     * @param args the command line arguments
     */
    static public PreparedStatement st, st2;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, XMLStreamException {
        Exa15.getConexion();

        String codp = null;
        String codc = null;
        String nomec = null;
        int peso = 0, graxa = 0, graxatotal = 0, graxaFinal = 0;
        ResultSet result, result2;
        FileInputStream pfr = new FileInputStream("/home/oracle/NetBeansProjects/exad1/platoss");
        ObjectInputStream in = null;
        in = new ObjectInputStream(pfr);
        Platos plido = new Platos();

        XMLOutputFactory fact = XMLOutputFactory.newInstance();
        XMLStreamWriter xtw = fact.createXMLStreamWriter(new FileWriter("/home/oracle/NetBeansProjects/exad1/totalgraxas.xml"));

        //escribe a declaracion XML con a Version especificada
        xtw.writeStartDocument("1.0");
        //escribe o tag de inicio de un elemento
        xtw.writeStartElement("Platos");

        while ((plido = (Platos) in.readObject()) != null) {

            codp = plido.getCodigop();
            System.out.println("CODIGO DO PLATO: " + plido.getCodigop());
            System.out.println("nome do plato: " + plido.getNomep());
            String s1 = ("SELECT * from composicion where CODP=?");
            st = Exa15.conexion.prepareStatement(s1);
            st.setString(1, codp);
            result = st.executeQuery();

            graxaFinal = 0;

            while (result.next()) {

                // codp = result.getString("codp");
                codc = result.getString("codc");
                peso = result.getInt("peso");
                // System.out.println(codp + "\t" + codc + "  \t" + peso);

                String s2 = ("SELECT * from componentes where codc=?");
                st2 = Exa15.conexion.prepareStatement(s2);
                st2.setString(1, codc);
                result2 = st2.executeQuery();

                while (result2.next()) {
                    codc = result2.getString("codc");
                    nomec = result2.getString("nomec");
                    graxa = result2.getInt("graxa");
                    System.out.println("codigo do componente :" + codc + "->graxa por cada 100gr = " + graxa);
                    System.out.println("peso : " + peso);
                }
                graxatotal = graxa * peso / 100;
                graxaFinal = graxaFinal + graxatotal;

                System.out.println("total graxa do componente = " + graxatotal + "\n");

            }
            System.out.println("TOTAL EN GRAXAS DO PLATO :" + graxaFinal + "\n");
            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("Plato");
            xtw.writeAttribute("Codigop", plido.getCodigop());
            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("nomep");
            //escribe o contido do elemento
            xtw.writeCharacters(plido.getNomep());
            xtw.writeEndElement();
            //escribe o tag de inicio de un elemento
            xtw.writeStartElement("graxatotal");
            //escribe o contido do elemento
            xtw.writeCharacters(String.valueOf(graxaFinal));
            //escribe o tag de peche do elemento actual
            xtw.writeEndElement();
            xtw.writeEndElement();
        }

        xtw.writeEndElement();
        xtw.writeEndDocument();
        xtw.flush();
        xtw.close();
        in.close();
        Exa15.closeConexion();

    }

}
