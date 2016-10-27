/**
 * Das Package Management beinhaltet Klassen die zu Verwaltung der Akteure benoetigt werden
 */
package management;

import java.util.List;

import dao.ProduktgruppeDAO;
import dao.SerializedProduktgruppeDAO;
import modell.Produktgruppe;


/**
 * @author Josef
 * 
 * Klasse Produktgruppeverwaltung verwaltet das Anlegen und Loeschen von Produktgruppen
 *
 */
public class Produktgruppeverwaltung {

	private static Produktgruppeverwaltung ProduktgruppeverwaltungInstance=null;
	private ProduktgruppeDAO dao;
	
	private Produktgruppeverwaltung(){
		dao = new SerializedProduktgruppeDAO();
	}
	
	/**
	 * @return gibt das Objekt ProduktgruppeverwaltungInstance (Produktgruppeverwaltung), 
	 * wenn noch keines vorhanden wird eines erzeugt.
	 */
	public static Produktgruppeverwaltung getinstance(){
		if(ProduktgruppeverwaltungInstance == null) ProduktgruppeverwaltungInstance = new Produktgruppeverwaltung();
		return ProduktgruppeverwaltungInstance;
	}
	
	/**
	 * 
	 * @param name ist der neue Produktgruppename der angelegt wird
	 * @return true wenn die neue Produktgruppe angelegt ist,
	 * @return false wenn die neue Produktgruppe einen Fehler hat
	 */
	public boolean produktgruppeAnlegen( String name ){
		
		List<Produktgruppe> liste = dao.getProduktgruppeList();

		for(Produktgruppe x: liste){
			if(x.getName().equals(name)){
				return false;
			}
		}
		
		Produktgruppe neueProduktgruppe = new Produktgruppe(name);
		return dao.produktgruppeAnlegen(neueProduktgruppe);
	}
	/**
	 * 
	 * @param name ist der gesuchte Produktgruppename, der geloescht werden soll
	 * @return true wenn das Loeschen von der Produktgruppe erfolgreich war,
	 * false falls nicht
	 */
	
	public boolean produktgruppeLoeschen( String name ){
	
		List<Produktgruppe> liste = dao.getProduktgruppeList();

		for(Produktgruppe x: liste){
			if(x.getName().equals(name)){
				return dao.produktgruppeLoeschen(name);
			}
		}

		return false;
		
	}
	
	
}
