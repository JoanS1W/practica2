package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaParticular;
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
        for (HashMap.Entry<Integer, FacturaParticular> entry : factures.entrySet()) {
            FacturaParticular tab = entry.getValue();
            totalFactures = totalFactures + tab.getTotal();
        }
        for (FacturaParticular factura : factures.values()) {
            totalFactures = totalFactures + factura.getTotal();
        }
        return totalFactures;
    }
}
