package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class EmpleatVendes extends Empleat{
    
    private double comissio;
    private HashMap<Integer,FacturaEmpresa> vendes;

    public EmpleatVendes(int codi, String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double comissio) {
        super(codi, nomComplet, identificador, email, telefon, direccio, categoria, salariBase);
        this.comissio = comissio;
        /*inicialitzam el set on es guardaran totes les seves vendes*/
        vendes = new HashMap<>();
    }

    @Override
    public double calcularNomina() {
        return getSalariBase() + comissio;
    }
    
    /*metodes per crear i gestionar factura per l'empleat*/

        public void facturaEmpresa(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc,  TerminiPagament pagament, String codiPais, String numCompte){
                    
        FacturaEmpresa novaFactura = new FacturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, codiPais, numCompte);
        /*insertam la factura dins el llistat de vendes de l'empleat*/
        vendes.put(novaFactura.getCodiFactura(), novaFactura);

    }
        public void facturaEmpresa(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducita){
                    
        FacturaEmpresa novaFactura = new FacturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, numTargeta, mesCaducitat, anyCaducita);
        /*insertam la factura dins el llistat de vendes de l'empleat*/
        vendes.put(novaFactura.getCodiFactura(), novaFactura);      
    }
    
        public void afegirLiniaFactura(int codiFactura, int producte, int quantitat, double preuUnitari){
            
            if (vendes.containsKey(codiFactura)) {
                FacturaEmpresa factura = vendes.get(codiFactura);
                factura.afegirLiniaFactura(producte, quantitat, preuUnitari);
            }
            else {
                //TODO missatge
            }
            
            
        }
        public void modificarFactura(int factura){
            
        }
    /*getters*/

    public double getComissio() {
        return comissio;
    }

    public HashMap<Integer,FacturaEmpresa> getVendes() {
        return vendes;
    }
    
}
