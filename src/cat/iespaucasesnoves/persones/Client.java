package cat.iespaucasesnoves.persones;



/**
 *
 * @author alumne
 */
public abstract class Client {

    String identificador;
    String nom;

    public Client(String identificador, String nom) {
        this.identificador = identificador;
        this.nom = nom;
    }    

    public abstract double calcularFacturacio();

    public String getIdentificador() {
        return identificador;
    }

    public String getNom() {
        return nom;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Client{" + "identificador=" + identificador + ", nom=" + nom + '}';
    }
    
    
}
