package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.excepcions.ValorNegatiuException;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author alumne
 */
public abstract class Empleat implements Serializable {

    private static int numeroEmpleat = 1;
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
    public abstract double calcularNomina();

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

    public void setSalariBase(double salariBase) throws ValorNegatiuException {
        if (salariBase < 0) {
            throw new ValorNegatiuException("El valor del salari base es negatiu.");
        } else {
            this.salariBase = salariBase;
        }
    }

    @Override
    public String toString() {
        return "Empleat{" + "codi=" + codi + ", nomComplet=" + nomComplet + ", identificador=" + identificador + ", email=" + email + ", telefon=" + telefon + ", direccio=" + direccio + ", categoria=" + categoria + ", salariBase=" + salariBase + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleat other = (Empleat) obj;
        if (!Objects.equals(this.nomComplet, other.nomComplet)) {
            return false;
        }
        if (!Objects.equals(this.identificador, other.identificador)) {
            return false;
        }
        return true;
    }
}
