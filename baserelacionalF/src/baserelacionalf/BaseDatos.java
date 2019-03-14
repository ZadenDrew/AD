package baserelacionalf;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author oracle
 */
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
    public CallableStatement cs;
    public int resultado;

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

    public void chamaProcedure() throws SQLException {
        connect();
        cs = conn.prepareCall("{call  pjavaprocoracle(?,?)}");

// Se indica que el primer interrogante es de salida.
// Se pasa un parámetro en el segundo y tercer interrogante.
        cs.setInt(1, 2);
        cs.setInt(2, 3);
        cs.registerOutParameter(2, Types.INTEGER);
// Se hace la llamada a la función.
        cs.execute();
        System.out.println(cs.getInt(2));


        disconnect();
    }
}
