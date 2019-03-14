package baserelacionalb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
    public Statement stmt;

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
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (SQLException ex) {
            System.out.println("Error: aaaaaaaa" + ex);
        }
    }

    public void mostrarProductos() {
        try {
            connect();
            ResultSet result;

            st = conn.prepareStatement("SELECT produtos.* FROM produtos");
            result = st.executeQuery();
            while (result.next()) {
                String codp = result.getString("codigo");
                String desc = result.getString("descricion");
                Double prez = result.getDouble("prezo");
                System.out.println(codp + "\t" + desc + "  \t" + prez);
            }
            System.out.println("La tabla se muestra con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        disconnect();
    }

    public void actualizaPre(String codigo, Double prezo) {
        connect();
        ResultSet uprs;

        try {
            stmt = conn.createStatement();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            uprs = stmt.executeQuery("SELECT produtos.* from produtos where codigo='" + codigo + "'");
            while (uprs.next()) {
                //  prezo = uprs.getDouble("prezo")+prezo;
                uprs.updateDouble("prezo", prezo);
                uprs.updateRow();
            }

            System.out.println("El precio ha sido modificado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        disconnect();
    }

    public void inserFila(String codigo, String descricion, double prezo) {
        try {
            connect();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet uprs = stmt.executeQuery("SELECT produtos.* FROM produtos ");

            uprs.moveToInsertRow();
            uprs.updateString("codigo", codigo);
            uprs.updateString("descricion", descricion);
            uprs.updateDouble("prezo", prezo);

            uprs.insertRow();
            uprs.beforeFirst();
            System.out.println("La fila ha sido insertada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
            disconnect();
        }
    }

    public void borrafila(String codigo) {
        connect();
        ResultSet uprs;

        try {
            stmt = conn.createStatement();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            uprs = stmt.executeQuery("SELECT produtos.* from produtos where codigo='" + codigo + "'");
            while (uprs.next()) {
                uprs.deleteRow();
            }

            System.out.println("La fila ha sido eliminada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        disconnect();
    }
}
