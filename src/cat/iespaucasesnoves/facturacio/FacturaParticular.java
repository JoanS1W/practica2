package cat.iespaucasesnoves.facturacio;

import java.time.LocalDate;

public class FacturaParticular extends Factura {

    private int importPagat;

    public FacturaParticular(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data) {
        super(producte, quantitat, preuUnitari, descompte, data);
    }
}
