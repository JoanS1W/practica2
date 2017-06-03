package cat.iespaucasesnoves.persones;

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
    public Factura crearFacturaParticular(int producte, int quantitat, double preuUnitari, double descompte){
        //cream factura
        Factura novaFactura = new Factura(producte, quantitat, preuUnitari, descompte);
        //retornam factura per tal de guardar-la dins l'empresa*/
        return novaFactura;
    }
    
}
