package cat.iespaucasesnoves.facturacio;

import cat.iespaucasesnoves.excepcions.ExcepcioPagada;
import java.io.Serializable;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.util.ArrayList;

/**
 * Aquesta sera una clase abstracta, definidora de les diferents tipos de
 * factura que tendrem al sistema.
 *
 * @author alumnes
 * @version 1 *
 */
public abstract class Factura implements Serializable{

    private ArrayList<Integer> productes;
    private ArrayList<Integer> quantitats;
    private ArrayList<Double> preuUnitaris;
    private int descompte;
    private LocalDate data;
    private boolean pagada;
    private double total;

    private static int numeroFactura = 1;
    private final int codiFactura;

    /**
     * Constructor de factura on hi tenim tots els atributs necessaris,
     * l'atribut 'pagada' al crear una factura es posa com a no pagada. Les
     * llistes fan referencia a les linies que te la factura on cada linia es
     * una jugueta amb la seva quantitat i preu, les 3 llistes es relacionen per
     * mitja de les posicions. En crear una factura s'ha d'afegir un producte ja
     * que sino no te sentit fer la factura, si es voldran afegir mes productes
     * s'emnpleara el metode especific.
     *
     * @param producte primer de la jugueta.
     * @param quantitat de la jugueta que ens compren.
     * @param preuUnitari de la jugueta individual.
     * @param data de creacio de la factura.
     * @param descompte de la jugueta
     */
    public Factura(int producte, int quantitat, double preuUnitari, int descompte) {
        productes = new ArrayList<>();
        quantitats = new ArrayList<>();
        preuUnitaris = new ArrayList<>();
        /*introduim producte*/
        productes.add(producte);
        quantitats.add(quantitat);
        preuUnitaris.add(preuUnitari);
        this.descompte = descompte;
        this.data = now();
        this.pagada = false;
        this.total= quantitat*preuUnitari;

        codiFactura = numeroFactura;
        numeroFactura++;

    }

    /*getters de tots els atributs*/
    public ArrayList<Integer> getProductes() {
        return productes;
    }

    public ArrayList<Integer> getQuantitat() {
        return quantitats;
    }

    public ArrayList<Double> getPreuUnitari() {
        return preuUnitaris;
    }

    public int getDescompte() {
        return descompte;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isPagada() {
        return pagada;
    }

    public int getCodiFactura() {
        return codiFactura;
    }

    public double getTotal() {
        return total;
    }

    /*setters amb condicio de que la factura no estigui pagada que amollara excepcio*/

 /*Aquest setter ens deixara modificar una linia sencera de la factura.*/
    public void modificarLiniaFactura(int numeroLinia, int producte, int quantitat, double preuUnitari) throws ExcepcioPagada {

        if (pagada) {
            throw new ExcepcioPagada("Factura PAGADA no modificable");
        } else {
            int oldQuantitat = quantitats.get(numeroLinia);
            double oldPreuUnitari = preuUnitaris.get(numeroLinia);

            productes.add(numeroLinia, producte);
            quantitats.add(numeroLinia, quantitat);
            preuUnitaris.add(numeroLinia, preuUnitari);
            total = total + (preuUnitari * quantitat) - (oldQuantitat * oldPreuUnitari);
        }
    }

    public void setDescompte(int descompte) throws ExcepcioPagada {

        if (pagada) {
            throw new ExcepcioPagada("Factura PAGADA no modificable");
        } else {
            this.descompte = descompte;
        }
    }

    public void setPagada(boolean pagat) throws ExcepcioPagada {

        if (pagada) {
            throw new ExcepcioPagada("Factura PAGADA no modificable");
        } else {
            this.pagada = pagat;
        }
    }

    public void afegirLiniaFactura(int producte, int quantitat, double preuUnitari) throws ExcepcioPagada {

        if (pagada) {
            throw new ExcepcioPagada("Factura PAGADA no modificable");
        } else {
            productes.add(producte);
            quantitats.add(quantitat);
            preuUnitaris.add(preuUnitari);
            total = total + (preuUnitari * quantitat);
        }
    }

    @Override
    public String toString() {
        return "Factura{" + "productes=" + productes + ", quantitats=" + quantitats + ", preuUnitaris=" + preuUnitaris + ", descompte=" + descompte + ", data=" + data + ", pagada=" + pagada + ", total=" + total + ", codiFactura=" + codiFactura + '}';
    }
    
    
}
