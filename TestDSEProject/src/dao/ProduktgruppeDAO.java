/**
*	Das Packet dient zum persistenten speichern der Benutzer und Produktdaten um spaeter 
*	wieder darauf zugreifen zu koennen.
 * 
 */

package dao;

import java.util.List;
import modell.Produktgruppe;

/**
 *Dieses Interface definiert Methoden des Entwurfsmusters DAO zur Erstellung persistenter Produktgruppeobjekte 
 *Diese Klasse erm�glicht es, auf Daten zuzugreifen.
 * @author Josef
 */


public interface ProduktgruppeDAO {
	
	/**
	 * Diese Methode liefert eine Liste von allen angelegten Produktgruppen
	 * @return
	 */

	List<Produktgruppe> getProduktgruppeList();
	/**
	 * In dieser Methode wird eine Produktgruppe Liste mit existierten Namen zurueckgeliefert 
	 * @param name ist ein existirender Name, der uebergeben wird um die gesuchte
	 *  Produktgruppe zurueckzuliefern. 
	 * @return 
	 */
	
	Produktgruppe getProduktgruppeByName(String name);
	/**
	 * In dieser Methode wird eine neue Produktgruppe persistent angelegt
	 * @param newProduktgruppe ist ein Objekt, das persistent in einer Produktgruppe Liste angelegt wird 
	 * @return true wenn das Objekt korrekt angelegt ist,
	 * false falls nicht
	 */
	
	boolean produktgruppeAnlegen(Produktgruppe newProduktgruppe);
	/**
	 * In diesesr Methode wird ein existierendes Objekt aus der Produktgruppe Liste geloescht
	 * 
	 * @param name ist ein existierender Name, der uebergeben wird um die gesuchte Produktgruppe 
	 * zu loeschen.
	 * @return true wenn das Objekt geloescht ist ,
	 * flase falls nicht
	 */
	
	boolean produktgruppeLoeschen(String name);
}
