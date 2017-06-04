package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzableException;
import cat.iespaucasesnoves.facturacio.Factura;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class Empresa extends Client {

    private HashMap<Integer, Factura> factures;
    private TerminiPagament metodePagament;
    private String formaPagament;//iban o targeta
    private String banc;
    //IBAN
    private String iban;
    //TAGRETA
    private String numTarg;
    private int any;
    private int mes;

    //constructor per crear empresa amb pagament IBAN.
    public Empresa(String nom, String identificadorEmpresa, TerminiPagament metodePagament, String formaPagament, String banc, String iban) {
        super(identificadorEmpresa, nom);
        this.metodePagament = metodePagament;
        this.formaPagament = formaPagament;
        this.banc = banc;
        this.iban = iban;
        this.mes = mes;
        factures = new HashMap<>();
    }

    //constructor per crear empresa amb pagament TARGETA.
    public Empresa(String nom, String identificadorEmpresa, TerminiPagament metodePagament, String formaPagament, String banc, String numTarg, int any, int mes) {
        super(identificadorEmpresa, nom);
        this.metodePagament = metodePagament;
        this.formaPagament = formaPagament;
        this.banc = banc;
        this.numTarg = numTarg;
        this.any = any;
        this.mes = mes;
        factures = new HashMap<>();
    }

    @Override
    public double calcularFacturacio() {
        double totalFacturat = 0;
        for (Factura factura : factures.values()) {
            totalFacturat = totalFacturat + factura.getTotal();
        }
        return totalFacturat;
    }

    public double calcularFacturacioMensual() {
        double totalFacturat = 0;
        LocalDate dataActual = LocalDate.now();//2017-6-02
        int mesActual = dataActual.getMonthValue();
        int anyActual = dataActual.getYear();
        for (Factura factura : factures.values()) {
            if (factura.getData().getMonthValue() == mesActual && factura.getData().getYear() == anyActual) {
                totalFacturat = totalFacturat + factura.getTotal();
            }
        }
        return totalFacturat;
    }

    public ArrayList<Factura> getFactures() {
        ArrayList<Factura> copia = new ArrayList<>();
        for (Factura factura : factures.values()) {
            copia.add(factura);
        }
        return copia;
    }

    public void afegirFactura(Factura fe) {
        factures.put(fe.getCodiFactura(), fe);
    }

    public void setFormaPagament(String formaPagament) throws AccioNoRealitzableException {
        if (!"iban".equals(formaPagament) || !"targeta".equals(formaPagament)) {
            throw new AccioNoRealitzableException("La forma de pagament només pot ser targeta o iban.");
        } else {
            this.formaPagament = formaPagament;
        }
    }

    public void setBanc(String banc) {
        this.banc = banc;
    }

    @Override
    public String toString() {
        return "Empresa : \t{" + super.toString() + ", totalFactures = " + factures.size() + ", facturacio = " + calcularFacturacio() + " Euros }";
    }

    public TerminiPagament getMetodePagament() {
        return metodePagament;
    }

    public String getFormaPagament() {
        return formaPagament;
    }

    public String getBanc() {
        return banc;
    }

    public String getIban() {
        return iban;
    }

    public String getNumTarg() {
        return numTarg;
    }

    public int getAny() {
        return any;
    }

    public int getMes() {
        return mes;
    }

}
