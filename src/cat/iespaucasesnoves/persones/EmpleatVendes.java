package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
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
    
    /*TODO metode crear factura i guardar-la al hashset*/
    
    /*getters*/

    public double getComissio() {
        return comissio;
    }

    public HashSet<FacturaEmpresa> getVendes() {
        return vendes;
    }
    
    
    
}
