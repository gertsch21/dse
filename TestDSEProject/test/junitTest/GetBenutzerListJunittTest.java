/**
 * In diesem Package werden die Mehtoden des Projektes überprüft und kontrolliert
 */
package junitTest;

import static org.junit.Assert.*;

import java.net.Socket;
import java.util.*;

import org.junit.Test;

import Admin_GUI.PhysicalServer;
import modell.Benutzer;
import network.client.Client;
import network.server.Server;

public class GetBenutzerListJunittTest {

	/**
	 * 
	 * Dieser Testfall prüft ob alle in der Liste vorhandenen Objekte
	 *  vom Typ Benutzer stammen.
	 */
	Socket clientsocket = new Socket();
	   @Test
	   public void testTypList(){
		 
		   //Starte Server
		   PhysicalServer test2 = new PhysicalServer();
		   test2.start();
		   
		  boolean check=false;
		  Client test = new Client();
		  List<Benutzer> liste = test.getBenutzerListe();
		  List<Benutzer> al = liste;
		  for(Benutzer i:al){
				if (i.getClass()==Benutzer.class){
					check=true;
				}
				else{
					check=false;	
				}
		  }
		  assertEquals (true,check);
	   }
}
	


