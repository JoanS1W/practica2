package cat.iespaucasesnoves.practica;

import cat.iespaucasesnoves.excepcions.*;
import cat.iespaucasesnoves.facturacio.*;
import cat.iespaucasesnoves.persones.*;
import cat.iespaucasesnoves.swpro.streams.auxiliar.EinesObjectesStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Miguel, Joan, Toni Xavier
 */
public class Proves {

    /* metode on cream dades y guardam objecte Aplicacio dins fitxer */
    public static void copiarDadesInicialsFitxer(String uri) {

        Aplicacio app = new Aplicacio();
        // 4 empleats vendes--> codi empresa autoincrementable automatic.
        EmpleatVendes ev1 = new EmpleatVendes("Miguel", "111", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.TECNIC, 2000, 20);
        EmpleatVendes ev2 = new EmpleatVendes("Joan", "222", "@gmail.com", "123 456 789", "gran via",
                CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev3 = new EmpleatVendes("Toni Xavier", "333", "@gmail.com", "123 456 789", "carrero",
                CategoriaEmpleat.AUXILIAR, 1500, 10);
        EmpleatVendes ev4 = new EmpleatVendes("Pep", "444", "@gmail.com", "123 456 789", "avenida",
                CategoriaEmpleat.DIRECTIU, 3000, 30);
        // afegim empleats de vendes 
        app.afegirEmpleatVendes(ev1);
        app.afegirEmpleatVendes(ev2);
        app.afegirEmpleatVendes(ev3);
        app.afegirEmpleatVendes(ev4);
        // 3 empleats generals--> codi empresa autoincrementable automatic.
        EmpleatGeneral eg5 = new EmpleatGeneral("M.A", "dni", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.DIRECTIU, 3000, 30, 10);
        EmpleatGeneral eg6 = new EmpleatGeneral("J.S", "nif", "@gmail.com", "123 456 789", "gran via",
                CategoriaEmpleat.TECNIC, 2000, 20, 20);
        EmpleatGeneral eg7 = new EmpleatGeneral("T.X", "nie", "@gmail.com", "123 456 789", "carrer",
                CategoriaEmpleat.AUXILIAR, 1000, 10, 3);
        // afegim empleats generals a la app.
        app.afegirEmpleatGeneral(eg5);
        app.afegirEmpleatGeneral(eg6);
        app.afegirEmpleatGeneral(eg7);
        // Empreses.
        Empresa emp1 = new Empresa("EMP1", "Empresa1", TerminiPagament.DIARI, "IBAN", "la caixa", "213453456FF");
        Empresa emp2 = new Empresa("EMP2", "Empresa2", TerminiPagament.SEMANAL, "IBAN", "la caixa", "213453456FF");
        Empresa emp3 = new Empresa("EMP3", "Empresa3", TerminiPagament.MENSUAL, "IBAN", "la caixa", "213453456FF");
        Empresa emp4 = new Empresa("EMP4", "Empresa4", TerminiPagament.TRIMESTRAL, "TARGETA", "la caixa", "98989898", 2018, 1);
        Empresa emp5 = new Empresa("EMP5", "Empresa5", TerminiPagament.TRIMESTRAL, "TARGETA", "sa nostra", "00000000", 2019, 5);
        app.afegirEmpresa(emp1);
        app.afegirEmpresa(emp2);
        app.afegirEmpresa(emp3);
        app.afegirEmpresa(emp4);
        app.afegirEmpresa(emp5);
        // Particulars.
        Particular par1 = new Particular("PAR1", "Particular2");
        Particular par2 = new Particular("PAR2", "Particular1");
        Particular par3 = new Particular("PAR3", "Particular3");
        Particular par4 = new Particular("PAR4", "Particular4");
        app.afegirParticular(par1);
        app.afegirParticular(par2);
        app.afegirParticular(par3);
        app.afegirParticular(par4);
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

        // Factures per Empreses i Particulars ( funcio de ticket )
        try {
            //EMP1 diaria
            Factura f1 = app.crearFactura(1, "EMP1", 1, 20, 10, 0);
            Factura f2 = app.crearFactura(1, "EMP1", 1, 30, 10, 0);
            Factura f3 = app.crearFactura(2, "EMP2", 1, 40, 10, 0);
            //canviam data per proves. A la entrega de l'aplicacio final aquest metode no existira.
            //EMP2 semanal
            f3.setData("2017-06-08");
            Factura f4 = app.crearFactura(2, "EMP2", 1, 50, 10, 0);
            f4.setData("2017-06-09");
            Factura f5 = app.crearFactura(3, "EMP3", 1, 50, 76, 10);
            //EMP3 mensual
            f5.setData("2017-06-20");
            Factura f6 = app.crearFactura(3, "EMP3", 2, 500, 85.5, 10);
            f6.setData("2017-06-10");
            Factura f7 = app.crearFactura(4, "EMP4", 5, 30, 288, 10);
            //EMP4 trimestral
            f7.setData("2017-06-20");
            Factura f8 = app.crearFactura(4, "EMP4", 1, 20, 43.65, 10);
            f8.setData("2017-06-04");
            Factura f9 = app.crearFactura(4, "EMP5", 1, 2, 123.8, 10);
            //EMP5 trimestral
            f9.setData("2017-09-20");
            app.crearFactura(5, "PAR2", 1, 24, 32.5, 10);
            app.crearFactura(6, "PAR3", 1, 40, 432.5, 10);
            app.crearFactura(7, "PAR1", 1, 2, 2.6, 10);
            app.crearFactura(5, "PAR4", 1, 24, 4.5, 10);

        } catch (AccioNoRealitzableException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (ExcepcioPagadaException e) {
            System.out.println("ERROR");
        }

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
//        try {
//            app.crearXml();
//            app.crearXmlFacturacioMensual();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        try {
            System.out.println("1) Calcular nomina d'un Empleat introduint el seu codi d'empresa : ");
            System.out.println("\tNomina = " + app.calcularNominaEmpleat(1) + " Euros");

            System.out.println("2) Crear Factura per un PARTICULAR que paga al moment i nomes guardam l'import cobrat: ");
            System.out.println("\t" + app.crearFactura(7, "PAR1", 2, 25, 4.65, 5));

            System.out.println("3) Crear Factura per una EMPRESA : ");
            System.out.println("\t" + app.crearFactura(4, "EMP1", 2, 300, 5, 0));

            System.out.println("4) Calcular la facturacio per el client empresa amb identificador 'EMP1' : ");
            System.out.println("\tLes seves factures son: ");
            Empresa e = (Empresa) app.cercarClient("EMP1");
            ArrayList<Factura> llistaFactures = e.getFactures();
            for (Factura f : llistaFactures) {
                System.out.println("\t" + f);
            }
            System.out.println("   **** Total Facturat = " + app.calcularFacturacio("EMP1") + " ****");
            System.out.println("5) Llista de 3 clients amb la facturacio mes alta, al nostre sistema tambe podria sortir un particular que hagues comprat molt ja que duim un registre del que gasta :");
            ArrayList<Client> llista = app.llistarMajorFacturacio2();
            for (int i = 0; i < 3; i++) {
                System.out.println("\t" + llista.get(i));
            }
            System.out.println("6) Llista de clients que han superat un limit de facturacio de '50000.50 Euros' :");
            ArrayList<Client> llistaParametritzada = app.llistaFacturacioParametritzada(1590.50);
            Collections.sort(llistaParametritzada);
            for (int i = 0; i < llistaParametritzada.size(); i++) {
                System.out.println("\t" + llistaParametritzada.get(i));
            }

            System.out.println("7) Llista de les 3 majors facturacions :");
            ArrayList<Client> majors = app.llistarMajorFacturacio2();
            for (int i = 0; i < 3; i++) {
                System.out.println("\t" + majors.get(i));
            }

        } catch (AccioNoRealitzableException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (ExcepcioPagadaException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (ValorNegatiuException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        //Llistar empreses i particulars
        System.out.println("*******Llistar empreses dels que disposam amb les facturesper veure si es cobren o no***********");
        ArrayList<Empresa> llistaEmpreses = app.getEmpreses();
        for (Empresa empresa : llistaEmpreses) {
            System.out.println(empresa);
            ArrayList<Factura> factures = empresa.getFactures();
            for (int i = 0; i < factures.size(); i++) {
                System.out.println("\t" + factures.get(i));
            }
        }
//         System.out.println("*******Llistar particulars dels que disposam amb els imports que hens han gastat***********");
//        ArrayList<Particular> llistaParticulars = app.getParticulars();
//        for (Particular particular : llistaParticulars) {
//            System.out.println(particular);
//            ArrayList<Double> factures = particular.getImportsPagats();
//            for (int i = 0; i < factures.size(); i++) {
//                System.out.println("\t IMPORT : "+ factures.get(i));
//            }
//        }

        app.cobramentDeFactures();
    }

}
