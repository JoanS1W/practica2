package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import cat.iespaucasesnoves.swpro.streams.auxiliar.EinesObjectesStream;

/**
 *
 * @author Miguel, Joan, Toni Xavier
 */
public class Proves {


    /*metode on cream dades y guardam dins JSON*/
    public void copiarDadesInicialsFitxer(String uri) {

       
        //4 empleats vendes
        EmpleatVendes ev1 = new EmpleatVendes("Miguel", "dni", "@gmail.com", "123 456 789", "carrer", CategoriaEmpleat.TECNIC, 2000, 20);
        EmpleatVendes ev2 = new EmpleatVendes("Joan", "nif", "@gmail.com", "123 456 789", "gran via", CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev3 = new EmpleatVendes("Toni Xavier", "nie", "@gmail.com", "123 456 789", "carrero", CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev4 = new EmpleatVendes("Pep", "dni", "@gmail.com", "123 456 789", "avenida", CategoriaEmpleat.DIRECTIU, 3000, 30);
        //
        //3 empleats generals        
        EmpleatGeneral eg1 = new EmpleatGeneral("M.A", "dni", "@gmail.com", "123 456 789", "carrer", CategoriaEmpleat.DIRECTIU, 3000, 30, 0);
        EmpleatGeneral eg2 = new EmpleatGeneral("J.S", "nif", "@gmail.com", "123 456 789", "gran via", CategoriaEmpleat.TECNIC, 2000, 20, 0);
        EmpleatGeneral eg3 = new EmpleatGeneral("T.X", "nie", "@gmail.com", "123 456 789", "carrer", CategoriaEmpleat.AUXILIAR, 1000, 10, 0);

        EinesObjectesStream.escriuObjecte(uri, novaAplicacio);

    }

    public void carregarDadesInicials(String uri) {


    }


    /*metodes per crear factures */
    public void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte,  String banc, TerminiPagament pagament, String codiPais, String numCompte) {

        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (app.get.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /*cream la factura amb IBAN*/
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, LocalDate.now(), banc, pagament, codiPais, numCompte);
            /*TODO ficar la facturaNova dins els registre de factures del client, client.add(facturaNova)*/
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            System.out.println("No tens permisos per executar aquesta factura.");
        } else {
            System.out.println("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducitat) {

        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /*cream la factura amb IBAN*/
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            FacturaEmpresa facturaNova = empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, numTargeta, mesCaducitat, anyCaducitat);
            /*TODO ficar la facturaNova dins els registre de factures del client, client.add(facturaNova)*/
        } else if (!empleatsVendes.containsKey(codiEmpleat)) {
            System.out.println("No tens permisos per executar aquesta factura.");
        } else {
            System.out.println("Aquest client no disposa d'aquest format de factura.");
        }

    }

    public void modificarFactura() {

    }

    public void crearNominaJson() {

    }

    public static void main(String[] args) {
        
        Aplicacio app = new Aplicacio();
        
        

    }

}
