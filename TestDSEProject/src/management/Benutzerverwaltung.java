/**
*	Das Package Management beinhaltet Klassen die zu Verwaltung der Akteure benoetigt werden
 * 
 */
package management;


import java.util.List;
import java.util.UUID;


import modell.Administrator;
import modell.Benutzer;
import modell.Forscher;
import modell.Person;
import dao.PersonDAO;
import dao.SerializedPersonDAO;

/**
 * @author Gerhard/Snezana
 *
 */
public class Benutzerverwaltung {
	
	private static Benutzerverwaltung benutzerverwaltungInstance=null;
	private PersonDAO dao;
	
	/**
	 * Konstruktor der Benutzerverwaltung, hier wird die private Variable vom Typ PersonDAO angelegt.
	 * 
	 */
	private Benutzerverwaltung() {
		dao = new SerializedPersonDAO();
	}
	
	/**
	 *  Singleton Design-pattern
	 *  
	 * @return Referenz auf das Benutzerverwaltungsobjekt
	 */
	public static Benutzerverwaltung getInstance(){
		if(benutzerverwaltungInstance == null) benutzerverwaltungInstance = new Benutzerverwaltung();
		return benutzerverwaltungInstance;
	}
	
	/**
	 * In dieser Methode wird geprüft, ob es eine Person gibt, mit dem Usernamen und dem dazugehörigen Passwort
	 * 
	 * @param username der zu überprüfende Benutzername
	 * @param password das zu überprüfende Passwort
	 * @return
	 */
	public boolean pruefeLogin(String username,String password){
		
		try{
			Person p = dao.getPersonByUsername(username);
		
			System.out.println("Prüfe login von: "+username+", korrektes pwd: "+p.getPassword());
		
			
			if(p.getPassword().equals(password)){
				if(p.getClass()==Benutzer.class && !((Benutzer)p).getIstEingefroren()){
					return true;//korrektes passwort-user kombi
				}if(p.getClass()!=Benutzer.class)
					return true;
			}
			return false;//falls kombi nicht passt
		}catch(NullPointerException e){
			return false;//null, da p null ist und p.getPassword aufgerufen wird
		}
	}
	
	
	/**
	 * In dieser Methode wird ein neuer Benutzer über das PersonDAO gespeichert.
	 * 
	 * @param vorname Vorname des neuen Benutzers
	 * @param nachname Nachname des neuen Benutzers
	 * @param email Email des neuen Benutzers
	 * @param strasse Strasse des neuen Benutzers
	 * @param wohnort Wohnort des neuen Benutzers
	 * @param username Der eineutige Username des neuen Benutzers
	 * @param password Das Passwort des neuen Benutzers
	 * @return Falls erfolgreich, wird true rückgegeben, bei einem Fehler: false
	 */
	public boolean benutzerAnlegen(String vorname, String nachname, String email, String strasse, String wohnort, String username, String password){//Verbesserungswürdig!!!
		UUID id = UUID.randomUUID();
	System.out.println("benutzerAnlegen: "+id+" "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
		return dao.speicherePerson(new Benutzer(id, vorname, nachname, email, strasse, wohnort, username, password));
	}
	
	/**
	 * In dierer Methode wird ein vorhandener Benutzer gelöscht.
	 * 
	 * @param username Der eineutige Username des neuen Benutzers 
	 * @return Falls erfolgreich wird true rückgegeben, bei einem Fehler: false
	 */
	public boolean benutzerloeschen(String username){//Achtung person nicht benutzer wird gelöscht!!!
		return this.dao.loeschePerson(username);
	}
	
	/**
	 * 
	 * @param username Einzufrierende Person username
	 * @return true, wenn erfolgreich, false, falls nicht möglich
	 */
	public boolean benutzerEinfrieren(String username){
		return this.dao.einfrierenPerson(username);
	}
	
	/**
	 * 
	 * @param username Username der Person, welche nicht mehr eingefroren sein soll.
	 * @return true, wenn erfolgreich, false, falls nicht möglich
	 */
	public boolean benutzerEinfAufheben(String username){
		return this.dao.einfrierenAufheben(username);
	}
	
	public boolean adminAnlegen(String vorname, String nachname, String email, String strasse, String wohnort, String username, String password,double gehalt){//Verbesserungswürdig!!!
		UUID id = UUID.randomUUID();
	System.out.println("AdminAnlegen: "+id+" "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
		return dao.speicherePerson(new Administrator(id, vorname, nachname, email, strasse, wohnort, username, password,gehalt));
	}
	public boolean forscherAnlegen(String vorname, String nachname, String email, String strasse, String wohnort, String username, String password,double gehalt){//Verbesserungswürdig!!!
		UUID id = UUID.randomUUID();
	System.out.println("ForscherAnlegen: "+id+" "+vorname+" "+nachname+" "+email+" "+strasse+" "+wohnort+" "+username+" "+password);
		return dao.speicherePerson(new Forscher(id, vorname, nachname, email, strasse, wohnort, username, password,gehalt));
	}
	public Person getBenByUsername(String username){//Achtung, liefert Personen und nicht Benutzer!!!
		return dao.getPersonByUsername(username);
	}
	/**
	 * Diese Methode setzt den zubelohnenden User in der setBelohunhgsvariable auf true.
	 * Somit hat der User bei nächsten einkauf ein Rabatt
	 *  Es wird eine Liste der Benutzer geholt und kontrolliert ob der User dort vorhanden ist
	 * @param username
	 * @return true
	 */
	public boolean benutzerbelohnen(String username){
	
		List<Benutzer>liste=dao.getBenutzerList();
		for(Benutzer i:liste){
			if(i.getUsername().equals(username)){
				i.setBelohnung(true);
				Benutzer p=i;
				if(benutzerloeschen(i.getUsername())){
					if(dao.speicherePerson(p)){
					
						return true;
					}
				}
			}
		}
		
		
		return false;
	}
	
	
	/**
	 * Retourniert die Liste mit allen Benutzern
	 * @return Liste mit allen Benutzern
	 */
	public List<Benutzer> getBenutzerListe(){
		return dao.getBenutzerList();
	}
	

}