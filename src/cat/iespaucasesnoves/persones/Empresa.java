package cat.iespaucasesnoves.persones;

import cat.iespaucasesnoves.facturacio.FacturaEmpresa;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alumne
 */
public class Empresa extends Client {

	private HashMap<Integer, FacturaEmpresa> factures;

	public Empresa (String identificador, String nom) {
        super(identificador, nom);
        this.factures = new HashMap<>();

        }
 
        @Override
	public double calcularFacturacio() {
		double totalFacturat = 0;
		for (FacturaEmpresa factura : factures.values()) {
			totalFacturat = totalFacturat + factura.getTotal();
		}
		return totalFacturat;
	}

	public ArrayList<FacturaEmpresa> getFactures() {
		ArrayList<FacturaEmpresa> copia = new ArrayList<>();
		for (FacturaEmpresa factura : factures.values()) {
			copia.add(factura);
		}
		return copia;
	}

	public void afegirFactura(FacturaEmpresa fe) {
		factures.put(fe.getCodiFactura(), fe);
	}

}
