package exa15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class Exa15 {
    public static Connection conexion=null;

    public static Connection getConexion() throws SQLException  {
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
//      public void mostrarProductos() {
//        try {
//            getConexion();
//            ResultSet result;
//
//            st = conexion.prepareStatement("SELECT produtos.* FROM produtos");
//            result = st.executeQuery();
//            while (result.next()) {
//                String codp = result.getString("codigo");
//                String desc = result.getString("descricion");
//                Double prez = result.getDouble("prezo");
//                System.out.println(codp + "\t" + desc + "  \t" + prez);
//            }
//            System.out.println("La tabla se muestra con éxito.");
//        } catch (SQLException ex) {
//            System.out.println("Error: " + ex);
//        }
//        closeConexion();
//      }
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, XMLStreamException {
       //codigo aqui
        
        
    }
}
