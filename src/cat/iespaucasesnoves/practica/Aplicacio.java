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

    public ArrayList<EmpleatGeneral> getEmpleatsGenerals() {
        ArrayList<EmpleatGeneral> copia = new ArrayList<>();
        for (EmpleatGeneral empleat : empleatsGenerals.values()) {
            copia.add(empleat);
        }
        return copia;
    }

    public ArrayList<Empresa> getEmpreses() {
        ArrayList<Empresa> copia = new ArrayList<>();
        for (Empresa empresa : empreses.values()) {
            copia.add(empresa);
        }
        return copia;
    }

    public ArrayList<Particular> getParticulars() {
        ArrayList<Particular> copia = new ArrayList<>();
        for (Particular particular : particulars.values()) {
            copia.add(particular);
        }
        return copia;
    }

    public ArrayList<Jugueta> getJuguetes() {
        ArrayList<Jugueta> copia = new ArrayList<>();
        for (Jugueta jugueta : juguetes.values()) {
            copia.add(jugueta);
        }
        return copia;
    }

    public void afegirEmpleatsVendes(EmpleatVendes empleat) {
        this.empleatsVendes.put(empleat.getCodi(), empleat);
    }

    public void afegirEmpleatsGenerals(EmpleatGeneral empleat) {
        this.empleatsGenerals.put(empleat.getCodi(), empleat);
    }

    public void afegirEmpreses(Empresa empresa) {
        //this.empreses.put(empresa.getIdentificador(), empresa);
    }
    //AQUESTS DOS IDENTIFICADORS no poden ser strings si els hem de ficar com a
    //clau, o feim una clau int per ells o canviem l'identificador a int però no 
    //se si això podia ser dni... de totes formes podriem fer dni sense lletra, no se
    public void afegirParticulars(Particular particular) {
        //this.particulars.put(particular.getIdentificador(), particular);
    }

    public void afegirJuguetes(Jugueta jugueta) {
        this.juguetes.put(jugueta.getCodi(), jugueta);
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