/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.facturacio.Jugueta;
import cat.iespaucasesnoves.persones.EmpleatGeneral;
import cat.iespaucasesnoves.persones.EmpleatVendes;
import cat.iespaucasesnoves.persones.Empresa;
import cat.iespaucasesnoves.persones.Particular;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author toni
 */
public class Aplicacio {
    
    private HashMap<Integer, EmpleatVendes> empleatsVendes = new HashMap<>();
    private HashMap<Integer, EmpleatGeneral> empleatsGenerals = new HashMap<>();
    private HashMap<Integer, Empresa> empreses = new HashMap<>();
    private HashMap<Integer, Particular> particulars = new HashMap<>();
    private HashMap<Integer, Jugueta> juguetes = new HashMap<>();
    

    public ArrayList<EmpleatVendes> getEmpleatsVendes() {
        ArrayList<EmpleatVendes> copia = new ArrayList<>();
        for (EmpleatVendes empleat : empleatsVendes.values()) {
            copia.add(empleat);
        }
        return copia;
    }

    //TODO lo de de adalt copies.
    public HashMap<Integer, EmpleatGeneral> getEmpleatsGenerals() {
        return empleatsGenerals;
    }

    public HashMap<Integer, Empresa> getEmpreses() {
        return empreses;
    }

    public HashMap<Integer, Particular> getParticulars() {
        return particulars;
    }

    public HashMap<Integer, Jugueta> getJuguetes() {
        return juguetes;
    }

    public void afegirEmpleatsVendes(EmpleatVendes empleat) {
        this.empleatsVendes.put(empleat.getCodi(), empleat);
    }

    //TODO 
    public void afegirEmpleatsGenerals(HashMap<Integer, EmpleatGeneral> empleatsGenerals) {
        this.empleatsGenerals = empleatsGenerals;
    }

    public void afegirEmpreses(HashMap<Integer, Empresa> empreses) {
        this.empreses = empreses;
    }

    public void afegirParticulars(HashMap<Integer, Particular> particulars) {
        this.particulars = particulars;
    }

    public void afegirJuguetes(HashMap<Integer, Jugueta> juguetes) {
        this.juguetes = juguetes;
    }

    
    
    
    
}
