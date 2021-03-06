package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzableException;
import cat.iespaucasesnoves.excepcions.DataIncorrecteException;
import cat.iespaucasesnoves.excepcions.ExcepcioPagadaException;
import cat.iespaucasesnoves.excepcions.ValorNegatiuException;
import cat.iespaucasesnoves.facturacio.Factura;
import cat.iespaucasesnoves.facturacio.Jugueta;
import cat.iespaucasesnoves.facturacio.TerminiPagament;
import cat.iespaucasesnoves.persones.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Aplicacio implements Serializable {

    private HashMap<Integer, EmpleatVendes> empleatsVendes;
    private HashMap<Integer, EmpleatGeneral> empleatsGenerals;
    private HashMap<String, Empresa> empreses;
    private HashMap<String, Particular> particulars;
    private HashMap<Integer, Jugueta> juguetes;
    private int numFactura;

    public Aplicacio() {
        empleatsVendes = new HashMap<>();
        empleatsGenerals = new HashMap<>();
        empreses = new HashMap<>();
        particulars = new HashMap<>();
        juguetes = new HashMap<>();
        numFactura = 1;
    }

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

    public void afegirEmpleatVendes(EmpleatVendes empleat) {
        this.empleatsVendes.put(empleat.getCodi(), empleat);
    }

    public void afegirEmpleatGeneral(EmpleatGeneral empleat) {
        this.empleatsGenerals.put(empleat.getCodi(), empleat);
    }

    public void afegirEmpresa(Empresa empresa) {
        this.empreses.put(empresa.getIdentificador(), empresa);
    }

    public void afegirParticular(Particular particular) {
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

        for (Empleat empleat : empleatsVendes.values()) {
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

    public void crearXmlFacturacioMensual() throws IOException {
        File arxiu = new File("facturacioMensual.xml");
        FileWriter fileWriter = new FileWriter(arxiu);

        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        fileWriter.write("<Empreses>\n");
        for (Empresa e : empreses.values()) {
            fileWriter.write("\t<Empresa>\n");
            fileWriter.write("\t\t<identificador>" + e.getIdentificador() + "</identificador>\n");
            fileWriter.write("\t\t<facturacio>" + e.calcularFacturacioMensual() + "</facturacio>\n");
            fileWriter.write("\t\t<Pagament>\n");
            fileWriter.write("\t\t\t<descripcio>" + e.getFormaPagament() + "</descripcio>\n");
            if (e.getFormaPagament().equalsIgnoreCase("IBAN")) {
                fileWriter.write("\t\t\t<iban>" + e.getIban() + "</iban>\n");
            } else {
                fileWriter.write("\t\t\t<targeta>" + e.getNumTarg() + "</targeta>\n");
                fileWriter.write("\t\t\t<mes>" + e.getMes() + "</mes>\n");
                fileWriter.write("\t\t\t<any>" + e.getAny() + "</any>\n");
            }
            fileWriter.write("\t\t<Pagament>\n");
            fileWriter.write("\t</Empresa>\n");
        }
        fileWriter.write("</Empreses>");
        fileWriter.close();
    }

    public Factura crearFactura(int codiEmpleat, String identificadorClient, int producte, int quantitat, double preuUnitari,
            double descompte) throws AccioNoRealitzableException, ExcepcioPagadaException {
        //Cercam el client a qui li volem fer la factura i l'empleat que l'executa.
        Client client = cercarClient(identificadorClient);
        Empleat empleat = cercarEmpleat(codiEmpleat);

        //si es una empresa i un empleat de vendes realitzam la factura per la empresa.
        if ((client instanceof Empresa) && (empleat instanceof EmpleatVendes)) {
            Factura facturaEmpresa = ((EmpleatVendes) empleat).crearFacturaEmpresa(numFactura, producte, quantitat, preuUnitari, descompte);
            numFactura++;
            //miram metodePagament de la empresa i si es diari posar pagada i guardarla.
            if ((((Empresa) client).getMetodePagament()).equals(TerminiPagament.DIARI)) {
                facturaEmpresa.setPagada(true);
            }
            ((Empresa) client).afegirFactura(facturaEmpresa);
            return facturaEmpresa;

        } else if ((client instanceof Particular) && (empleat instanceof EmpleatGeneral)) {
            //cream factura i la cobram, faria la funcio de ticket
            Factura ticketParticular = ((EmpleatGeneral) empleat).crearFacturaParticular(numFactura, producte, quantitat, preuUnitari, descompte);
            numFactura++;
            //afegim el total al client per dur un recompte del que ens ha anat gastant.
            ((Particular) client).afegirImport(ticketParticular.getTotal());
            ticketParticular.setPagada(true);
            return ticketParticular;
        } else {
            throw new AccioNoRealitzableException("No s'ha pogut realitzar la factura.");
        }
    }

    public void afegirProducteFacturaEmpresa(int codiEmpleat, int codiFactura, int producte, int quantitat,
            double preuUnitari) throws ExcepcioPagadaException, AccioNoRealitzableException {
        /*
		 * L'empleat és de VENDES i la JUGUETA
		 * existeix al nostre sistema?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && juguetes.containsKey(producte)) {
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            // Nomes l'empleat que ha creat la factura li podra afegir
            // productes, sino retorna excepcio que tractarem a Proves.
            empleat.afegirLiniaFacturaEmpresa(codiFactura, producte, quantitat, preuUnitari);
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            throw new AccioNoRealitzableException("Codi empleat incorrecte o sense permisos per realitzar l'accio. ");
        } else {
            throw new AccioNoRealitzableException("Producte inexistent. ");
        }

    }

    public void modificaFacturaEmpresa(int codiEmpleat, int codiFactura, int linia, int producte, int quantitat,
            double preuUnitari) throws ExcepcioPagadaException, AccioNoRealitzableException {
        /*
		 * L'empleat és de VENDES, el client és una EMPRESA i la JUGUETA
		 * existeix al nostre sistema?
         */
        if (empleatsVendes.containsKey(codiEmpleat) && juguetes.containsKey(producte)) {
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            // Nomes l'empleat que ha creat la factura li podra afegir,el control es fa dins empleat.
            empleat.modificarLiniaFacturaEmpresa(codiFactura, linia, producte, quantitat, preuUnitari);
        } else {
            throw new AccioNoRealitzableException("Empleat i/o jugueta inexistents.");
        }
    }

    public double calcularNominaEmpleat(int codiEmpleat) throws AccioNoRealitzableException {
        Empleat empleat = cercarEmpleat(codiEmpleat);
        if (empleat != null) {
            return empleat.calcularNomina();
        } else {
            throw new AccioNoRealitzableException("Empleat inexistenx.");
        }
    }

    public void nouEmpleatVendes(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double comissio) throws AccioNoRealitzableException {
        EmpleatVendes empleat = new EmpleatVendes(nomComplet, identificador, email, telefon, direccio, categoria, salariBase, comissio);
        for (Empleat emp : empleatsVendes.values()) {
            if (emp.equals(empleat)) {
                empleat = null;
                throw new AccioNoRealitzableException("Ja existeix aquest empleat.");
            }
        }
        for (Empleat emp : empleatsGenerals.values()) {
            if (emp.equals(empleat)) {
                empleat = null;
                throw new AccioNoRealitzableException("Ja existeix aquest empleat.");
            }
        }
        empleatsVendes.put(empleat.getCodi(), empleat);
    }

    public void nouEmpleatGeneral(String nomComplet, String identificador, String email, String telefon, String direccio, CategoriaEmpleat categoria, double salariBase, double horesExtres, double preuHora) throws AccioNoRealitzableException {
        EmpleatGeneral empleat = new EmpleatGeneral(nomComplet, identificador, email, telefon, direccio, categoria, salariBase, horesExtres, preuHora);
        for (Empleat emp : empleatsGenerals.values()) {
            if (emp.equals(empleat)) {
                empleat = null;
                throw new AccioNoRealitzableException("Ja existeix aquest empleat.");
            }
        }
        for (Empleat emp : empleatsVendes.values()) {
            if (emp.equals(empleat)) {
                empleat = null;
                throw new AccioNoRealitzableException("Ja existeix aquest empleat.");
            }
        }
        empleatsGenerals.put(empleat.getCodi(), empleat);
    }

    public void nouClientEmpresa(String identificadorEmpresa, String nom, TerminiPagament metodePagament, String formaPagament, String banc, String iban) throws AccioNoRealitzableException {
        Empresa client = new Empresa(identificadorEmpresa, nom, metodePagament, formaPagament, banc, iban);
        for (Client cli : empreses.values()) {
            if (cli.equals(client)) {
                client = null;
                throw new AccioNoRealitzableException("Ja existeix aquest client.");
            }
        }
        for (Client cli : particulars.values()) {
            if (cli.equals(client)) {
                client = null;
                throw new AccioNoRealitzableException("Ja existeix aquest client.");
            }
        }
        empreses.put(client.getIdentificador(), client);
    }

    public void nouClientEmpresa(String identificadorEmpresa, String nom, TerminiPagament metodePagament, String formaPagament, String banc, String numTarg, int any, int mes) throws AccioNoRealitzableException, DataIncorrecteException {
        Empresa client = new Empresa(identificadorEmpresa, nom, metodePagament, formaPagament, banc, numTarg, any, mes);
        LocalDate data = LocalDate.now();

        if (any < data.getYear() || mes < 0 || mes > 12) {
            throw new DataIncorrecteException("Les dades any o mes de la targeta són incorrectes.");
        } else {
            for (Client cli : empreses.values()) {
                if (cli.equals(client)) {
                    client = null;
                    throw new AccioNoRealitzableException("Ja existeix aquest client.");
                }
            }
            for (Client cli : particulars.values()) {
                if (cli.equals(client)) {
                    client = null;
                    throw new AccioNoRealitzableException("Ja existeix aquest client.");
                }
            }
            empreses.put(client.getIdentificador(), client);
        }
    }

    public void nouClientParticular(String identificador, String nom) throws AccioNoRealitzableException {
        Particular client = new Particular(identificador, nom);
        for (Client cli : particulars.values()) {
            if (cli.equals(client)) {
                client = null;
                throw new AccioNoRealitzableException("Ja existeix aquest client.");
            }
        }
        for (Client cli : empreses.values()) {
            if (cli.equals(client)) {
                client = null;
                throw new AccioNoRealitzableException("Ja existeix aquest client.");
            }
        }
        particulars.put(client.getIdentificador(), client);
    }

    public ArrayList<Client> llistarMajorFacturacio() {
        ArrayList<Client> llistaFinal = new ArrayList<>();
        ArrayList<Empresa> llistaEmpreses = getEmpreses();
        ArrayList<Particular> llistaParticulars = getParticulars();
        //ordenam
        Collections.sort(llistaEmpreses);
        Collections.sort(llistaParticulars);
        //Afegim el 3 majors de cada llista
        for (int i = 0; i < 3; i++) {
            llistaFinal.add(llistaEmpreses.get(i));
            llistaFinal.add(llistaParticulars.get(i));
        }
        Collections.sort(llistaFinal);

        return llistaFinal;
    }

    public ArrayList<Client> llistaFacturacioParametritzada(double limit) throws ValorNegatiuException {
        ArrayList<Client> llista = new ArrayList<>();
        for (Empresa client : empreses.values()) {
            if (limit < 0) {
                throw new ValorNegatiuException("El limit es negatiu.");
            } else if (client.calcularFacturacio() > limit) {
                llista.add(client);
            }
        }
        for (Particular client : particulars.values()) {
            if (limit < 0) {
                throw new ValorNegatiuException("El limit es negatiu.");
            } else if (client.calcularFacturacio() > limit) {
                llista.add(client);
            }
        }
        return llista;
    }

    public double calcularFacturacio(String identificadorClient) throws AccioNoRealitzableException {
        //cercam si exiteix un client amb aquest identificador.
        Client client = cercarClient(identificadorClient);
        if (client != null) {
            return client.calcularFacturacio();
        } else {
            throw new AccioNoRealitzableException("El client no existeix al sistema");
        }

    }

    public double calcularFacturacioMensualEmpresa(String identificadorClient) throws AccioNoRealitzableException {
        Empresa empresa = empreses.get(identificadorClient);
        //calculam la seva facturacio mensual
        if (empresa == null) {
            return empresa.calcularFacturacioMensual();
        } else {
            throw new AccioNoRealitzableException("Empresa inexistent al sistema.");
        }
    }

    public void cobramentDeFactures() throws ExcepcioPagadaException {
        //En acabar les proves aquesta data ha de ser l'actual ( .now() )
        LocalDate data = LocalDate.parse("2017-06-29");
        if (data.getDayOfWeek().getValue() == 6) {
            //cobram emprese semanals
            for (Empresa empresa : empreses.values()) {
                if ((empresa.getMetodePagament()).equals(TerminiPagament.SEMANAL)) {
                    for (Factura factura : empresa.getFactures()) {
                        if (factura.getData().getDayOfWeek().getValue() < 6) {
                            factura.setPagada(true);
                        }
                    }
                }
            }
        }
        if (data.getDayOfMonth() == 30) {
            //cobram mensuals
            for (Empresa empresa : empreses.values()) {
                if ((empresa.getMetodePagament()).equals(TerminiPagament.MENSUAL)) {
                    for (Factura factura : empresa.getFactures()) {
                        if (factura.getData().getMonthValue() < 31) {
                            factura.setPagada(true);
                        }
                    }
                }
            }
        }
        if (data.getDayOfYear() % 90 == 0) {
            //cobram trimestrals
            int primerMes = data.minusMonths(2).getMonthValue();
            int segonMesMes = data.minusMonths(1).getMonthValue();
            int tercerMes = data.getMonthValue();
            for (Empresa empresa : empreses.values()) {
                if ((empresa.getMetodePagament()).equals(TerminiPagament.TRIMESTRAL)) {
                    for (Factura factura : empresa.getFactures()) {
                        if ((factura.getData().getMonthValue() == primerMes) | (factura.getData().getMonthValue() == segonMesMes) | (factura.getData().getMonthValue() == tercerMes)) {
                            factura.setPagada(true);
                        }
                    }
                }
            }
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
