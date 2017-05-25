package cat.iespaucasesnoves.facturacio;

import cat.iespaucasesnoves.excepcions.ExcepcioPagada;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  Aquesta sera una clase abstracta, definidora de les diferents tipos de factura que tendrem al sistema. 
 * @author alumnes
 * @version 1 * 
 */
public abstract class Factura {
    private ArrayList<Integer> productes;
    private ArrayList<Integer> quantitats;
    private ArrayList<Double> preuUnitaris;
    private int descompte;
    private LocalDate data;
    private boolean pagada;
    
    /**
    * Constructor de factura on hi tenim tots els atributs necessaris, l'atribut 'pagada' al crear una factura es posa com a no pagada.
    * Les llistes fan referencia a les linies que te la factura on cada linia es una jugueta amb la seva quantitat i preu, les 3 llistes es relacionen per mitja de les posicions.
    * En crear una factura s'ha d'afegir un producte ja que sino no te sentit fer la factura, si es voldran afegir mes productes s'emnpleara el metode especific.
    * @param producte primer de la jugueta.
    * @param quantitat de la jugueta que ens compren.
    * @param preuUnitari de la jugueta individual.
    * @param data de creacio de la factura.
    * @param descompte de la jugueta
    */
    public Factura(int producte, int quantitat, double preuUnitari, int descompte, LocalDate data) {
        productes = new ArrayList<>();
        quantitats = new ArrayList<>();
        preuUnitaris = new ArrayList<>();
        /*introduim producte*/
        productes.add(producte);
        quantitats.add(quantitat);
        preuUnitaris.add(preuUnitari);
        this.descompte = descompte;
        this.data = data;
        this.pagada = false;
    }
    
    abstract void afegirProducte();
    
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
    
    /*setters amb condicio de que la factura no estigui pagada que amollara excepcio*/

    /*Aquest setter ens deixara modificar una linia sencera de la factura.*/
    public void setProducte(int numeroLinia, int codiJugueta, int quantitat, double preuUnitari) throws ExcepcioPagada{
        
        if(pagada){
            throw new ExcepcioPagada("Factura PAGADA no modificable");
        }else{
            productes.add(numeroLinia, codiJugueta);
            quantitats.add(numeroLinia, quantitat);
            preuUnitaris.add(numeroLinia,preuUnitari);  
        }
    }

    public void setDescompte(int descompte) throws ExcepcioPagada{
        
        if (pagada) {
          throw new ExcepcioPagada("Factura PAGADA no modificable");  
        }else {
        this.descompte = descompte;
        }
    }

    public void setPagada(boolean pagat) throws ExcepcioPagada{
        
         if (pagada) {
          throw new ExcepcioPagada("Factura PAGADA no modificable");  
        }else {
        this.pagada = pagat;
        }
    }
    
    
}
