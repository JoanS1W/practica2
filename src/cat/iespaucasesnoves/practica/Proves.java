package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzable;
import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.*;
import cat.iespaucasesnoves.swpro.streams.auxiliar.EinesObjectesStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Miguel, Joan, Toni Xavier
 */
public class Proves {

    /* metode on cream dades y guardam objecte Aplicacio dins fitxer */
    public static void copiarDadesInicialsFitxer(String uri) {
        
        Aplicacio app = new Aplicacio();
        // 4 empleats vendes.
        EmpleatVendes ev1 = new EmpleatVendes("Miguel", "dni", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.TECNIC, 2000, 20);
        EmpleatVendes ev2 = new EmpleatVendes("Joan", "nif", "@gmail.com", "123 456 789", "gran via",
                CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev3 = new EmpleatVendes("Toni Xavier", "nie", "@gmail.com", "123 456 789", "carrero",
                CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev4 = new EmpleatVendes("Pep", "dni", "@gmail.com", "123 456 789", "avenida",
                CategoriaEmpleat.DIRECTIU, 3000, 30);
        // afegim empleats de vendes a la app.
        app.afegirEmpleatsVendes(ev1);
        app.afegirEmpleatsVendes(ev2);
        app.afegirEmpleatsVendes(ev3);
        app.afegirEmpleatsVendes(ev4);
        // 3 empleats generals
        EmpleatGeneral eg5 = new EmpleatGeneral("M.A", "dni", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.DIRECTIU, 3000, 30, 10);
        EmpleatGeneral eg6 = new EmpleatGeneral("J.S", "nif", "@gmail.com", "123 456 789", "gran via",
                CategoriaEmpleat.TECNIC, 2000, 20, 20);
        EmpleatGeneral eg7 = new EmpleatGeneral("T.X", "nie", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.AUXILIAR, 1000, 10, 3);
        // afegim empleats generals a la app.
        app.afegirEmpleatsGenerals(eg5);
        app.afegirEmpleatsGenerals(eg6);
        app.afegirEmpleatsGenerals(eg7);
        /* TODO */
        // Empreses.
        Empresa emp1 = new Empresa("EMP1", "Empresa1", TerminiPagament.MENSUAL, "IBAN", "la caixa", "213453456FF" );
        Empresa emp2 = new Empresa("EMP2", "Empresa2", TerminiPagament.SEMANAL, "IBAN", "la caixa", "213453456FF" );
        Empresa emp3 = new Empresa("EMP3", "Empresa3", TerminiPagament.DIARI, "IBAN", "la caixa", "213453456FF" );
        Empresa emp4 = new Empresa("EMP4", "Empresa4", TerminiPagament.MENSUAL, "TARGETA", "la caixa", "98989898", 2018, 1 );
        Empresa emp5 = new Empresa("EMP5", "Empresa5", TerminiPagament.MENSUAL, "TARGETA", "sa nostra", "00000000", 2019, 5 );


        app.afegirEmpreses(emp1);
        app.afegirEmpreses(emp2);
        app.afegirEmpreses(emp3);
        app.afegirEmpreses(emp4);
        // Particulars.
        Particular par1 = new Particular("PAR1", "Particular2");
        Particular par2 = new Particular("PAR2", "Particular1");
        Particular par3 = new Particular("PAR3", "Particular3");
        Particular par4 = new Particular("PAR4", "Particular4");

        app.afegirParticulars(par1);
        app.afegirParticulars(par2);
        app.afegirParticulars(par3);
        app.afegirParticulars(par4);
        // Juguetes.
        Jugueta jug1 = new Jugueta(1, "Jugueta1");
        Jugueta jug2 = new Jugueta(2, "Jugueta2");
        Jugueta jug3 = new Jugueta(3, "Jugueta3");
        Jugueta jug4 = new Jugueta(4, "Jugueta4");
        Jugueta jug5 = new Jugueta(5, "Jugueta5");
        app.afegirJuguetes(jug1);
        app.afegirJuguetes(jug2);
        app.afegirJuguetes(jug3);
        app.afegirJuguetes(jug4);
        app.afegirJuguetes(jug5);
 
        // Factures per Empreses.
//        FacturaEmpresa fe1 = ev1.facturaEmpresa(1, 5, 10, 1, "+34", "ASDFG12233");
//        FacturaEmpresa fe2 = ev1.facturaEmpresa(2, 5, 10, 1, "+34", "AAAAAAAAA");
//        FacturaEmpresa fe3 = ev1.facturaEmpresa(1, 5, 10, 1, "+34", "BBBBBBBBB");
//        FacturaEmpresa fe4 = ev1.facturaEmpresa(4, 5, 10, 1, "+34", "RRRRRRRRRRR");
//        FacturaEmpresa fe5 = ev2.facturaEmpresa(3, 5, 10, 1, "+34", "TTTTTTTTTT");
//        FacturaEmpresa fe6 = ev2.facturaEmpresa(1, 5, 10, 3, "+34", "YUUYUUUU");
        //ficar dins empresa la factura
//        emp2.afegirFactura(fe1);
//        emp2.afegirFactura(fe2);
//        emp2.afegirFactura(fe3);
//        emp2.afegirFactura(fe4);
//        emp2.afegirFactura(fe5);
//        emp2.afegirFactura(fe6);
        // Factures per Client(importsPagats)

        // copiam objecte aplicacio al fitxer.
        EinesObjectesStream.escriuObjecte(uri, app);
    }

    public static Aplicacio carregarDadesInicialsFitxer(String uri) {
         
       return (Aplicacio) EinesObjectesStream.llegeixObjecte(uri);
        
    }

    public static void main(String[] args) {

        
        copiarDadesInicialsFitxer("fitxerInicial.dat");
        Aplicacio app = (Aplicacio) EinesObjectesStream.llegeixObjecte("fitxerInicial.dat");
                
        
       
        
        //crear xml amb l'empleat i la seva nomina.
        try {
            app.crearXml();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//        try {
//            System.out.println("1) Calcular nomina d'un Empleat introduint el seu codi d'empresa : "+app.calcularNominaEmpleat(5));
//        } catch (AccioNoRealitzable ex) {
//            System.out.println(ex);
//        }
        try {
            System.out.println("1) Calcular nomina d'un Empleat introduint el seu codi d'empresa : "+app.calcularNominaEmpleat(5));
            System.out.println("2) Crear Factura per un particular que paga al moment : "  + app.crearFactura(5, "PAR1", 2, 10, 5, 5));
            System.out.println("2) Crear Factura per un particular que paga al moment : "  + app.crearFactura(1, "EMP1", 28, 10, 5, 15));
        } catch (AccioNoRealitzable ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
////         try {
////            app.nouEmpleatVendes("Tofol", "7596584G", "@gmail.com", "123 456 789", "carrer",
////                    CategoriaEmpleat.TECNIC, 2000, 20);
////        } catch (AccioNoRealitzable ex) {
////            System.out.println("ERROR: " + ex.getMessage());
////        }
////        try {
////            app.crearFacturaEmpresa(1, "EMP1", 1, 50, 85.65, 10, "LaCaixa", TerminiPagament.DIARI, "+34", "85214566981");
////        } catch (AccioNoRealitzable ex) {
////            System.out.println("ERROR: " + ex.getMessage());
////        }
////        try {
////            System.out.println(app.calcularFacturacio("EMP1"));
////        } catch (AccioNoRealitzable ex) {
////            System.out.println(ex);
////        }
//        try {
//            System.out.println(app.calcularFacturacio("EMP2"));
//        } catch (AccioNoRealitzable ex) {
//            System.out.println("ERROR: "+ex.getMessage());
//        }
//        
        //crear factura a empresa i a particular correcte, els errors tambe surten correctament
//        try {
//            
//        app.crearFactura(1, "EMP2", 2, 10, 5, 5);
//        app.crearFactura(5, "PAR1", 2, 10, 5, 5);
//        } catch (AccioNoRealitzable e) {
//            System.out.println(e.getMessage());
//        }
        //Llistar empreses i particulars
        System.out.println("*******Llistar empreses i particulars dels que disposam ***********");
        ArrayList<Empresa> llistaEmpreses = app.getEmpreses();
        for (Empresa empresa : llistaEmpreses) {
            System.out.println(empresa);
        }
        ArrayList<Particular> llistaParticulars = app.getParticulars();
        for (Particular particular : llistaParticulars) {
            System.out.println(particular);
        }

            
    }

}
