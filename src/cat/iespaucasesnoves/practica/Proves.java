package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.Client;
import cat.iespaucasesnoves.persones.Empleat;
import cat.iespaucasesnoves.persones.EmpleatVendes;
import cat.iespaucasesnoves.persones.Empresa;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;

/**
 *
 * @author Miguel, Joan, Toni Xavier
 */
public class Proves {

    private static HashMap<Integer,Client> clients = new HashMap<>();
    private static HashMap<Integer,Empleat> empleats = new HashMap<>();
    /*metodes per crear factures
    *
    *
    */

    public static void crearFactura(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc,  TerminiPagament pagament, String codiPais, String numCompte){
    
        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleats.get(codiEmpleat) instanceof EmpleatVendes && clients.get(codiClient) instanceof Empresa) {
            /*cream la factura amb IBAN*/
            FacturaEmpresa novaFactura = new FacturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, codiPais, numCompte);
            /*ficam la factura dins l'empleat d evendes que l'ha efectuada*/
        }else{
            System.out.println("No pots crear la factura");
        }
    }
    public static void crearFactura(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducitat){
        
         /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleats.get(codiEmpleat) instanceof EmpleatVendes && clients.get(codiClient) instanceof Empresa) {
            /*cream factura amb TARGETA*/
            FacturaEmpresa novaFactura = new FacturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, numTargeta, mesCaducitat, anyCaducitat);
        }
    }
    public static void main(String[] args) {

    }
    

}
