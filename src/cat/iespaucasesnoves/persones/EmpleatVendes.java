package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzable;
import cat.iespaucasesnoves.excepcions.ExcepcioPagada;
import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class EmpleatVendes extends Empleat {

    private double comissio;
    private HashMap<Integer, FacturaEmpresa> vendes;

    public EmpleatVendes(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double comissio) {
        super(nomComplet, identificador, email, telefon, direccio, categoria, salariBase);
        this.comissio = comissio;
        /*inicialitzam el set on es guardaran totes les seves vendes*/
        vendes = new HashMap<>();
    }

    /*getters*/
    public double getComissio() {
        return comissio;
    }

    //Cream una copia per a no passar la referencia del nostre mapa
    public ArrayList<FacturaEmpresa> getVendes() {
        ArrayList<FacturaEmpresa> facturesEmpleat = new ArrayList<>();
        for (HashMap.Entry<Integer, FacturaEmpresa> entry : vendes.entrySet()) {
            facturesEmpleat.add(entry.getValue());
        }
        return facturesEmpleat;
    }

    @Override
    public double calcularNomina() {
        return getSalariBase() + comissio;
    }

    /*metodes per crear i gestionar factura per l'empleat*/
    public FacturaEmpresa facturaEmpresa(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String codiPais, String numCompte) {

        FacturaEmpresa novaFactura = new FacturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, codiPais, numCompte);
        /*insertam la factura dins el llistat de vendes de l'empleat*/
        vendes.put(novaFactura.getCodiFactura(), novaFactura);
        /*retornam factura per tal de guardar-la dins el client*/
        return novaFactura;

    }

    public FacturaEmpresa facturaEmpresa(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducita) {

        FacturaEmpresa novaFactura = new FacturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, numTargeta, mesCaducitat, anyCaducita);
        /*insertam la factura dins el llistat de vendes de l'empleat*/
        vendes.put(novaFactura.getCodiFactura(), novaFactura);
        /*retornam factura per tal de guardar-la dins el client*/
        return novaFactura;
    }

    public void afegirLiniaFactura(int codiFactura, int producte, int quantitat, double preuUnitari) throws ExcepcioPagada, AccioNoRealitzable {

        if (vendes.containsKey(codiFactura)) {
            FacturaEmpresa factura = vendes.get(codiFactura);
            //aquest metode torna excepcio de factura no pagada que propagam fins que les tractem totes al main.
            factura.afegirLiniaFactura(producte, quantitat, preuUnitari);
        } else {
            throw new AccioNoRealitzable("Factura no disponible.");
        }

    }

    public void modificarFactura(int codiFactura, int numeroLinia, int producte, int quantitat, double preuUnitari) throws ExcepcioPagada, AccioNoRealitzable {

        if (vendes.containsKey(codiFactura)) {
            FacturaEmpresa factura = vendes.get(codiFactura);
            //aquest metode torna excepcio de factura no pagada que propagam fins que les tractem totes al main.
            factura.afegirLiniaFactura(producte, quantitat, preuUnitari);
        } else {
            throw new AccioNoRealitzable("Factura no disponible.");
        }
    }
}
