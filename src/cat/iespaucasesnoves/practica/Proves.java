package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.AccioNoRealitzable;
import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.*;
import cat.iespaucasesnoves.swpro.streams.auxiliar.EinesObjectesStream;
import java.io.IOException;

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
        EmpleatGeneral eg1 = new EmpleatGeneral("M.A", "dni", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.DIRECTIU, 3000, 30, 0);
        EmpleatGeneral eg2 = new EmpleatGeneral("J.S", "nif", "@gmail.com", "123 456 789", "gran via",
                CategoriaEmpleat.TECNIC, 2000, 20, 0);
        EmpleatGeneral eg3 = new EmpleatGeneral("T.X", "nie", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.AUXILIAR, 1000, 10, 0);
        // afegim empleats generals a la app.
        app.afegirEmpleatsGenerals(eg1);
        app.afegirEmpleatsGenerals(eg2);
        app.afegirEmpleatsGenerals(eg3);
        /* TODO */
        // Empreses.
        Empresa emp1 = new Empresa("EMP1", "Empresa1");
        Empresa emp2 = new Empresa("EMP2", "Empresa2");
        Empresa emp3 = new Empresa("EMP3", "Empresa3");
        Empresa emp4 = new Empresa("EMP4", "Empresa4");

        app.afegirEmpreses(emp1);
        app.afegirEmpreses(emp2);
        app.afegirEmpreses(emp3);
        app.afegirEmpreses(emp4);
        // Particulars.
        Particular par1 = new Particular("PAR1", "Particular1");
        Particular par2 = new Particular("PAR2", "Particular2");
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
        /*
                TODO
         */
        // Factures per Empreses.
        FacturaEmpresa fe1 = ev1.facturaEmpresa(1, 5, 10, 1, "BMN", TerminiPagament.DIARI, "+34", "ASDFG12233");
        FacturaEmpresa fe2 = ev1.facturaEmpresa(2, 5, 10, 1, "REF", TerminiPagament.DIARI, "+34", "AAAAAAAAA");
        FacturaEmpresa fe3 = ev1.facturaEmpresa(1, 5, 10, 1, "TTY", TerminiPagament.DIARI, "+34", "BBBBBBBBB");
        FacturaEmpresa fe4 = ev1.facturaEmpresa(4, 5, 10, 1, "UUU", TerminiPagament.DIARI, "+34", "RRRRRRRRRRR");
        FacturaEmpresa fe5 = ev2.facturaEmpresa(3, 5, 10, 1, "III", TerminiPagament.DIARI, "+34", "TTTTTTTTTT");
        FacturaEmpresa fe6 = ev2.facturaEmpresa(1, 5, 10, 3, "OOL", TerminiPagament.DIARI, "+34", "YUUYUUUU");
        //ficar dins empresa la factura
        emp1.afegirFactura(fe1);
        emp2.afegirFactura(fe2);
        emp2.afegirFactura(fe3);
        emp2.afegirFactura(fe4);
        emp2.afegirFactura(fe5);
        emp2.afegirFactura(fe6);
        // Factures per Client(importsPagats)

        // copiam objecte aplicacio al fitxer.
        EinesObjectesStream.escriuObjecte(uri, app);
    }

    public Objectt static void carregarDadesInicialsFitxer(String uri) {
        
        return EinesObjectesStream.llegeixObjecte(uri);
        
    }

    public static void main(String[] args) {

        
        copiarDadesInicialsFitxer("fitxerInicial.dat");
        carregarDadesInicialsFitxer("fitxerInicial.dat");
        
        Aplicacio app = new Aplicacio();
        
        //crear xml amb l'empleat i la seva nomina.
        try {
            app.crearXml();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            System.out.println(app.calcularFacturacio("EMP1"));;
        } catch (AccioNoRealitzable ex) {
            System.out.println(ex);
        }

    }

}
