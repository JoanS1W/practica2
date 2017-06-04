package cat.iespaucasesnoves.persones;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author alumne
 */
public abstract class Client implements Serializable, Comparable<Client> {

    String identificador;
    String nom;

    public Client(String identificador, String nom) {
        this.identificador = identificador;
        this.nom = nom;
    }

    public abstract double calcularFacturacio();

    public String getIdentificador() {
        return identificador;
    }

    public String getNom() {
        return nom;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "identificador = " + identificador + ", nom = " + nom;
    }

    @Override
    public int compareTo(Client o) {
        return (int) (o.calcularFacturacio() - calcularFacturacio());
    }

    ; 

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return true;
    }
 

}
