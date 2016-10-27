/**
 * Das Package Modell dient fuer alle Akteure die Akionen und Verwaltungen durchfuehren koennen 
 */
package modell;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * @author Josef/Mirza
 *  Die Klasse Produkt,dient uns zur Erzeugung von Artikeln, welche zur Auktion ben�tigt werden
 */
public class Produkt implements Serializable{
	
	
	@Override
	public String toString() {
		return "Produkt [id=" + produktID +
						    ", Name=" + name +
						    ", startpreis="  + startpreis +
						    ", Produkt-Besitzer=" + ownerUsername  + "]";
	}

	
	
	
	private static final long serialVersionUID = 1L;
	UUID produktID;
	String name;
	double startpreis;
	String ownerUsername;
	String kategorie;
	int dauer;
	String beschreibung;
	Date startdatum;
	Date enddatum;
	String hoechstbietender;
	double aktuellesGebot;
	boolean verkauft = false;
	boolean rabatt=false;
	
/**
 * 
 * @param id Die ID des Produktes
 * @param name Der Name des Produktes
 * @param startpreis Der Startpreis des produktes 
 * @param Uname Der Username des Benutzers, welcher das Produkt anlegt
 * @param kategorie Ist eine Kategorie in welcher das Produkt am besten identifiziert wird
 * @param dauer gibt die Dauer der Auktion des Produktes an
 * @param beschreibung Die genau Beschreibung des Produktes
 */
	
	public Produkt(UUID id, String name, double startpreis, String Uname, String kategorie, int dauer, String beschreibung){
		setProduktID(id);
		setName(name);
		setStartpreis(startpreis);
		setOwnerUsername(Uname);
		//setAktpreis(startpreis);
		setKategorie(kategorie);
		setDauer(dauer);
		setBeschreibung(beschreibung);
		setStartdatum(new Date());
		setEnddatum(dauer);
		setHoechstbietender(Uname);
		setAktuellesGebot(startpreis);
		setRabatt(false);
		
	}
	
	// - - - - - - - - - - - - - - - - - - - - - //
	
	/**
	 * @return gibt den Rabatt zurueck
	 */
	public boolean isRabatt() {
	return rabatt;
	}
	
	/**
	 * @param rabatt wird der Instanzvariable rabatt zugeteilt
	 */
	public void setRabatt(boolean rabatt) {
		this.rabatt = rabatt;
	}

	/**
	 * @return gibt die Auktionsdauer in Tagen zurueck
	 */
	public int getDauer() {
		return dauer;
	}

	/**
	 * @param dauer aendert den Wert der Instanzvariable dauer
	 */
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	/**
	 * @return gibt die genau Produktbeschreibung zurueck
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung aendert die Beschreibung des Produktes 
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return gibt den Namen der Kategorie zurueck
	 */
	public String getKategorie() {
		return kategorie;
	}

	/**
	 * @param kategorie aendert den Namen der Kategorie
	 */
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	/**
	 * @return gibt die ID des Produktes zurueck
	 */
	public UUID getProduktID() {
		return produktID;
	}


	/**
	 * @param produktID aendert die ID des Produktes
	 */
	public void setProduktID(UUID produktID) {
		this.produktID = produktID;
	}


	/**
	 * @return gibt den Namen des Produktes zurueck
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name aendert den Namen des Produktes
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return gibt den Startpreis des Produktes zurueck
	 */
	public double getStartpreis() {
		return startpreis;
	}


	/**
	 * @param startpreis aendert den Startpreis des Produktes
	 */
	public void setStartpreis(double startpreis) {
		this.startpreis = startpreis;
	}


	/**
	 * @return gibt den Usernamen des angelegten Produktes zurueck
	 */
	public String getOwnerUsername() {
		return ownerUsername;
	}


	/**
	 * @param ownerUsername aendert den Besitzername des angelegten Produktes
	 */
	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}


	/**
	 * @return gibt den Status des produktes zurueck
	 */
	public boolean isVerkauft() {
		return verkauft;
	}


	/**
	 * @param verkauft aendert den Status der Produktes 
	 */
	public void setVerkauft(boolean verkauft) {
		this.verkauft = verkauft;
	}



	/**
	 * @return gibt das startdatum des produktes zurueck
	 */
	public Date getStartdatum() {
		return startdatum;
	}

	/**
	 * @param startdatum wird fuer das Produkt geaendert
	 */
	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}

	/**
	 * @return gibt den aktuellen Hoechstbietenden zurueck
	 */
	public String getHoechstbietender() {
		return hoechstbietender;
	}

	/**
	 * @param hoechstbietender aendert den Hoechstbietenden
	 */
	public void setHoechstbietender(String hoechstbietender) {
		this.hoechstbietender = hoechstbietender;
	}

	/**
	 * @return gibt das aktuelle Gebot zurueck
	 */
	public double getAktuellesGebot() {
		return aktuellesGebot;
	}

	/**
	 * @param aktuellesGebot wird geaendert 
	 */
	public void setAktuellesGebot(double aktuellesGebot) {
		this.aktuellesGebot = aktuellesGebot;
	}
	/**
	 * @return das Enddatum wird zurueckgeliefert
	 */
	public Date getEnddatum() {
		return enddatum;
	}

	/**
	 * @param dauer des Produkts wird geaendert
	 */
	public void setEnddatum(int dauer) {
		 Date now = new Date();
	        Calendar calendar = new GregorianCalendar();
	        calendar.setTime(now);

	        // add 5 days to calendar instance
	        calendar.add(Calendar.DAY_OF_MONTH, dauer);

	        // get the date instance
	        Date future = calendar.getTime();

	        // print out the dates...
	        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'um' HH:mm:ss");

	        System.out.println("Now:    " + dateFormat.format(now));
	        System.out.println("Future: " + dateFormat.format(future));
		this.enddatum = future;

	}
	
	
}
