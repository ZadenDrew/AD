package textodelimitado;

/**
 *
 * @author oracle
 */
public class Product {

    private String codigo, descripcion;
    private Double prezo;

    public Product(String codigo, String descripcion, Double prezo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.prezo = prezo;
    }

    public Product() {
        codigo = null;
        descripcion = null;
        prezo = 0.0;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrezo() {
        return prezo;
    }

    @Override
    public String toString() {
        return "Product{" + "codigo=" + getCodigo() + ", descripcion=" + getDescripcion() + ", prezo=" + getPrezo() + '}';
    }

}
