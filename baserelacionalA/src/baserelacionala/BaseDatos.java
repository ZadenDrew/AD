package baserelacionala;

import static baserelacionala.BaserelacionalA.codigot;
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

    public void insireProducto(Product p) {

        try {

            st = conn.prepareStatement("insert into produtos values (?,?,?)");
            st.setString(1, p.getCodigo());
            st.setString(2, p.getDescripcion());
            st.setDouble(3, p.getPrezo());

            st.execute();
            //conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        disconnect();

    }

    public ArrayList mostrarProductos() {
        ArrayList<Product> listaProductos = new ArrayList();
        connect();
        ResultSet result;
        try {
            st = conn.prepareStatement("SELECT * FROM produtos");
            result = st.executeQuery();
            while (result.next()) {

                codigot = "hihi";
                Product pr = new Product(result.getString("codigo"), result.getString("descricion"), result.getDouble("prezo"));
                listaProductos.add(pr);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        disconnect();
        return listaProductos;
    }

    public void actualizaPre(String codigo, Double prezo) {
        connect();
        try {

            st = conn.prepareStatement("UPDATE produtos set prezo = '" + prezo + "' where codigo='" + codigo + "'");
            st.executeUpdate();
            conn.commit();

            System.out.println("La fila ha sido modificada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        disconnect();
    }

    public Product amosarFila(String codigo) {
        connect();
        Product p = null;
        try {

            ResultSet result;
            st = conn.prepareStatement("SELECT * from produtos where codigo='" + codigo + "'");
            result = st.executeQuery();
            while (result.next()) {
                p = new Product(result.getString("codigo"), result.getString("descricion"), result.getDouble("prezo"));
               
            }
            System.out.println("Filas atopada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        disconnect();
        return p;
    }

    public void amosarFila2(String codigo) {
        connect();

        try {

            ResultSet result;
            st = conn.prepareStatement("SELECT * from produtos where codigo='" + codigo + "'");
            result = st.executeQuery();
            while (result.next()) {
                String codp = result.getString("codigo");
                String desc = result.getString("descricion");
                int precio = result.getInt("prezo");
                System.out.println(codp + "\t" + desc + "  \t" + precio);
            }
            System.out.println("Filas atopada con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        disconnect();

    }

    public void borrafila(String codigo) {
        connect();

        try {

            Statement stm = conn.createStatement();

            String delete = "DELETE from produtos where codigo='" + codigo + "';";
            stm.executeUpdate(delete);
            conn.commit();

            System.out.println("Filas borradas con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        disconnect();
    }
}
