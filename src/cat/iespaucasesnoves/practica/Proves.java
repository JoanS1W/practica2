package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
//import cat.iespaucasesnoves.coses.ObjectStream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel, Joan, Toni Xavier
 */
public class Proves {

    /* metode on cream dades y guardam objecte Aplicacio dins fitxer */
    public static void copiarDadesInicialsFitxer(String uri, Aplicacio app) {

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
        Jugueta jug4 = new Jugueta(1, "Jugueta4");
        Jugueta jug5 = new Jugueta(1, "Jugueta5");
        app.afegirJuguetes(jug1);
        app.afegirJuguetes(jug2);
        app.afegirJuguetes(jug3);
        app.afegirJuguetes(jug4);
        app.afegirJuguetes(jug5);
        /*
                TODO
         */
        // Factures per Empreses.
        // Factures per Client(importsPagats)

        // copiam objecte aplicacio al fitxer.
        //ObjectStream.escriuObjecte(uri, app);
        

    }

    public static void carregarDadesInicialsFitxer(String uri) {

    }

    public static void main(String[] args) {

        Aplicacio app = new Aplicacio();
        copiarDadesInicialsFitxer("fitxerInicial.dat", app);
        carregarDadesInicialsFitxer("fitxerInicial.dat");
        
        //crear xml amb l'empleat i la seva nomina.
        try {
            app.crearXML();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
