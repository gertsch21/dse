/**
 * Das Package Modell dient fuer alle Akteure die Akionen und Verwaltungen durchfuehren koennen 
 */
package modell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Josef
 * Die Klasse Produktgruppe dient als Grundlage fuer alle Produkte, um sie in Kategorien zu Verwalten.
 *
 */
public class Produktgruppe implements Serializable {

	private static final long serialVersionUID = 1L;
	UUID produktgruppenID;
	String name;
	List<Produkt> liste;
	
	
	public Produktgruppe( String name ) {
		setProduktgruppenID(produktgruppenID);
		setName(name);
		//setListe(liste);
	}
	
	/**
	 * @return gibt die ID der Produktgruppe zurueck
	 */
	public UUID getProduktgruppenID() {
		return produktgruppenID;
	}

	/**
	 * @param aendert die ID der Produktgruppe auf einen gewuenschten Wert
	 */
	public void setProduktgruppenID(UUID produktgruppenID) {
		this.produktgruppenID = UUID.randomUUID();
	}

	/**
	 * @return gibt den Namen der Produktgruppe zurueck
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param aendert den Namen der Produktgruppe auf einen gewuenschten Wert
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return gibt eine Liste mit allen Produkten zurueck
	 */
	public List<Produkt> getListe() {
		return liste;
	}

	/**
	 * @param aendert eine Liste mit den Produkten
	 */
	public void setListe(List<Produkt> liste) {
		this.liste = new ArrayList<Produkt>();
	}


	
	
}