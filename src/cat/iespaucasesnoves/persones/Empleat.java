package cat.iespaucasesnoves.persones;


/**
 *
 * @author alumne
 */
public abstract class Empleat {
    
    private static int numeroEmpleat = 0;
    private final int codi;
    private String nomComplet;
    private String identificador;
    private String email;
    private String telefon;
    private String direccio;
    private CategoriaEmpleat categoria;
    private double salariBase;

    public Empleat(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase) {
        codi = numeroEmpleat++;
        this.nomComplet = nomComplet;
        this.identificador = identificador;
        this.email = email;
        this.telefon = telefon;
        this.direccio = direccio;
        this.categoria = categoria;
        this.salariBase = salariBase;
    }
    
    /*metodes abstractes que hauran d'implementar les clases heretades*/
    abstract double calcularNomina();
    
    /*getters de tots atributs menys del codi que es final*/

    public int getCodi() {
        return codi;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getDireccio() {
        return direccio;
    }

    public CategoriaEmpleat getCategoria() {
        return categoria;
    }

    public double getSalariBase() {
        return salariBase;
    }
    
    /*setters*/

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public void setCategoria(CategoriaEmpleat categoria) {
        this.categoria = categoria;
    }

    public void setSalariBase(double salariBase) {
        this.salariBase = salariBase;
    }
    
    
    
}
