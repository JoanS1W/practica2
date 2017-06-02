package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaParticular;
import java.util.ArrayList;

/**
 *
 * @author alumne
 */
public class Particular extends Client {

    private ArrayList<Double> importsPagats;

    public Particular(String identificador, String nom) {
        super(identificador, nom);
        this.importsPagats = new ArrayList<>();
    }

    @Override
    public double calcularFacturacio() {
        double totalFacturat = 0;
        for (int i = 0; i < importsPagats.size(); i++) {
            totalFacturat = totalFacturat + importsPagats.get(i);
        }

        return totalFacturat;
    }

    public void afegirImport(double importPagat) {
        importsPagats.add(importPagat);
    }

    public ArrayList<Double> getImportsPagats() {
        ArrayList<Double> copia = new ArrayList<>();
        for (Double importPagat : importsPagats) {
            copia.add(importPagat);
        }
        return copia;
    }
}
