package main;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.UUID;

import dao.PersonDAO;
import dao.SerializedPersonDAO;
import management.Benutzerverwaltung;
import modell.Benutzer;
import modell.Person;
import network.client.SimpleClient;

public class Testi {

	public static void main(String[] args) {
		SimpleClient client = new SimpleClient(1234,"localhost");
		
		List<Benutzer> liste = client.getBenutzerListe();
		
		for(Benutzer a : liste)
			System.out.println(a);
		System.out.println("Client: erfolgreich beendet!");

		
		if(client.pruefeLogin("mirzi", "hallo"))
			System.out.println("Userdaten sind korrekt");
		else
			System.out.println("Inkorrekte Userdaten");
		
		client.beenden();
	}

}
