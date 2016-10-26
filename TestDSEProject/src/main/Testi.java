package main;

import java.util.UUID;

import dao.PersonDAO;
import dao.SerializedPersonDAO;
import management.Benutzerverwaltung;
import modell.Benutzer;
import modell.Person;

public class Testi {

	public static void main(String[] args) {
		
		Person neuePerson = new Benutzer(UUID.randomUUID(), "Gerhard","Schmidt", "gerhardschmidt@gmx.at","Roseldorf","Roseldorf","gertsch2","hallo");
		
		Benutzerverwaltung benver = Benutzerverwaltung.getInstance();
		
		benver.benutzerAnlegen("Mirza", "Talic", "denso@gmx.at", "WelserStrasse", "Wien", "mirzi", "hallo");

		for(Person p : benver.getBenutzerListe())
			System.out.println(p);
		
		
	}

}
