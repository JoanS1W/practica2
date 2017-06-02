package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzable;
import cat.iespaucasesnoves.excepcions.ExcepcioPagada;
import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import cat.iespaucasesnoves.facturacio.FacturaParticular;
import cat.iespaucasesnoves.facturacio.Jugueta;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import cat.iespaucasesnoves.persones.*;
import cat.iespaucasesnoves.swpro.streams.EinesBufferedStreams;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;

public class Aplicacio implements Serializable {

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

    public File crearXml() throws IOException {
        File arxiu = new File("arxiu.xml");
        FileWriter fileWriter = new FileWriter(arxiu);

        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        fileWriter.write("<Empleats>\n");

        for (EmpleatVendes empleat : empleatsVendes.values()) {
            fileWriter.write("\t<EmpleatVendes>\n");
            fileWriter.write("\t\t<identificador>" + empleat.getIdentificador() + "</identificador>\n");
            fileWriter.write("\t\t<nom>" + empleat.getNomComplet() + "</nom>\n");
            fileWriter.write("\t\t<nomina>" + empleat.calcularNomina() + "</nomina>\n");
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

    public void crearXmlMensual() {

    }

    /* metodes per crear factures *///TODO tots els metodes haurien de retornar un String informant de l'accio, com es que he fet a crearFacturaParticular
    public void crearFacturaEmpresa(int codiEmpleat, String identificadorClient, int producte, int quantitat, double preuUnitari,
            int descompte, String banc, TerminiPagament pagament, String codiPais, String numCompte)
            throws AccioNoRealitzable {

        /*
		 * L'empleat és de VENDES i el client a qui li volem fer la factura és
		 * una EMPRESA?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(identificadorClient)) {
            /* cream la factura amb IBAN */
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte,
                    banc, pagament, codiPais, numCompte);
            // associam la factura al client.
            Empresa empresa = empreses.get(identificadorClient);
            empresa.afegirFactura(facturaNova);
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void crearFacturaEmpresa(int codiEmpleat, String identificadorClient, int producte, int quantitat, double preuUnitari,
            int descompte, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat,
            Year anyCaducitat) throws AccioNoRealitzable {

        /*
		 * L'empleat és de VENDES i el client a qui li volem fer la factura és
		 * una EMPRESA?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(identificadorClient)) {
            /* cream la factura amb TARGETA */
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, banc,
                    pagament, numTargeta, mesCaducitat, anyCaducitat);
            // associam la factura al client.
            Empresa empresa = empreses.get(identificadorClient);
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

    public String crearFacturaParticular(int codiEmpleat, String indetificadorClient, int producte, int quantitat, double preuUnitari,
            int descompte) throws AccioNoRealitzable {

        /*
		 * L'empleat és GENERAL i el client a qui li volem fer la factura és
		 * un PARTICULAR?
         */
        if (empleatsGenerals.containsKey(codiEmpleat) && particulars.containsKey(indetificadorClient)) {
            /* cream la factura amb TARGETA */
            EmpleatGeneral empleat = empleatsGenerals.get(codiEmpleat);
            FacturaParticular facturaNova = empleat.facturaParticular(producte, quantitat, preuUnitari, descompte);
            // associam el que ha pagat al client, per tal de poder tenir una facturacio total a la nostra aplicacio.
            particulars.get(indetificadorClient).afegirImport(facturaNova.getImportPagat());
        } else if (!empleatsGenerals.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzable("Empleat sense capacitat de realitzar la factura");
        } else {
            throw new AccioNoRealitzable("Aquest client no disposa d'aquest format de factura.");
        }
        return "Factura executada correctament.";
    }

    //ha de ser empresa
    public void mostrarFacturesClient(int client) {

    }

    //ha de ser empleat de vendes
    public void mostrarFacturesEmpleat(int codiEmpleat) {

    }

    public double calcularNominaEmpleat(int codiEmpleat) throws AccioNoRealitzable {
        Empleat empleat = cercarEmpleat(codiEmpleat);
        if (empleat != null) {
            return empleat.calcularNomina();
        } else {
            throw new AccioNoRealitzable("Empleat inexistenx.");
        }
    }

    //NOUS EMPLEATS JA FUNCIONEN
    public void nouEmpleatVendes(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double comissio) throws AccioNoRealitzable {
        EmpleatVendes empleat = new EmpleatVendes(nomComplet, identificador, email, telefon, direccio, categoria, salariBase, comissio);
        for (Empleat emp : empleatsVendes.values()) {
            if (emp.equals(empleat)) {
                throw new AccioNoRealitzable("Ja existeix aquest empleat.");
            }
        }
        for (Empleat emp : empleatsGenerals.values()) {
            if (emp.equals(empleat)) {
                throw new AccioNoRealitzable("Ja existeix aquest empleat.");
            }
        }
        empleatsVendes.put(empleat.getCodi(), empleat);
    }

    public void nouEmpleatGeneral(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double horesExtres, double preuHora) throws AccioNoRealitzable {
        EmpleatGeneral empleat = new EmpleatGeneral(nomComplet, identificador, email, telefon, direccio, categoria, salariBase, horesExtres, preuHora);
        for (Empleat emp : empleatsGenerals.values()) {
            if (emp.equals(empleat)) {
                throw new AccioNoRealitzable("Ja existeix aquest empleat.");
            }
        }
        for (Empleat emp : empleatsVendes.values()) {
            if (emp.equals(empleat)) {
                throw new AccioNoRealitzable("Ja existeix aquest empleat.");
            }
        }
        empleatsGenerals.put(empleat.getCodi(), empleat);
    }

    public void nouClientEmpresa() {

    }

    public void nouClientParticular() {

    }

    public void llistarMajorFacturacio() {

    }

    public void llistaFacturacioParametritzada() {

    }

    public double calcularFacturacio(String identificadorClient) throws AccioNoRealitzable {
        //cercam si exiteix un client amb aquest identificador.
        Client client = cercarClient(identificadorClient);
        if (client != null) {
            return client.calcularFacturacio();
        }
//        if (client instanceof Empresa) {
//            return client.calcularFacturacio();
//        } else if (client instanceof Particular) {
//
//            return client.calcularFacturacio();
//        } 
        else {
            throw new AccioNoRealitzable("El client no existeix al sistema");
        }

    }

    //metodes de cerca de l'objecte ja que desde la aplicacio lo normal sera executar els metodes amb els codis identificatius.
    public Client cercarClient(String identificadorClient) {
        for (Empresa empresa : empreses.values()) {
            if (empresa.getIdentificador().equals(identificadorClient)) {
                return empresa;
            }
        }
        for (Particular particular : particulars.values()) {
            if (particular.getIdentificador().equals(identificadorClient)) {
                return particular;
            }
        }
        return null;
    }

    public Empleat cercarEmpleat(int codiEmpleat) {
        for (EmpleatVendes ev : empleatsVendes.values()) {
            if (ev.getCodi() == codiEmpleat) {
                return ev;
            }
        }

        for (EmpleatGeneral eg : empleatsGenerals.values()) {
            if (eg.getCodi() == codiEmpleat) {
                return eg;
            }
        }
        return null;
    }
}
