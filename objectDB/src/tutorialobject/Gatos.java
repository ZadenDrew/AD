package tutorialobject;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author oracle
 */
@Entity
public class Gatos implements Serializable {

    @Id@GeneratedValue
    private int id;
    
    private String raza;
    private String color;
    private int peso;

    public Gatos() {
    }

    public Gatos(String raza, String color, int peso) {
        this.raza = raza;
        this.color = color;
        this.peso = peso;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ("Gatos -> " + "id=" + id + ", raza=" + raza + ", color=" + color + ", peso=" + peso );
    }

}
