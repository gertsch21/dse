/**Das Package Management beinhaltet Klassen die zu Verwaltung der Akteure benoetigt werden
 *  
 * 
 */
package management;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import modell.Person;
import modell.Produkt;
import dao.ProduktDAO;
import dao.SerializedProduktDAO;
import dao.PersonDAO;
import dao.SerializedPersonDAO;

/**
 * @author Josef
 * 
 * Klasse Produktverwaltung verwaltet alle Methoden mit dem Produkt in Verbindung stehen.
 *
 */
public class Produktverwaltung {
	
	private static Produktverwaltung produktverwaltungInstance=null;
	private ProduktDAO dao;
	
	private Produktverwaltung(){
		dao = new SerializedProduktDAO();
	}
	
	/**
	 * @return gibt das Objekt produktverwaltungInstance (Produktverwaltung),
	 *  wenn noch keines vorhanden wird eines erzeugt.
	 */
	public static Produktverwaltung getinstance(){
		if(produktverwaltungInstance == null) produktverwaltungInstance = new Produktverwaltung();
		return produktverwaltungInstance;
		
	}
	
	/**
	 * @param name ist der Name des Produkt
	 * @param startpreis ist der Anfangspreis des Produkts
	 * @param Uname ist der Username des Besitzers
	 * @return dao.produktAnlegen() oder false
	 */
	public boolean produktAnlegen( String name, double startpreis, String Uname, String kategorie, int dauer, String beschreibung){
		
		
		
		PersonDAO daoP = new SerializedPersonDAO();
		List<Person> personListe = daoP.getPersonList();
		
		for(Person x:personListe){
			if(x.getUsername().equals(Uname)){
				System.out.println("username: " + Uname);
				
				UUID id=UUID.randomUUID();
				return dao.produktAnlegen(new Produkt(id, name, startpreis, Uname, kategorie, dauer, beschreibung));
			}
		}
		
		System.out.println("Error: (produktAnlegen()) : No matching Username was found.");
		return false;
		
	}
	
	/**
	 * @param parameter ist der vom User übergebene Parameter zur produktsuche
	 * @return Liste der gefundenen Produkte
	 */
	public List<Produkt> sucheProdukt(String parameter){
		
		List<Produkt> alleProdukte = dao.getProduktList();
		List<Produkt> gefundeneProdukte = new ArrayList<Produkt>();
		
		if(alleProdukte.size() == 0){
			return alleProdukte;
		}
		
		for(Produkt x: alleProdukte){
			if(x.getName().contains(parameter)){
				gefundeneProdukte.add(x);
				
			}
		}
		
		return gefundeneProdukte;	
	}
	
	/**
	 * @param id ist die aus der Produkt Lise zu löschende ID für das jeweilige Produkt
	 * @return gibt eine Funktion mit der id als Parameter an das ProduktDAO weiter zum löschen
	 */
	public boolean produktLoeschen(String id){//Verbesserungswürdig!!!
		System.out.println("Delete : " + id);
		return this.dao.produktLoeschen(id);
	}
	
	/**
	 * @param id entspricht dem zu verschiebenden Produkt
	 * @param kategorie entspricht der Kategorie in die das Produkt verschoben wird
	 * @return true oder false je nach dem ob das Produkt erfolgreich neu angelegt wird oder nicht.
	 */
	public boolean produktverschieben(UUID id,String kategorie){
	
		Produkt p= dao.getProduktByID(id.toString());
		Produkt a=p;
		a.setKategorie(kategorie);
		
		if(dao.produktLoeschen(p.getProduktID().toString())){
			if(dao.produktAnlegen(a)){
				return true;
			}
		}
		
		return false;
	}
	
	public List<Produkt> getAlleProdukt(){
		
		return dao.getProduktList();	
	}
}
