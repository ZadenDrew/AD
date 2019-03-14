package exad2;

import java.io.Serializable;

/**
 *
 * @author oracle
 */
public class Platos implements Serializable {

    private String codp;
    private String nomep;
    private int graxa;

    public Platos() {
        this("", "", 0);
    }

    public Platos(String codp, String nomep, int graxa) {
        this.codp = codp;
        this.nomep = nomep;
        this.graxa = graxa;
    }

    public String getCodp() {
        return codp;
    }

    public void setCodp(String codp) {
        this.codp = codp;
    }

    public String getNomep() {
        return nomep;
    }

    public void setNomep(String nomep) {
        this.nomep = nomep;
    }

    public int getGraxa() {
        return graxa;
    }

    public void setGraxa(int graxa) {
        this.graxa = graxa;
    }

    @Override
    public String toString() {
        return "Platos codigo plato=" + codp + ", nome plato=" + nomep + ", graxa=" + graxa;
    }

}
