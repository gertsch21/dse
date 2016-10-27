/**
*	Das Packet dient zum persistenten speichern der Benutzer und Produktdaten um spaeter 
*	wieder darauf zugreifen zu koennen.
 * 
 */

package dao;

import java.util.List;

import modell.Produkt;

/**
 * /**
 *Dieses Interface definiert Methoden des Entwurfsmusters DAO zur Erstellung persistenter Produktobjekte 
 *Diese Klasse erm�glicht es, auf Daten zuzugreifen.
 * @author Josef
 *
 */


public interface ProduktDAO {
	
	
	/**
	 * Diese Methode liefert eine Liste von Produkten 
	 * @return
	 */
	List<Produkt> getProduktList();
	/**
	 * 
     * Diese Methode lifert ein Objekt eines Produktes mit der eindeutigen Id zur�ck
     *  @param id ist eine ProduktId ,die bereits existiert 
	 * @return ein Objekt von der Klasse Produkt
	 */
	
	Produkt getProduktByID(String id);
	/**
	 * In dieser Methode wird ein Objekt von der Klasse Produkt gespeichert
	 * @param newProdukt ist ein Objekt von der Klasse Produkt das persistent gespeichert wird
	 * @return true wenn das Objekt korrekt gespeichert ist,false falls nicht
	 */
	
	boolean produktAnlegen(Produkt newProdukt);
	/**
	 * In dieser Methode wird ein Objekt von der Klasse Produkt geloescht
	 * @param deleteID ist eine existierende ProduktId, die uebergeben wird um das Produkt
	 * aus der Produkt Liste zu loeschen
	 * @return true wenn das Objekt korrekt geloescht ist ,false falls nicht
	 */
	boolean produktLoeschen(String deleteID);
	
}
