package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.Factura;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public abstract class Client {

    String identificador;
    String nom;
    //HashMap<Integer, Factura> factures = new HashMap<>();


    public abstract double calcularFactura();

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
        return "Client{" + "identificador=" + identificador + ", nom=" + nom + '}';
    }
    
    
}
