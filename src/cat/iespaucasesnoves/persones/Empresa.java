package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class Empresa extends Client {

    HashMap<Integer, FacturaEmpresa> factures = new HashMap<>();

    @Override
    public double calcularFactura() {
        double totalFactures = 0;
        for (FacturaEmpresa factura : factures.values()) {
            totalFactures = totalFactures + factura.getTotal();
        }
        return totalFactures;
    }

    public ArrayList<FacturaEmpresa> getFactures() {
        ArrayList<FacturaEmpresa> copia = new ArrayList<>();
        for (FacturaEmpresa factura : factures.values()) {
            copia.add(factura);
        }
        return copia;
    }
    
    public void afegirFactura(FacturaEmpresa fe){
        factures.put(fe.getCodiFactura(), fe);
    }
}
