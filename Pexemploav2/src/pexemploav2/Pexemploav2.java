package pexemploav2;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.bson.Document;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author oracle
 */
public class Pexemploav2 {
//ORACLE

    public static String driver = "jdbc:oracle:thin:";
    public static String host = "localhost.localdomain";
    public static String porto = "1521";
    public static String sid = "orcl";
    public static String usuario = "hr";
    public static String password = "hr";
    public static String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
    //para conectar co native protocal all java driver: creamos un obxecto Connection usando o metodo getConnection da clase  DriverManager            
    public static Connection conn;
    public static PreparedStatement st;

    //EXIST
    public static String driverex = "org.exist.xmldb.DatabaseImpl";
    // col  = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/cousas", "admin", "oracle");

    public static String colecion = "/db";
    public static String recursos = "/db/cousas";
    public static String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
    public static Collection col, rec;

    //MONGO
    public static MongoClient mogoCliente;
    public static MongoDatabase base;
    public static MongoCollection<Document> coleccion;
    public static MongoCursor<Document> itera;

    public static int cinf;
    public static String dniinf;

    public static void main(String[] args) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //MONGO
        MongoClient mongoCliente = new MongoClient("localhost", 27017);
        base = mongoCliente.getDatabase("test");
        coleccion = base.getCollection("empretodos");

        //OBJECTDB
        //Creamos una nueva base de datos o conectamos
        EntityManagerFactory emanagerf
                = Persistence.createEntityManagerFactory("$objectdb/db/horasextratodos.odb");
        EntityManager emanager = emanagerf.createEntityManager();

        Class<?> cl = Class.forName(driverex);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(uri + colecion, "admin", "oracle");
        rec = DatabaseManager.getCollection(uri + recursos, "admin", "oracle");

        File arquivo = new File("/home/oracle/Desktop/final.xml");
        String recurso = "final.xml";

        Resource novoRecurso = rec.createResource(arquivo.getName(), "XMLResource");
        novoRecurso.setContent(arquivo);
        rec.storeResource(novoRecurso);

        connect();
        try {

            ResultSet result;

            st = conn.prepareStatement("SELECT * FROM informaticos");
            result = st.executeQuery();
            while (result.next()) {
                cinf = result.getInt("cinf");
                java.sql.Struct fillos = (java.sql.Struct) result.getObject(3);
                Object[] tipo_fillos = fillos.getAttributes();
                BigDecimal filloHome = (BigDecimal) tipo_fillos[0];
                BigDecimal filloMuller = (BigDecimal) tipo_fillos[1];
                int numHome = filloHome.intValue();
                int numMuller = filloMuller.intValue();
                if (numMuller + numHome > 0) {
                    dniinf = result.getString("dniinf");
                    System.out.println("CINF:" + cinf + "\tDNI: " + dniinf + "  \tNumero fillos:" + numHome + "   \tNumero fillas:" + numMuller);
                    int nh = numHome + numMuller;

                    BasicDBObject gtQuery = new BasicDBObject();
                    gtQuery.put("dnie", dniinf);
                    Document campo = coleccion.find(gtQuery).first();
                    double sb = campo.getDouble("sb");
                    double phe = campo.getDouble("phe");
                    String che = campo.getString("che");

                    System.out.println("sb-> " + sb + "\tphe-> " + phe + "\tche-> " + che + "\n");

//                    // Consulta lista de Horasextra objects de la database:
                    TypedQuery<Horasextra> consulta
                            = emanager.createQuery("SELECT nhe FROM Horasextra h where che='" + che + "'", Horasextra.class);
                    List<Horasextra> resultados = consulta.getResultList();
                    for (int i = 0; i < resultados.size(); i++) {
                        int nhe = consulta.getFirstResult();
                        System.out.println(resultados.get(i));
                        double salarioTotal = sb + phe * nhe + nh * 100;
                        System.out.println(salarioTotal);

                        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
                        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
                        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
                        ResourceSet resultado = servicio.query("update insert <empleados>\n"
                                + "    <cinf>" + cinf + "</cinf>\n"
                                + "    <salarioTotal>" + salarioTotal + "</salarioTotal>\n"
                                + "  </empleados> into //informaticos");
                    }
                }

            }
            System.out.println("La tabla se muestra con éxito.");

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }

        disconnect();
    }

    public static void connect() {
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

    public static void disconnect() {
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

}
