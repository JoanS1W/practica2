package cat.iespaucasesnoves.facturacio;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

/**
 *
 * @author alumne
 */
public class FacturaEmpresa extends Factura {
    //Fer getters i setters amb if pagada?
    private TerminiPagament pagament;
    private String banc;
    private String metodeDePagament;
    /*IBAN*/
    private String codiPais;
    private int digitControl;
    private String numCompte;
    /*TARGETA*/
    private String numTargeta;
    private Month mesCaducitat;
    private Year anyCaducitat;
    /*CLIENT??????????*/
//    private int codiClient;

    /**
     * Tendrem dos constructors en sobrecarrega per la factura d'empresa,
     * un per les que tenen IBAN i l'altra per TARGETES.
     */
    public FacturaEmpresa(int producte, int quantitat, double preuUnitari, int descompte, String banc, TerminiPagament pagament, String codiPais, String numCompte) {
        super(producte, quantitat, preuUnitari, descompte);
        this.pagament = pagament;
        this.codiPais = codiPais;
        this.numCompte = numCompte;
        this.banc = banc;
        this.metodeDePagament = "IBAN";

    }

    public FacturaEmpresa(int producte, int quantitat, double preuUnitari, int descompte, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducitat) {
        super(producte, quantitat, preuUnitari, descompte);
        this.pagament = pagament;
        this.numTargeta = numTargeta;
        this.mesCaducitat = mesCaducitat;
        this.anyCaducitat = anyCaducitat;
        this.banc = banc;
        this.metodeDePagament = "TARGETA";

    }

    @Override
    public String toString() {
        return super.toString()+ "pagament=" + pagament + ", banc=" + banc + ", metodeDePagament=" + metodeDePagament + ", codiPais=" + codiPais + ", digitControl=" + digitControl + ", numCompte=" + numCompte + ", numTargeta=" + numTargeta + ", mesCaducitat=" + mesCaducitat + ", anyCaducitat=" + anyCaducitat + '}';
    }

    
}
