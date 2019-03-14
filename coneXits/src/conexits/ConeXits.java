package conexits;

import java.io.File;
import org.xmldb.api.base.Collection;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

/**
 *
 * @author oracle
 */
public class ConeXits {

    public static String driver = "org.exist.xmldb.DatabaseImpl";
    // col  = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/cousas", "admin", "oracle");

    public static String colecion = "/db";
    public static String recursos = "/db/cousas";
    public static String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
    public static Collection col, rec;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, XMLDBException, IllegalAccessException {
        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(uri + colecion, "admin", "oracle");
        rec = DatabaseManager.getCollection(uri + recursos, "admin", "oracle");

        File arquivo = new File("/home/oracle/Desktop/empleados.xml");
        String recurso = "empleados.xml";
        File arquivo2 = new File("/home/oracle/Desktop/proba.xml");
        String recurso2 = "proba.xml";
        File arquivo3 = new File("/home/oracle/Desktop/proba2.xml");
        String recurso3 = "proba2.xml";

        MetodosExits mx = new MetodosExits();
//        mx.listarColecions(col);
//        mx.listaRecursos(rec);
//        mx.crearColeción(col);
//        //mx.borraColecions(col);
//        mx.subiRecurso(rec, arquivo);
       // mx.borraRecurso(rec, recurso);
//        mx.amosarContidoRec(col);
//        mx.insireElemenRec(rec);
        mx.actualizarElemenRec(rec);
//        mx.borrarElemenRec(rec);
        //mx.insireElementosRec(col);
//        mx.actualizarElementosRec(col);
        col.close();
        rec.close();

    }

}
