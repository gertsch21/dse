/**
*	Das Packet dient zum persistenten speichern der Benutzer und Produktdaten um spaeter 
*	wieder darauf zugreifen zu koennen.
 * 
 */
package dao;

import java.util.List;



import modell.Benutzer;
import modell.Person;

/**
 * @author Gerhard
 *
 */
public interface PersonDAO {

	/**
	 * Achtung, wenn keine Liste enthalten ist, eine leere Liste erstellt und
	 * diese zurückgegeben!
	 * 
	 * @return Die Liste der gespeicherten Person.
	 */
	List<Person> getPersonList();

	/**
	 * Achtung, wenn keine Liste enthalten ist, eine leere Liste erstellt und
	 * diese zurückgegeben!
	 * 
	 * @return Die Liste der gespeicherten Person.
	 */
	List<Benutzer> getBenutzerList();

	
	/**
	 * 
	 * @param id Anhand der eindeutigen ID wird die Person gesucht.
	 * @return Die gefundene Person wird zurückgegeben, oder null.
	 */
	Person getPersonById(String id);
	
	/**
	 * 
	 * @param username Anhand diesem eindeutigen Parameter wird die Person gesucht.
	 * @return Die gefundene Person wird zurückgegeben, oder null.
	 */
	Person getPersonByUsername(String username);

	/**
	 * 
	 * @param meinePerson Die zu speichernde Person.
	 * @return true, falls Vorgang erfolgreich, andernfalls false.
	 */
	boolean speicherePerson(Person meinePerson);

	/**
	 * 
	 * @param meinePerson Die Id der zu löschenden Person als String.
	 * @return true, falls Vorgang erfolgreich, andernfalls false.
	 */
	boolean loeschePerson(String meinePerson);

	/**
	 * 
	 * @param meinePerson Der Username der einzufrierenden Person als String.
	 * @return true, falls Vorgang erfolgreich, andernfalls false.
	 */
	boolean einfrierenPerson(String meinePerson);
	
	/**
	 * 
	 * @param meinePerson Der Username der einzufrierenden Person als String.
	 * @return true, falls Vorgang erfolgreich, andernfalls false.
	 */
	boolean einfrierenAufheben(String meinePerson);
	
}