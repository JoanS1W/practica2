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

    public String getIdentificador() {
        return identificador;
    }

    public String getNom() {
        return nom;
    }

    public abstract double calcularFactura();
}
