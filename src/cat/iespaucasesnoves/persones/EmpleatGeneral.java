package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.excepcions.ValorNegatiuException;
import cat.iespaucasesnoves.facturacio.Factura;

/**
 *
 * @author alumne
 */
public class EmpleatGeneral extends Empleat {

    private double horesExtres;
    private double preuHora;

    public EmpleatGeneral(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double horesExtres, double preuHora) {
        super(nomComplet, identificador, email, telefon, direccio, categoria, salariBase);
        this.horesExtres = horesExtres;
        this.preuHora = preuHora;
    }

    @Override
    public double calcularNomina() {
        return getSalariBase() + (horesExtres * preuHora);
    }

    /*getters*/
    public double getHoresExtres() {
        return horesExtres;
    }

    public double getPreuHora() {
        return preuHora;
    }

    /*metodes per crear i gestionar factura per l'empleat general*/
    public Factura crearFacturaParticular(int codiFactura, int producte, int quantitat, double preuUnitari, double descompte) {
        //cream factura
        Factura novaFactura = new Factura(codiFactura, producte, quantitat, preuUnitari, descompte);
        //retornam factura per tal de guardar-la dins l'empresa*/
        return novaFactura;
    }

    public void setHoresExtres(double horesExtres) throws ValorNegatiuException {
        if (horesExtres < 0) {
            throw new ValorNegatiuException("El valor de les hores extra es negatiu.");
        } else {
            this.horesExtres = horesExtres;
        }
    }

    public void setPreuHora(double preuHora) throws ValorNegatiuException {
        if (preuHora < 0) {
            throw new ValorNegatiuException("El valor del preu per hora es negatiu.");
        } else {
            this.preuHora = preuHora;
        }
    }

    @Override
    public String toString() {
        return "EmpleatGeneral{" + "horesExtres=" + horesExtres + ", preuHora=" + preuHora + '}';
    }

}
