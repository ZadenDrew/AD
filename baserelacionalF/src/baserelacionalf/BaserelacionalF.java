package baserelacionalf;

import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class BaserelacionalF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        BaseDatos bd = new BaseDatos();
        bd.chamaProcedure();
    }

}
