package exad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class Exad2 {

    /**
     * @param args the command line arguments
     */
    static String codp = null, codc = null, nomec = null, nomep = null, elementName;
    static int peso = 0, graxa = 0, graxatotal = 0, graxaFinal = 0;
    public static Connection conexion = null;
    static public PreparedStatement st;

    public static Connection getConexion() throws SQLException {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost";
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;

        conexion = DriverManager.getConnection(ulrjdbc);
        return conexion;
    }

    public static void closeConexion() throws SQLException {
        conexion.close();
    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, IOException, SQLException {
        ArrayList<Platos> listaPlatos = new ArrayList<>();
        Platos p = new Platos();
        ResultSet result;
        File f = new File("/home/oracle/NetBeansProjects/exad2/platos.xml");
        XMLInputFactory infact = XMLInputFactory.newInstance();
        XMLStreamReader xtw = infact.createXMLStreamReader(new FileReader(f));
        // PrintWriter escribir = null;
        BufferedReader leer = null;

        File pf = new File("/home/oracle/NetBeansProjects/exad2/composicion.txt");

        // escribir = new PrintWriter(new BufferedWriter(new FileWriter(pf)));
        leer = new BufferedReader(new FileReader(pf));
        String lineas = "";
        String[] linea;

        while (xtw.hasNext()) {
            //int peso = 0, graxa = 0;
            int eventType = xtw.next();

            if (XMLStreamReader.START_ELEMENT == eventType) {
                elementName = xtw.getLocalName();

                if ("Plato".equals(elementName)) {
                    codp = xtw.getAttributeValue(0);
                    eventType = xtw.next();
                    nomep = xtw.getElementText();

                    p.setCodp(codp);
                    p.setNomep(nomep);

                    System.out.println("CODIGO DO PLATO: " + codp);
                    System.out.println("nome do plato :" + nomep);

                    leer = new BufferedReader(new FileReader(pf));
                    graxaFinal = 0;
                    while ((lineas = leer.readLine()) != null) {
                        linea = lineas.split("#");

                        if (codp.equals(linea[0])) {
                            codc = (linea[1]);
                            peso = (Integer.parseInt(linea[2]));

                            String s = ("SELECT * from componentes where codc='" + codc + "'");
                            st = conexion.prepareStatement(s);
                            st.setString(1, codc);
                            result = st.executeQuery();

                            while (result.next()) {
//                                codc = result.getString("codc");
//                                nomec = result.getString("nomec");
                                graxa = result.getInt("graxa");
                                closeConexion();
                            }
                            graxatotal = graxa * peso / 100;
                            graxaFinal = graxaFinal + graxatotal;
                            System.out.println("codigo do componente :" + codc + "-> graxa por cada 100gr= " + graxa);
                            System.out.println("peso : " + peso + "\n");
                            System.out.println("total graxa do componente = " + graxatotal + "\n");
                            System.out.println("TOTAL EN GRAXAS DO PLATO: " + graxaFinal + "\n");
                            peso = 0;
                            p.setGraxa(graxaFinal);
//                    listaPlatos.add(p);
//                    p = new Platos();
                        }

                    }
                    leer.close();

                }

            }

        }

        xtw.close();

//        System.out.println(listaPlatos);
    }

}
