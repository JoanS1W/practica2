package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzableException;
import cat.iespaucasesnoves.excepcions.ExcepcioPagadaException;
import cat.iespaucasesnoves.excepcions.ValorNegatiuException;
import cat.iespaucasesnoves.facturacio.Factura;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class EmpleatVendes extends Empleat {

    private double comissio;
    private HashMap<Integer, Factura> vendes;

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
    public ArrayList<Factura> getVendes() {
        ArrayList<Factura> copia = new ArrayList<>();
        for (Factura facturaEmpresa : vendes.values()) {
            copia.add(facturaEmpresa);
        }
        return copia;
    }

    @Override
    public double calcularNomina() {
        return getSalariBase() + comissio;
    }

    /*metodes per crear i gestionar factura per l'empleat*/
    public Factura crearFacturaEmpresa(int codiFactura, int producte, int quantitat, double preuUnitari, double descompte) {
        //cream factura
        Factura novaFactura = new Factura(codiFactura, producte, quantitat, preuUnitari, descompte);
        //ficam dins la llista de vendes de l'empleat
        vendes.put(novaFactura.getCodiFactura(), novaFactura);
        //retornam factura per tal de guardar-la dins l'empresa*/
        return novaFactura;
    }

    public void afegirLiniaFacturaEmpresa(int codiFactura, int producte, int quantitat, double preuUnitari) throws ExcepcioPagadaException, AccioNoRealitzableException {

        if (vendes.containsKey(codiFactura)) {
            Factura factura = vendes.get(codiFactura);
            //aquest metode torna excepcio de factura no pagada que propagam fins que les tractem totes al main.
            factura.afegirLiniaFactura(producte, quantitat, preuUnitari);
        } else {
            throw new AccioNoRealitzableException("Factura no disponible.");
        }

    }

    public void modificarLiniaFacturaEmpresa(int codiFactura, int numeroLinia, int producte, int quantitat, double preuUnitari) throws ExcepcioPagadaException, AccioNoRealitzableException {

        if (vendes.containsKey(codiFactura)) {
            Factura factura = vendes.get(codiFactura);
            //aquest metode torna excepcio de factura no pagada que propagam fins que les tractem totes al main.
            factura.modificarLiniaFactura(numeroLinia, producte, quantitat, preuUnitari);
        } else {
            throw new AccioNoRealitzableException("Factura no disponible.");
        }
    }

    public void setComissio(double comissio) throws ValorNegatiuException {
        if (comissio < 0) {
            throw new ValorNegatiuException("El valor de la comissio es negatiu.");
        } else {
            this.comissio = comissio;
        }
    }

    @Override
    public String toString() {
        return "EmpleatVendes{" + super.toString() + " ,comissio=" + comissio + ",\n vendes=" + vendes.values() + '}';
    }

}
