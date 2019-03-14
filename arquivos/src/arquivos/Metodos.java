package arquivos;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {

    public static File arquivosdir;
    File arquivo, subdir, arquivo2;

    public Metodos() {
    }

    public void crear() throws IOException {
        arquivosdir = new File("/home/oracle/NetBeansProjects/arquivos/arquivosdir");
        arquivosdir.mkdir();
        arquivo = new File(arquivosdir, "Products1.txt");
        arquivo.createNewFile();
        System.out.println(arquivo.getAbsoluteFile());
        subdir = new File("/home/oracle/NetBeansProjects/arquivos/arquivosdir/subdir");
        subdir.mkdir();
        arquivo2 = new File(subdir, "Products2.txt");
        arquivo2.createNewFile();
    }

    public void mostrar() {

        System.out.println(arquivo.getName());
        System.out.println(subdir.getName());
        System.out.println(arquivosdir.getPath());

        //        try {
        //
        //            String[] lista = arquivosdir.list();
        //
        //            // for each name in the path array
        //            for (int i = 0; i < lista.length; i++) {
        //
        //                // prints filename and directory name
        //                System.out.println(lista[i]);
        //            }
        //
        //        } catch (Exception e) {
        //            // if any error occurs
        //            e.printStackTrace();
        //        }
    }

    public void informacion() {
        String nome, ruta, lectura, escritura = null;
        System.out.println("nome -> \t" + arquivo.getName());
        System.out.println("ruta -> \t" + arquivo.getPath());
        if (arquivo.canWrite()) {
            escritura = "Sí";
        } else {
            escritura = "No";
        }
        System.out.println("¿Pódese escribir nel? -> \t" + escritura);
        if (arquivo.canRead()) {
            lectura = "Sí";
        } else {
            lectura = "No";
        }
        System.out.println("¿Pódese ler nel? -> \t" + lectura);
        System.out.println("Lonxitude en bytes -> \t" + arquivo.length() + "\tbytes");
    }
    /*
     8) forzar a que o mesmo arquivo referido no apartado anterior   sexa de so lectura
     comprobar dende o sistema operativo que no se pode escribir na da en dito arquivo
    
     cd NetBeansProjects/arquivos/arquivosdir/arquivo
     ls -l
     -r--r--r-- 1 oracle dba    4 Sep 27 11:08 Products1.txt

     */

    public void permisoler() {
        String ler = null;
        if (arquivo.setReadOnly()) {
            ler = "Sí";

        } else {
            ler = "No";
        }
        System.out.println("¿Ficheiro de só lectura ? -> \t" + ler);
    }
    /*
     9)forzar a que o arquivo referido no apartado anterior pase de novo a ser de novo de  escritura
     comprobar dendo o sistema operativo que se pode ler do arquivo
     cd NetBeansProjects/arquivos/arquivosdir/arquivo
     ls -l
     -rw-r--r-- 1 oracle dba    4 Sep 27 11:08 Products1.txt

     */

    public void permisoescribe() {
        String escribir = null;
        if (arquivo.setWritable(true)) {
            escribir = "Sí";

        } else {
            escribir = "No";
        }
        System.out.println("¿Ficheiro de só escritura ? -> \t" + escribir);
    }

    /*
     10)borrar o arquivo referido no apartado anterior
     comprobar dende o sistema operativo que o arquivo foi borrado
     cd NetBeansProjects/arquivos/arquivosdir/arquivo
     ls 
     Products1.txt~ subdir
     */
    public void borrarFicheiro() {
        arquivo.deleteOnExit();
        if (arquivo.delete()) {
            System.out.println("El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero no pudó ser borrado");
        }
    }
    /*
     11)borrar os o resto de arquivos e directorios creados anteriormente 
    
     */

    public  void borrarTodo(File x) {

        try {
            File[] lista = x.listFiles();
            for (int i = 0; i < lista.length; i++) {
                if (lista[i].isDirectory()) {
                    borrarTodo(lista[i]);
                    System.out.println("" + lista[i]);
                }
                lista[i].delete();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        if (arquivosdir.delete()) {
            System.out.println("El fichero " + arquivosdir + "ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero " + arquivosdir + " no pudó ser borrado");
        }
        if (subdir.delete()) {
            System.out.println("El fichero " + subdir + "ha sido borrado satisfactoriamente");
        } else {
            System.out.println("El fichero" + arquivosdir + " no pudó ser borrado");
        }
    }

}
