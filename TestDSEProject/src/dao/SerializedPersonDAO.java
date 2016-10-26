/**
 * Das Packet dient zum persistenten speichern der Nutzerstatistik
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import modell.Benutzer;
import modell.Person;

/**
 * @author Gerhard
 *
 */
public class SerializedPersonDAO implements PersonDAO {

	private String filePath;

	private File myFile;
	
	/**
	 * Das der Pfad ist immer PersonenListe.dat(also im jeweiligen Ordner)
	 */
	public SerializedPersonDAO() {
		super();
		this.filePath = "PersonenListe.dat";
		this.myFile = new File(filePath);

		checkIfFileExist();
	}

	/* (non-Javadoc)
	 * @see dao.PersonDAO#getPersonList()
	 */
	@Override
	public List<Person> getPersonList() {
		InputStream is = null;
		ArrayList<Person> myList = null;
		ObjectInputStream ois = null;
		
		if (myFile.length() == 0){
			System.out.println("Nix in file");
			myList = new ArrayList<Person>();
			writeListInFile(myList);
			return myList;
		}

		
		try {
			is = new FileInputStream(filePath);
			ois = new ObjectInputStream(is);
			myList = (ArrayList<Person>) ois.readObject();
		} catch (IOException e) {
			System.err.println("IO: "+e);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: "+e);
		} finally {
			try {
				ois.close();
				is.close();
			} catch (Exception e) {
				System.err.println("Fehler beim Schließen: "+e);
			}
		}
		
		return myList;
	}

	/* (non-Javadoc)
	 * @see dao.PersonDAO#getBenutzerList()
	 */
	@Override
	public List<Benutzer> getBenutzerList(){
		List<Person> personen=this.getPersonList();
		List<Benutzer> benutzer = new ArrayList<Benutzer>();
		for(Person i:personen){
			if(i.getClass()==Benutzer.class)
				benutzer.add((Benutzer)i);
		}
		System.out.println("Anz Personen: "+personen.size()+"\nAnz Benutzer: "+benutzer.size());
		return benutzer;
		
	}
	/* (non-Javadoc)
	 * @see dao.PersonDAO#getPersonById(long)
	 */
	@Override
	public Person getPersonById(String id) {

		List<Person> liste = getPersonList();
		if(liste == null) return null; //falls noch keine Liste
		
		
		for(Person p:liste){
			if(p.getId().toString() == id){
				return p; //gib gefundenes retour
			}
		}
		
		return null;//falls nix gefunden 
	}

	/* (non-Javadoc)
	 * @see dao.PersonDAO#speicherePerson(modell.Person)
	 */
	@Override
	public boolean speicherePerson(Person meinePerson) {


		List<Person> myList = null;

		myList = getPersonList();
		for(Person i:myList)
			if (meinePerson.getUsername().equals(i.getUsername()) || meinePerson.getId().equals(i.getId())) {
				System.out.println("Person schon enthalten.");
				return false;
			}
		
		myList.add(meinePerson);
			
		writeListInFile(myList);
		return true;
		
	}
	
	/* (non-Javadoc)
	 * @see dao.PersonDAO#getPersonByUsername()
	 */
	@Override
	public Person getPersonByUsername(String username) {
		List<Person> liste = getPersonList();
		
		if(liste == null){System.out.println("Keine Liste!"); return null;} //falls noch keine Liste

		for(Person p:liste){
			if(p.getUsername().equals(username))
				return p; //gib gefundenes retour
		}
		
		return null;//falls nix gefunden 
	}

	
	/* (non-Javadoc)
	 * @see dao.PersonDAO#loeschePerson(modell.Person)
	 */
	@Override
	public boolean loeschePerson(String username) {
		boolean found = false;
		List<Person> myList = this.getPersonList();
		
		try{
			for (Person i : myList)
				if ( i.getUsername().equals(username) ) {
					myList.remove(i);
					found = true;
					break;
				}
			
			writeListInFile(myList);
			return found;
		}catch(NullPointerException e){
			System.out.println("Error: Keine Liste vorhanden!"); //Zur Absicherung, obwohl eig getArtikelList() eine leere erzeugt, wenn keine vorhanden.
			return false;
		}

	}
	
	/* (non-Javadoc)
	 * @see dao.PersonDAO#einfrierenPerson()
	 */
	@Override
	public boolean einfrierenPerson(String username) {
		boolean found = false;
		List<Person> myList = this.getPersonList();
		try{
			for (Person i : myList){
				if ( i.getUsername().equals(username) ) {
					if(i.getClass()== Benutzer.class)
						((Benutzer)i).setIstEingefroren(true);
					else 
						return false;
					found = true;
					break;
				}
			}
			
			writeListInFile(myList);
			return found;
		}catch(NullPointerException e){
			System.out.println("Error: Keine Liste vorhanden!"); //Zur Absicherung, obwohl eig getArtikelList() eine leere erzeugt, wenn keine vorhanden.
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see dao.PersonDAO#einfrierenAufheben()
	 */
	@Override
	public boolean einfrierenAufheben(String username) {
		boolean found = false;
		List<Person> myList = this.getPersonList();
		try{
			for (Person i : myList){
				if ( i.getUsername().equals(username) ) {
					if(i.getClass()== Benutzer.class)
						((Benutzer)i).setIstEingefroren(false);
					else 
						return false;
					found = true;
					break;
				}
			}
			
			writeListInFile(myList);
			return found;
		}catch(NullPointerException e){
			System.out.println("Error: Keine Liste vorhanden!"); //Zur Absicherung, obwohl eig getArtikelList() eine leere erzeugt, wenn keine vorhanden.
			return false;
		}
	}

	
	
	/**
	 * Prüft ob ein Person in einer gegebenen Liste enthalten ist.
	 * 
	 * @param meinPerson Der zu prüfende Person.
	 * @param myList Die zu verwendende Liste.
	 * @return True falls enthalten, False falls nicht enthalten.
	 */
	private boolean elementInList(Person meinPerson, List<Person> myList) {
		if(myList != null)
			for (Person i : myList)
				if (i.getId() == meinPerson.getId())
					return true;
		return false;
	}

	/**
	 * Prüft ob das File, auf welches myFile zeigt auch existiert, falls nicht, dann wird eines erzeugt.
	 */
	public void checkIfFileExist() {

		if (!this.myFile.exists())
			try {
				this.myFile.createNewFile();
			} catch (IOException e1) {
				System.out.println("Error: File konnte nicht erstellt werden.(Gründe: fehlende Rechte,...)");
			}
	}
	
	/**
	 * Diese Methode schreibt eine übergebene Liste in die Datei.
	 * @param myList Die zu speichernde Liste.
	 */
	private void writeListInFile(List<Person> myList) {
		OutputStream fo = null;
		try {
			fo = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fo);
			oos.writeObject(myList);

		} catch (IOException e) {
			System.err.println("Problem mit dem Dateischreiben: " + e);
		} finally {
			try {
				fo.close();
			} catch (Exception e) {
				System.err.println("Problem mit schliessen: " + e);
			}
		}

	}



	
}