/**
*	Das Packet dient zum persistenten speichern der Benutzer und Produktdaten um spaeter 
*	wieder darauf zugreifen zu koennen.
 * 
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

import modell.Produktgruppe;

/**
 * Diese Klasse implementiert die Interface Klasse ProduktgruppeDAO 
 * 
 * @author Josef
 *
 */
public class SerializedProduktgruppeDAO implements ProduktgruppeDAO {

	private String filePath;

	private File myFile;
	/**
	 * Der Konstruktor der Klasse wird mit super(); von der Oberklasse aufgerufen 
	 * und erweitert durch weitere Instanzvariablen
	 */
	
	public SerializedProduktgruppeDAO(){
		super();
		this.filePath = "ProduktgruppeListe.dat";
		this.myFile = new File(filePath);

		checkIfFileExist();
	}
	
	
	/* (non-Javadoc)
	 * @see dao.ProduktgruppeDAO#getProduktgruppeList()
	 */
	public List<Produktgruppe> getProduktgruppeList() {
		
		InputStream is = null;
		ArrayList<Produktgruppe> myList = null;
		ObjectInputStream ois = null;
		
		if (myFile.length() == 0){
			System.out.println("Nix in file");
			myList = new ArrayList<Produktgruppe>();
			writeListInFile(myList);
			return myList;
		}

		
		try {
			is = new FileInputStream(filePath);
			ois = new ObjectInputStream(is);
			myList = (ArrayList<Produktgruppe>) ois.readObject();
		} catch (IOException e) {
			System.err.println("IO: "+e);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: "+e);
		} finally {
			try {
				ois.close();
				is.close();
			} catch (Exception e) {
				System.err.println("Fehler beim Schlie?en: "+e);
			}
		}
		
		return myList;
		
	}

	/* (non-Javadoc)
	 * @see dao.ProduktgruppeDAO#getProduktgruppeByName(java.lang.String)
	 */
	public Produktgruppe getProduktgruppeByName(String name) { 
		
		List<Produktgruppe> liste = getProduktgruppeList();
		if(liste == null) return null; //falls noch keine Liste
		
		
		for(Produktgruppe p:liste){
			if(p.getName().toString() == name){
				return p; //gib gefundenes retour
			}
		}
		
		return null;//falls nix gefunden 
		
	}

	/* (non-Javadoc)
	 * @see dao.ProduktgruppeDAO#produktgruppeAnlegen(modell.Produktgruppe)
	 */
	public boolean produktgruppeAnlegen(Produktgruppe newProduktgruppe) {
		
		List<Produktgruppe> myList = null;

		myList = getProduktgruppeList();
		for(Produktgruppe i:myList)
			if (newProduktgruppe.getProduktgruppenID().equals(i.getProduktgruppenID())) {
				System.out.println("Produktgruppe schon enthalten.");
				return false;
			}
		
		myList.add(newProduktgruppe);
			
		writeListInFile(myList);
		return true;
		
	}

	/* (non-Javadoc)
	 * @see dao.ProduktgruppeDAO#produktgruppeLoeschen(java.lang.String)
	 */
	public boolean produktgruppeLoeschen(String name) { // löschen  mit  ProduktID - Verbesserung ?
		
		boolean found = false;
		List<Produktgruppe> myList = this.getProduktgruppeList();
		
		try{
			for (Produktgruppe i : myList)
				if ( i.getName().equals(name) ) {
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
	/**
	 * In dieser Methode wird ueberprueft ob Bereits ein File angelegt ist,
	 *  falls nicht wird ein neues File erzeugt 
	 */
	public void checkIfFileExist() {

		if (!this.myFile.exists())
			try {
				this.myFile.createNewFile();
			} catch (IOException e1) {
				System.out.println("Error: File <ProduktgruppeListe> konnte nicht erstellt werden.(Gr?nde: fehlende Rechte,...)");
			}
	}
	/**
	 *  * In dieser Methode wird eine Liste von Produkten persisten gespeichert
	 * @param myList ist eine existierende Liste mit neuen Produkten die die alte Liste ueberschreiben wird 
	 */
	private void writeListInFile(List<Produktgruppe> myList) {
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

