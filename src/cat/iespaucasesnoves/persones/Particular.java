package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.FacturaParticular;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class Particular extends Client {

    HashMap<Integer, FacturaParticular> factures = new HashMap<>();

    @Override
    public double calcularFactura() {
        double totalFactures = 0;
        for (FacturaParticular factura : factures.values()) {
            totalFactures = totalFactures + factura.getTotal();
        }
        return totalFactures;
    }
    
        public ArrayList<FacturaParticular> getFactures() {
        ArrayList<FacturaParticular> copia = new ArrayList<>();
        for (FacturaParticular factura : factures.values()) {
            copia.add(factura);
        }
        return copia;
    }
    
    public void afegirFactura(FacturaParticular fp){
        factures.put(fp.getCodiFactura(), fp);
    }
}
