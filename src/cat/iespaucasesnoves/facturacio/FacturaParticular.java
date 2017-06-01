package cat.iespaucasesnoves.facturacio;


public class FacturaParticular extends Factura {

    private double importPagat;

    public FacturaParticular(int producte, int quantitat, double preuUnitari, int descompte, double importPagat) {
        super(producte, quantitat, preuUnitari, descompte);
        this.importPagat = importPagat;
    }

    public double getImportPagat() {
        return importPagat;
    }

    //TODO si pagada no modificable
    public void setImportPagat(double importPagat) {
        this.importPagat = importPagat;
    }
    
    
}
