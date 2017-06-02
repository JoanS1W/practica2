package cat.iespaucasesnoves.facturacio;

import java.io.Serializable;

/**
 *
 * @author alumne
 */
public class Jugueta implements Serializable{

	int codi;
	String nom;

	public Jugueta(int codi, String nom) {
		this.codi = codi;
		this.nom = nom;
	}

	public int getCodi() {
		return codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
