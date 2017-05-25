package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import java.time.LocalDate;
import java.util.HashSet;

/**
 *
 * @author alumne
 */
public class EmpleatVendes extends Empleat{
    
    private double comissio;
    private HashSet<FacturaEmpresa> vendes;

    public EmpleatVendes(double comissio, int codi, String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase) {
        super(codi, nomComplet, identificador, email, telefon, direccio, categoria, salariBase);
        this.comissio = comissio;
        /*inicialitzam el set on es guardaran totes les seves vendes*/
        vendes = new HashSet<FacturaEmpresa>();
    }

    @Override
    public double calcularNomina() {
        return getSalariBase() + comissio;
    }
    
    /*metodes per crear factura a empresa amb TARGETA o IBAN*/
    public void crearFactura(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc,  TerminiPagament pagament, String codiPais, String numCompte){
    
    }
    public void crearFactura(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, LocalDate mesCaducitat, LocalDate anyCaducitat){
        
    }
    /*getters*/

    public double getComissio() {
        return comissio;
    }

    public HashSet<FacturaEmpresa> getVendes() {
        return vendes;
    }
    
    
    
}
