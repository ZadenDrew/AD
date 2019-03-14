package baserelacionalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {

    public String driver = "jdbc:oracle:thin:";
    public String host = "localhost.localdomain";
    public String porto = "1521";
    public String sid = "orcl";
    public String usuario = "hr";
    public String password = "hr";
    public String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
    //para conectar co native protocal all java driver: creamos un obxecto Connection usando o metodo getConnection da clase  DriverManager            
    public Connection conn;
    public PreparedStatement st;

    public BaseDatos() {
    }

    public void connect() {
        try {

            conn = DriverManager.getConnection(url);
            // conn.setAutoCommit(false);
            if (conn != null) {
                System.out.println("Conectado.");
            }
        } catch (SQLException exsq) {
            System.out.println("Error: " + exsq);

        }

    }

    public void disconnect() {
        try {
            if (st != null) {
                st.close();
            }

            if (conn != null) {
                conn.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (SQLException ex) {
            System.out.println("Error: aaaaaaaa" + ex);
        }
    }

    public void insirePrep() {
        connect();
        try {

            st = conn.prepareStatement("insert into produtos" + "(codigo,descricion,prezo)values (?,?,?)");
            st.setString(1, "p5");
            st.setString(2, "desparafuso");
            st.setDouble(3, 12.0);

            st.execute();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        disconnect();

    }

    public void actuPrep(String codigo, Double prezo) {
        connect();
        try {

            st = conn.prepareStatement("UPDATE produtos set prezo =?  where codigo=?");
            st.setDouble(1, prezo);
            st.setString(2, codigo);
            st.executeUpdate();
            //conn.commit();

            System.out.println("La fila ha sido modificada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        disconnect();
    }

    public void consulta() {
        connect();

        try {

            ResultSet result;
            st = conn.prepareStatement("SELECT produtos.* from produtos");
            result = st.executeQuery();
            while (result.next()) {
                String codp = result.getString("codigo");
                String desc = result.getString("descricion");
                int precio = result.getInt("prezo");
                System.out.println(codp + "\t" + desc + "  \t" + precio);
            }
            System.out.println("Filas da táboa atopadas con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        disconnect();

    }

}
