package examenad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author oracle
 */
public class ExamenAD {

    /**
     * @param args the command line arguments
     */
    static String codigoa = null, tipoUva = null, dni = null, tratacidez = null, nome = null, tel = null;
    static int acidez, grao, taninos, cantidade, total, numanalisis;
    public static Connection conexion = null;
    static public PreparedStatement st;
    static public Statement stm, stm2;

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

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {

//        String Tipo = null, nomeu = null;
//        int acidezmin, acidezmax;
        // PrintWriter escribir = null;
        BufferedReader leer = null;

        File pf = new File("/home/oracle/NetBeansProjects/examenAD/analisis.txt");

        // escribir = new PrintWriter(new BufferedWriter(new FileWriter(pf)));
        leer = new BufferedReader(new FileReader(pf));
        String lineas = "";
        String[] linea;
        while ((lineas = leer.readLine()) != null) {
            linea = lineas.split(",");
            codigoa = (linea[0]);
            acidez = (Integer.parseInt(linea[1]));
            grao = (Integer.parseInt(linea[2]));
            taninos = (Integer.parseInt(linea[3]));
            tipoUva = (linea[4]);
            cantidade = (Integer.parseInt(linea[5]));
            total = cantidade * 15;
            dni = (linea[6]);

            //System.out.println(lineas);
            //System.out.println(codigoa + "," + acidez + "," + grao + "," + taninos + "," + tipoUva + "," + cantidade + "," + dni);
            getConexion();
            ResultSet result;
            String s = ("SELECT * from uvas where TIPO='" + tipoUva + "'");
            st = conexion.prepareStatement(s);
            // st.setString(1, Tipo);
            result = st.executeQuery();

            while (result.next()) {
                String tipo = result.getString("TIPO");
                String nomeu = result.getString("NOMEU");
                int acidezmin = result.getInt("ACIDEZMIN");
                int acidezmax = result.getInt("ACIDEZMAX");
                System.out.println(tipo + nomeu + " A acidez mínima é :" + acidezmin + " A acidez máxima é :" + acidezmax);
                System.out.println("Total " + total);
                if (acidez < acidezmin) {
                    tratacidez = "subir acidez";
                }
                if (acidez > acidezmax) {
                    tratacidez = "baixar acidez";
                }
                if (acidez > acidezmin && acidez < acidezmax) {
                    tratacidez = "equilibrada";
                }
                System.out.println(tratacidez);

                stm2 = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ResultSet uprs2 = stm2.executeQuery("SELECT clientes.* FROM clientes where DNI ='" + dni + "'");
                uprs2.moveToInsertRow();
                String dni2 = uprs2.getString("DNI");
                nome = uprs2.getString("NOME");
                tel = uprs2.getString("TELF");
                numanalisis = uprs2.getInt("NUMERODEANALISIS");

//                uprs2.updateString("DNI", dni);
//                uprs2.updateString("NOME", nome);
//                uprs2.updateString("TELF", tel);
                if (dni.equals(dni2)) {
                    uprs2.updateString("DNI", dni);
                    uprs2.updateString("NOME", nome);
                    uprs2.updateString("TELF", tel);
                    numanalisis = 7;
                    uprs2.updateInt("NUMERODEANALISIS", numanalisis);
                } else if (dni.equals(dni2)) {
                    uprs2.updateString("DNI", dni);
                    uprs2.updateString("NOME", nome);
                    uprs2.updateString("TELF", tel);
                    numanalisis = 18;
                    uprs2.updateInt("NUMERODEANALISIS", numanalisis);
                } else if (dni.equals(dni2)) {
                    uprs2.updateString("DNI", dni);
                    uprs2.updateString("NOME", nome);
                    uprs2.updateString("TELF", tel);
                    numanalisis = 1;
                    uprs2.updateInt("NUMERODEANALISIS", numanalisis);
                }
                // uprs2.updateInt("NUMERODEANALISIS", numanalisis);
                System.out.println();
                uprs2.insertRow();
                uprs2.beforeFirst();
                System.out.println("La fila ha sido insertada con éxito.");

                stm = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                ResultSet uprs = stm.executeQuery("SELECT xerado.* FROM xerado ");

                uprs.moveToInsertRow();
                uprs.updateString("NUM", codigoa);
                uprs.updateString("NOMEUVA", nomeu);
                uprs.updateString("TRATACIDEZ", tratacidez);
                uprs.updateInt("TOTAL", total);
                System.out.println(tipoUva);
                uprs.insertRow();
                uprs.beforeFirst();
                System.out.println("La fila ha sido insertada con éxito.");

            }
            closeConexion();
        }

    }
}
