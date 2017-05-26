package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
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
        for (HashMap.Entry<Integer, FacturaEmpresa> entry : factures.entrySet()) {
            FacturaEmpresa tab = entry.getValue();
            totalFactures = totalFactures + tab.getTotal();
        }
        return totalFactures;
    }
}
