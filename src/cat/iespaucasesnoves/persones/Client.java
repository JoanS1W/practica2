package cat.iespaucasesnoves.persones;

import java.io.Serializable;



/**
 *
 * @author alumne
 */
public abstract class Client implements Serializable {

    String identificador;
    String nom;

    public Client(String nom ,String identificador) {
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
        return "identificador = " + identificador + ", nom = " + nom ;
    }
    
    
}
