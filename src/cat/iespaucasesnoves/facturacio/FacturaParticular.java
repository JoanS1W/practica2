package cat.iespaucasesnoves.facturacio;

import cat.iespaucasesnoves.excepcions.ExcepcioPagada;

public class FacturaParticular extends Factura {

    private double importPagat;

    public FacturaParticular(int producte, int quantitat, double preuUnitari, int descompte) {
        super(producte, quantitat, preuUnitari, descompte);
        this.importPagat = quantitat*preuUnitari;
    }

    public double getImportPagat() {
        return importPagat;
    }

    //TODO si pagada no modificable FET, mirar si es correcte.
    public void setImportPagat(double importPagat) throws ExcepcioPagada {
        if (isPagada()) {
            throw new ExcepcioPagada("Factura PAGADA no modificable");
        } else {
            this.importPagat = importPagat;
        }

    }

}
