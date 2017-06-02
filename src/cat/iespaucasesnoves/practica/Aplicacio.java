package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzable;
import cat.iespaucasesnoves.excepcions.ExcepcioPagada;
import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.FacturaParticular;
import cat.iespaucasesnoves.facturacio.Jugueta;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import cat.iespaucasesnoves.persones.Empleat;
import cat.iespaucasesnoves.persones.EmpleatGeneral;
import cat.iespaucasesnoves.persones.EmpleatVendes;
import cat.iespaucasesnoves.persones.Empresa;
import cat.iespaucasesnoves.persones.Particular;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectStreamClass;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;


public class Aplicacio {

    private HashMap<Integer, EmpleatVendes> empleatsVendes = new HashMap<>();
    private HashMap<Integer, EmpleatGeneral> empleatsGenerals = new HashMap<>();
    private HashMap<String, Empresa> empreses = new HashMap<>();
    private HashMap<String, Particular> particulars = new HashMap<>();
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
        this.empreses.put(empresa.getIdentificador(), empresa);
    }

    public void afegirParticulars(Particular particular) {
        this.particulars.put(particular.getIdentificador(), particular);
    }

    public void afegirJuguetes(Jugueta jugueta) {
        this.juguetes.put(jugueta.getCodi(), jugueta);
    }

    public void afegirJuguetes(HashMap<Integer, Jugueta> juguetes) {
        this.juguetes = juguetes;
    }

    public File crearXML() throws IOException {
        File arxiu = new File("arxiu.xml");
        FileWriter fileWriter = new FileWriter(arxiu);     
  
        
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        fileWriter.write("<Empleats>\n");
        
        for (EmpleatVendes empleat : empleatsVendes.values()) {
            fileWriter.write("\t<EmpleatVendes>\n");
            fileWriter.write("\t\t<identificador>"+empleat.getIdentificador()+"</identificador>\n");
            fileWriter.write("\t\t<nom>"+empleat.getNomComplet()+"</nom>\n");
            fileWriter.write("\t\t<nomina>"+empleat.calcularNomina()+"</nomina>\n");
            fileWriter.write("\t</EmpleatVendes>\n");
        }

        for (EmpleatGeneral empleat : empleatsGenerals.values()) {
            fileWriter.write("\t<EmpleatGeneral>\n");
            fileWriter.write("\t\t<identificador>" + empleat.getIdentificador() + "</identificador>\n");
            fileWriter.write("\t\t<nom>" + empleat.getNomComplet() + "</nom>\n");
            fileWriter.write("\t\t<dni>" + empleat.calcularNomina() + "</dni>\n");
            fileWriter.write("\t</EmpleatVendes>\n");
        }
        fileWriter.write("</Empleats>");
        fileWriter.close();
        
        return arxiu;

    }

    /* metodes per crear factures */
    public void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari,
            int descompte, String banc, TerminiPagament pagament, String codiPais, String numCompte)
            throws AccioNoRealitzable {

        /*
		 * L'empleat és de VENDES i el client a qui li volem fer la factura és
		 * una EMPRESA?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /* cream la factura amb IBAN */
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte,
                    LocalDate.now(), banc, pagament, codiPais, numCompte);
            // associam la factura al client.
            Empresa empresa = empreses.get(codiClient);
            empresa.afegirFactura(facturaNova);
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari,
            int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat,
            Year anyCaducitat) throws AccioNoRealitzable {

        /*
		 * L'empleat és de VENDES i el client a qui li volem fer la factura és
		 * una EMPRESA?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /* cream la factura amb TARGETA */
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc,
                    pagament, numTargeta, mesCaducitat, anyCaducitat);
            // associam la factura al client.
            Empresa empresa = empreses.get(codiClient);
            empresa.afegirFactura(facturaNova);
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void afegirProducteFacturaEmpresa(int codiEmpleat, int codiFactura, int producte, int quantitat,
            double preuUnitari) throws ExcepcioPagada, AccioNoRealitzable {
        /*
		 * L'empleat és de VENDES, el client és una EMPRESA i la JUGUETA
		 * existeix al nostre sistema?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && juguetes.containsKey(producte)) {
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            // Nomes l'empleat que ha creat la factura li podra afegir
            // productes, sino retorna excepcio que tractarem a Proves.
            empleat.afegirLiniaFacturaEmpresa(codiFactura, producte, quantitat, preuUnitari);
        }
    }

    public void modificaFacturaEmpresa(int codiEmpleat, int codiFactura, int linia, int producte, int quantitat,
            double preuUnitari) throws ExcepcioPagada, AccioNoRealitzable {
        /*
		 * L'empleat és de VENDES, el client és una EMPRESA i la JUGUETA
		 * existeix al nostre sistema?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && juguetes.containsKey(producte)) {
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            // Nomes l'empleat que ha creat la factura li podra afegir
            // productes, sino retorna excepcio que tractarem a Proves.
            empleat.modificarLiniaFacturaEmpresa(codiFactura, linia, producte, quantitat, preuUnitari);
        }
    }

    public void crearFacturaParticular(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari,
            int descompte, double importPagat) throws AccioNoRealitzable {

        /*
		 * L'empleat és GENERAL i el client a qui li volem fer la factura és
		 * un PARTICULAR?
         */
        if (empleatsGenerals.containsKey(codiEmpleat) && particulars.containsKey(codiClient)) {
            /* cream la factura amb TARGETA */
            EmpleatGeneral empleat = empleatsGenerals.get(codiEmpleat);
            FacturaParticular facturaNova = empleat.facturaParticular(producte, quantitat, preuUnitari, descompte,
                    importPagat);
            // associam el que ha pagat al client, per tal de poder tenir una facturacio total a la nostra aplicacio.
            particulars.get(codiClient).afegirImport(facturaNova.getImportPagat());
        } else if (!empleatsGenerals.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }
    }

    public void mostrarFacturesClient(int client) {

    }

    public void mostrarFacturesEmpleat(int codiEmpleat) {

    }

    public double calcularNominaEmpleat(Empleat e) throws AccioNoRealitzable{
        if (e instanceof EmpleatVendes) {
            return ((EmpleatVendes) e).calcularNomina();
        } else if (e instanceof EmpleatGeneral) {
            return ((EmpleatVendes) e).calcularNomina();
        } else {
            throw new AccioNoRealitzable("Empleat inexistenx.");            
        }
    }
    
    public void nouEmpleatVendes(){
        
    }
    
        public void nouEmpleatGeneral(){
        
    }
            public void nouClientEmpresa(){
        
    }
                public void nouClientParticular(){
        
    }
                    public void llistarTresClientsMajorFacturacio(){
        
    }
}
