package baserelacionalb;

/**
 *
 * @author oracle
 */
public class Product {

    private String codigo, descricion;
    private Double prezo;

    public Product(String codigo, String descripcion, Double prezo) {
        this.codigo = codigo;
        this.descricion = descripcion;
        this.prezo = prezo;
    }

    public Product() {
        codigo = null;
        descricion = null;
        prezo = 0.0;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descricion = descripcion;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descricion;
    }

    public Double getPrezo() {
        return prezo;
    }

    @Override
    public String toString() {
        return "Product{" + "codigo=" + getCodigo() + ", descripcion=" + getDescripcion() + ", prezo=" + getPrezo() + '}';
    }

}
