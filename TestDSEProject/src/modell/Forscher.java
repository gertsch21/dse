/**
 * Das Package Modell dient fuer alle Akteure die Akionen und Verwaltungen durchfuehren koennen 
 */
package modell;


import java.util.UUID;


/**
 * @author Gerhard
 * Die Klasse Forscher ist eine Unterklasse der Klasse Person und repräsentiert den Forscher, welcher fuer
 * Nutzerstatistik verantwortlich ist.
 */
public class Forscher extends Person {

	private static final long serialVersionUID = 1L;
	double gehalt;

	
	/**
	 * 
	 * @param id Die id des jeweiligen Forschers.
	 * @param vorname Der Vorname des des jeweiligen Forschers.
	 * @param nachname Der Nachname des jeweiligen Forschers.
	 * @param email Die email des jeweiligen Forschers.
	 * @param strasse Die Strasse in der der jeweilige Forscher gemeldet ist.
	 * @param wohnort Der Wohnort, in dem der jeweilige Forscher gemeldet ist.
	 * @param username Der eindeutige Username des jeweiligen Forschers.(min 5 Zeichen)
	 * @param password Das Passwort des jeweiligen Forschers.(min 5 Zeichen) 
	 * @param gehalt Das Gehalt des Forschers.
	 */
	public Forscher(UUID id, String vorname, String nachname, String email,
			String strasse, String wohnort, String username, String password, double gehalt) {
		super(id, vorname, nachname, email, strasse, wohnort, username, password);
		
		setGehalt(gehalt);
	}
	
	/**
	 * 
	 * @return Das aktuelle Gehalt des Forschers.
	 */
	public double getGehalt() {
		return gehalt;
	}
	
	/**
	 * 
	 * @param gehalt Der neue Gehalt des Forschers.
	 */
	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
	
	
}