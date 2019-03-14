package conexits;

import static conexits.ConeXits.col;
import java.io.File;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

/**
 *
 * @author oracle
 */
public class MetodosExits {

    public void listarColecions(Collection col) throws XMLDBException {
        String[] colecion = col.listChildCollections();
        for (int i = 0; i < colecion.length; i++) {
            System.out.println(colecion[i]);

        }
    }

    public void listaRecursos(Collection rec) throws XMLDBException {
        String[] recursos = rec.listResources();
        for (int i = 0; i < recursos.length; i++) {
            System.out.println(recursos[i]);

        }
    }

    public void crearColeción(Collection col) throws XMLDBException {
        CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        mgtService.createCollection("cousas");

    }

    public void borraColecions(Collection col) throws XMLDBException {
        CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        mgtService.removeCollection("cousas");
    }

    public void subiRecurso(Collection rec, File arch) throws XMLDBException {

        //File arquivo = new File("/home/oracle/Desktop/empleados.xml");
        Resource novoRecurso = rec.createResource(arch.getName(), "XMLResource");
        novoRecurso.setContent(arch);
        rec.storeResource(novoRecurso);
    }

    public void borraRecurso(Collection rec, String recurso) throws XMLDBException {
        Resource recursoaborrar = rec.getResource(recurso);
        rec.removeResource(recursoaborrar);

    }

    public void amosarContidoRec(Collection col) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        ResourceSet resultado = servicio.query("/EMPLEADOS/EMP_ROW[DEPT_NO=10]");
        //construir un iterador para recorrer o contido do resultado devolto:
        ResourceIterator i = resultado.getIterator();
        boolean fin = true;
        //preguntar por fin de recurso:
        while (fin = i.hasMoreResources()) {
            // 	ler cada item do recurso: 
            Resource r = i.nextResource();
            //	imprimir item lido: 
            String lido = (String) r.getContent();
            System.out.println(lido);
        }
    }

    public void insireElemenRec(Collection rec) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) rec.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        String cons = "update insert <PERSONA>LOLA</PERSONA> into //PLAY/PERSONAE ";
        ResourceSet resultado = servicio.queryResource("proba.xml",cons);

    }

    public void actualizarElemenRec(Collection rec) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) rec.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        String cons = "update value //PLAY/fm/p/nome[. = \"Andrew\"] with 'LOLI'";
        ResourceSet resultado = servicio.queryResource("proba2.xml",cons);

    }

    public void borrarElemenRec(Collection rec) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) rec.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        String cons = "update delete //PLAY/PERSONAE/PERSONA[. = \"LOLA\"] ";
        ResourceSet resultado = servicio.queryResource("proba.xml",cons);

    }

    public void insireElementosRec(Collection col) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        ResourceSet resultado = servicio.query("update insert <PERSONA>LOLA</PERSONA> into //PLAY/PERSONAE ");
        ResourceSet resultado2 = servicio.query("update insert <EMP_ROW>\n"
                + "    <EMP_NO>8369</EMP_NO>\n"
                + "    <APELLIDO>GARCÍA</APELLIDO>\n"
                + "    <OFICIO>EMPLEADO</OFICIO>\n"
                + "    <DIR>7902</DIR>\n"
                + "    <FECHA_ALT>1985-12-21</FECHA_ALT>\n"
                + "    <SALARIO>1340</SALARIO>\n"
                + "    <DEPT_NO>40</DEPT_NO>\n"
                + "  </EMP_ROW> into //EMPLEADOS");
    }

    public void actualizarElementosRec(Collection col) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        ResourceSet resultado = servicio.query("update value //fm/p/nome[. = \"Andrew\"] with 'Ana'");
        ResourceSet resultado2 = servicio.query("update value //EMPLEADOS/EMP_ROW/APELLIDO[. = \"GARCÍA\"] with 'Andrew'");
    }

    public void borrarElementosRec(Collection col) throws XMLDBException {
        //    amosar contido do recurso da  colecion devolta :  (sendo -col- a coleción devolta )
        XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        //seleciona a partir de que nodo onde queremos que busque: e unha consulta de tipo xPath como por exemplo : 
        ResourceSet resultado = servicio.query("update delete //PLAY/PERSONAE/PERSONA[. = \"LOLA\"] ");
        ResourceSet resultado2 = servicio.query("update delete  //EMPLEADOS/EMP_ROW/EMP_NO[. = \"8369\"] ");
    }
}
