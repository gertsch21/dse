/**
 * Das Package Modell dient fuer alle Akteure die Akionen und Verwaltungen durchfuehren koennen 
 */
package modell;

import java.util.UUID;

/**
 * @author Gerhard
 *
 * Die Klasse Administrator dient zur Verwaltung aller Benutzer und Produkte die registriert sind. 
 */
public class Administrator extends Benutzer {
	

	private static final long serialVersionUID = 1L;
	double gehalt;
	
	/**
	 * @param id Die id des jeweiligen Administrators.
	 * @param vorname Der Vorname des des jeweiligen Administrators.
	 * @param nachname Der Nachname des jeweiligen Administrators.
	 * @param email Die email des jeweiligen Administrators.
	 * @param strasse Die Strasse in der der jeweilige Administrator gemeldet ist.
	 * @param wohnort Der Wohnort, in dem der jeweilige Administrator gemeldet ist.
	 * @param username Der eindeutige Username des jeweiligen Administrators.(min 5 Zeichen)
	 * @param password Das Passwort des jeweiligen Administrators.(min 5 Zeichen) 
	 * @param gehalt Das Gehalt des Administrators.
	 */
	public Administrator(UUID id, String vorname, String nachname,
			String email, String strasse, String wohnort, String username,
			String password,double gehalt) {
		super(id, vorname, nachname, email, strasse, wohnort, username,
				password);
		setGehalt(gehalt);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return Das Gehalt des Administrators.
	 */
	public double getGehalt() {
		return gehalt;
	}
	
	/**
	 * 
	 * @param gehalt Das Gehalt des Administrators.
	 */
	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}

}
