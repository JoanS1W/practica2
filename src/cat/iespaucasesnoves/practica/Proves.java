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
    
    /*metode on cream dades y guardam objecte Aplicacio dins fitxer*/
    public static void copiarDadesInicialsFitxer(String uri, Aplicacio app) {

        //4 empleats vendes.
        EmpleatVendes ev1 = new EmpleatVendes("Miguel", "dni", "@gmail.com", "123 456 789", "carrer", CategoriaEmpleat.TECNIC, 2000, 20);
        EmpleatVendes ev2 = new EmpleatVendes("Joan", "nif", "@gmail.com", "123 456 789", "gran via", CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev3 = new EmpleatVendes("Toni Xavier", "nie", "@gmail.com", "123 456 789", "carrero", CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev4 = new EmpleatVendes("Pep", "dni", "@gmail.com", "123 456 789", "avenida", CategoriaEmpleat.DIRECTIU, 3000, 30);
        //afegim empleats de vendes a la app.
        app.afegirEmpleatsVendes(ev1);
        app.afegirEmpleatsVendes(ev2);
        app.afegirEmpleatsVendes(ev3);
        app.afegirEmpleatsVendes(ev4);
        //3 empleats generals        
        EmpleatGeneral eg1 = new EmpleatGeneral("M.A", "dni", "@gmail.com", "123 456 789", "carrer", CategoriaEmpleat.DIRECTIU, 3000, 30, 0);
        EmpleatGeneral eg2 = new EmpleatGeneral("J.S", "nif", "@gmail.com", "123 456 789", "gran via", CategoriaEmpleat.TECNIC, 2000, 20, 0);
        EmpleatGeneral eg3 = new EmpleatGeneral("T.X", "nie", "@gmail.com", "123 456 789", "carrer", CategoriaEmpleat.AUXILIAR, 1000, 10, 0);
        /*TODO*/
        //afegim empleats generals a la app.
        //Empreses.
        //Praticulars.
        //Juguetes.
        //Factures per clients.
        
        
        //copiam objecte aplicacio al fitxer.
        EinesObjectesStream.escriuObjecte(uri, app);

    }

    public static void carregarDadesInicialsFitxer(String uri) {


    }

    public static void main(String[] args) {
        
        Aplicacio app = new Aplicacio();
        copiarDadesInicialsFitxer("fitxerInicial.dat", app);
        carregarDadesInicialsFitxer("fitxerInicial.dat");
        
        

    }

}
