package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;

/**
 *
 * @author Miguel, Joan, Toni Xavier
 */
public class Proves {

    private static HashMap<Integer,EmpleatVendes> empleatsVendes = new HashMap<>();
    private static HashMap<Integer,EmpleatGeneral> empleatsGenerals = new HashMap<>();
    private static HashMap<Integer,Empresa> empreses = new HashMap<>();
    private static HashMap<Integer,Particular> particulars = new HashMap<>();
    
    /*metodes per crear factures */

    public static void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc,  TerminiPagament pagament, String codiPais, String numCompte){
    
        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /*cream la factura amb IBAN*/
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, codiPais, numCompte);            
        }
        else if(!empleatsVendes.containsKey(codiEmpleat)){
            System.out.println("No tens permisos per executar aquesta factura.");
        }
        else{
            System.out.println("Aquest client no disposa d'aquest format de factura.");
        }
            
        
    }
    public static void crearFacturaEmpresa(int codiEmpleat, int codiClient, int producte, int quantitat, double preuUnitari, int descompte, LocalDate data, String banc, TerminiPagament pagament, String numTargeta, Month mesCaducitat, Year anyCaducitat){
        
        /*L'empleat és de VENDES i el client a qui li volem fer la factura és una EMPRESA?*/
        if (empleatsVendes.containsKey(codiEmpleat) && empreses.containsKey(codiClient)) {
            /*cream la factura amb IBAN*/
            EmpleatVendes empleat = empleatsVendes.get(codiEmpleat);
            empleat.facturaEmpresa(producte, quantitat, preuUnitari, descompte, data, banc, pagament, numTargeta, mesCaducitat, anyCaducitat);            
        }
        else if(!empleatsVendes.containsKey(codiEmpleat)){
            System.out.println("No tens permisos per executar aquesta factura.");
        }
        else{
            System.out.println("Aquest client no disposa d'aquest format de factura.");
        }
        
    }
    public static void modificarFactura(){
        
    }
    

    public static void main(String[] args) {
        

   
    }
    

}
