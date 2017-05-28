/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author PCcasa
 */
public class ElementsJSON {

    private String nom;
    private String identificador;
    private double totalNomina;

    public ElementsJSON(String nom, String identificador, double totalNomina) {
        this.nom = nom;
        this.identificador = identificador;
        this.totalNomina = totalNomina;
    }

    public String getNom() {
        return nom;
    }

    public String getIdentificador() {
        return identificador;
    }

    public double getTotalNomina() {
        return totalNomina;
    }

}
