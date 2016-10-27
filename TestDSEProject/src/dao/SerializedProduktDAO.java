/**
 * Das Packet dient zum persistenten speichern der Benutzer und Produktdaten um spaeter 
*	wieder darauf zugreifen zu koennen.
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

import modell.Produkt;

/**
 * Diese Klasse implementiert die Interface Klasse ProduktDAO 
 *
 * @author  Josef
 *
 */


public class SerializedProduktDAO implements ProduktDAO {

	private String filePath;

	private File myFile;
	
	/**
	 * Der Konstruktor der Klasse wird mit super(); von der Oberklasse aufgerufen 
	 * und erweitert durch weitere Instanzvariablen
	 */
	
	public SerializedProduktDAO(){
		super();
		this.filePath = "ProduktListe.dat";
		this.myFile = new File(filePath);

		checkIfFileExist();
	}
	
 
	/* (non-Javadoc)
	 * @see dao.ProduktDAO#getProduktList()
	 */
	@Override
	public List<Produkt> getProduktList() { 
		InputStream is = null;
		ArrayList<Produkt> myList = null;
		ObjectInputStream ois = null;
		
		if (myFile.length() == 0){
			System.out.println("Nix in file");
			myList = new ArrayList<Produkt>();
			writeListInFile(myList);
			return myList;
		}

		
		try {
			is = new FileInputStream(filePath);
			ois = new ObjectInputStream(is);
			myList = (ArrayList<Produkt>) ois.readObject();
		} catch (IOException e) {
			System.err.println("IO: "+e);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: "+e);
		} finally {
			try {
				ois.close();
				is.close();
			} catch (Exception e) {
				System.err.println("Fehler beim Schlie�en: "+e);
			}
		}
		
		return myList;
		
	}

	@Override
	public Produkt getProduktByID(String id) { 
		
		List<Produkt> liste = getProduktList();
		if(liste == null) return null; //falls noch keine Liste
		
		
		for(Produkt p:liste){
			if(p.getProduktID().toString().equals(id)){
				return p; //gib gefundenes retour
			}
		}
		
		return null;//falls nix gefunden 
		
	}
	

	/* (non-Javadoc)
	 * @see dao.ProduktDAO#produktAnlegen(modell.Produkt)
	 */
	@Override
	public boolean produktAnlegen(Produkt newProdukt) {
		
		List<Produkt> myList = getProduktList();

		//myList = getProduktList();
		for(Produkt i:myList)
			if (newProdukt.getProduktID().equals(i.getProduktID())) {
				System.out.println("Produkt schon enthalten.");
				return false;
			}
		
		myList.add(newProdukt);
			
		writeListInFile(myList);
		return true;
		
	}

	/* (non-Javadoc)
	 * @see dao.ProduktDAO#produktLoeschen(java.lang.String)
	 */
	@Override
	public boolean produktLoeschen(String deleteID) { // löschen  mit  ProduktID - Verbesserung ?
		
		boolean found = false;
		List<Produkt> myList = this.getProduktList();
		
		try{
			for (Produkt i : myList)
				if ( i.getProduktID().toString().equals(deleteID) ) {
					System.out.println(i);
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
				System.out.println("Error: File <ProduktListe> konnte nicht erstellt werden.(Gr�nde: fehlende Rechte,...)");
			}
	}
	
	/**
	 * In dieser Methode wird eine Liste von Produkten persisten gespeichert
	 * @param myList ist eine existierende Liste mit neuen Produkten die die alte Liste ueberschreiben wird
	 */
	
	private void writeListInFile(List<Produkt> myList) {
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
