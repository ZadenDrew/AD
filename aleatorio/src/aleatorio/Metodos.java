package aleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author oracle
 */
public class Metodos {

    Product p;

    public Metodos() {

    }

    public void escribeRandom(RandomAccessFile raw, String[] cod, String[] desc, int[] prezo) throws IOException {
        for (int i = 0; i <=0; i++) {
            raw.writeChars(String.format("%-3s", cod[i]).replace(" ", "*"));
            raw.writeChars(String.format("%-10s", desc[i]).replace(" ", "*"));
            raw.writeInt(prezo[i]);
        }

    }

    public void leerRandom(RandomAccessFile raw) throws IOException {
        String c = "";
        String d = "";
        int reg = Integer.parseInt(JOptionPane.showInputDialog("Nº de registro:(1-3)"));
        raw.seek((reg - 1) * 30);
        for (int i = 0; i < 13; i++) {
            if (i < 3) {
                c += raw.readChar();
            } else {
                d += raw.readChar();

            }
        }
        p = new Product(c.replace("*", ""), d.replace("*", ""), raw.readInt());
        String codigo = p.getCodigo();
        String descripcion = p.getDescripcion();
        int prezo = p.getPrezo();
        System.out.println(codigo);
        System.out.println(descripcion);
        System.out.println(prezo);
        
        
        
        
    }
}
