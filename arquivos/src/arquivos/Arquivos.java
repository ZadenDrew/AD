package arquivos;

import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Arquivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
     
        Metodos met = new Metodos();
        met.crear();
        met.mostrar();
        met.informacion();
        met.permisoler();
        met.permisoescribe();
        met.borrarFicheiro();
        met.borrarTodo(Metodos.arquivosdir);
    }

}
