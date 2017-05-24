package cat.iespaucasesnoves.persones;

/**
 *
 * @author alumne
 */
public class EmpleatGeneral extends Empleat{
    
    private double horesExtres;
    private double preuHora;

    public EmpleatGeneral(int codi, String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double horesExtres, double preuHora ) {
        super(codi, nomComplet, identificador, email, telefon, direccio, categoria, salariBase);
        this.horesExtres = horesExtres;
        this.preuHora = preuHora;
    }

    @Override
    double calcularNomina() {
        return getSalariBase() + (horesExtres*preuHora);
    }
    
    /*getters*/

    public double getHoresExtres() {
        return horesExtres;
    }

    public double getPreuHora() {
        return preuHora;
    }
    
}
