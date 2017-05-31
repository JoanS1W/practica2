/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzable;
import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.FacturaParticular;
import cat.iespaucasesnoves.facturacio.Jugueta;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import cat.iespaucasesnoves.persones.EmpleatGeneral;
import cat.iespaucasesnoves.persones.EmpleatVendes;
import cat.iespaucasesnoves.persones.Empresa;
import cat.iespaucasesnoves.persones.Particular;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
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

    /*metodes per crear factures */
    public void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, String banc, TerminiPagament pagament, String codiPais, String numCompte) throws AccioNoRealitzable {

        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /*cream la factura amb IBAN*/
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, LocalDate.now(), banc, pagament, codiPais, numCompte);
            //associam la factura al client.
            Empresa empresa = empreses.get(codiClient);
            empresa.afegirFactura(facturaNova);
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducitat) throws AccioNoRealitzable {

        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /*cream la factura amb TARGETA*/
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, numTargeta, mesCaducitat, anyCaducitat);
            //associam la factura al client.
            Empresa empresa = empreses.get(codiClient);
            empresa.afegirFactura(facturaNova);
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void crearFacturaParticular(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, double importPagat)throws AccioNoRealitzable{
        
        /*L'empleat és GENERAL i el client a qui li volem fer la factura és un PARTICULAR?*/
        if (empleatsGenerals.containsKey(codiEmpleat) && particulars.containsKey(codiClient)) {
            /*cream la factura amb TARGETA*/
            EmpleatGeneral empleat = empleatsGenerals.get(codiEmpleat);
            FacturaParticular facturaNova = empleat.facturaParticular(producte, quantitat, preuUnitari, descompte, importPagat);
            //associam la factura al client.
            Particular particular = particulars.get(codiClient);
            particular.afegirFactura(facturaNova);
        } else if (!empleatsGenerals.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }
    }


}
