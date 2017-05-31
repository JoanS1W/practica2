package cat.iespaucasesnoves.facturacio;

import java.time.LocalDate;

public class FacturaParticular extends Factura {

    private double importPagat;

    public FacturaParticular(int producte, int quantitat, double preuUnitari, int descompte, double importPagat) {
        super(producte, quantitat, preuUnitari, descompte);
        this.importPagat = importPagat;
    }
}
