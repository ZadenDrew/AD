package tutorialobject;

/**
 *
 * @author oracle
 */
import javax.persistence.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Tutorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
        EntityManager em = emf.createEntityManager();

        //Creamos una nueva base de datos o conectamos
        EntityManagerFactory emanagerf
                = Persistence.createEntityManagerFactory("$objectdb/db/gatos.odb");
        EntityManager emanager = emanagerf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Guardamos 2 Gatos objects en la  database:
        emanager.getTransaction().begin();
        String j = JOptionPane.showInputDialog(null, "¿Cuántos objetos Gatos vas a introducir??: :");
        for (int i = 0; i < Integer.parseInt(j); i++) {
            Gatos g = new Gatos();
            String raza = JOptionPane.showInputDialog(null, "Introduce raza del gato :");
            String color = JOptionPane.showInputDialog(null, "Introduce el color del pelo :");
            String peso = JOptionPane.showInputDialog(null, "Introduce peso el peso del gatito :");
            g = new Gatos(raza, color, Integer.parseInt(peso));
            emanager.persist(g);

        }
        emanager.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query
                = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        // Consulta lista de Gatos objects de la database:
        TypedQuery<Gatos> consulta
                = emanager.createQuery("SELECT g FROM Gatos g", Gatos.class);
        List<Gatos> resultados = consulta.getResultList();
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println(resultados.get(i));
        }

        //Modificar datos base Gatos
        Gatos gati = emanager.find(Gatos.class, 1);
        emanager.getTransaction().begin();
        gati.setPeso(8);
        emanager.getTransaction().commit();

        //Modificacion masiva datos base Gatos
        emanager.getTransaction().begin();
        Query cons = emanager.createQuery("UPDATE Gatos g SET g.peso = 5,g.color='cualquiera'");
        int updateCons = cons.executeUpdate();
        emanager.getTransaction().commit();

        //Eliminación dun obxecto
        emanager.getTransaction().begin();
        Query cons2 = emanager.createQuery("DELETE FROM Gatos g WHERE g.raza= 'persa'");
        int deleteCons2 = cons2.executeUpdate();
        emanager.getTransaction().commit();

        // Close the database connection:
        em.close();
        emf.close();

        emanager.close();
        emanagerf.close();
    }

}
