/**In diesem Package werden die Methode des Projektes 
 * überprueft und kontrolliert.
 */
package junitTest;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import modell.Produkt;
import network.client.Client;

/**
 * In dieser Klasse wird ein Test durchgefuehrt
 * um die Funktionalitaet einer Methode zu bestimmen.
 */

public class GetProduktListeJunitTest{

	/**
	 * Diese Methode ueberprueft ob alle vorhandenen Objekte
	 * in der Produktliste vom Typ Produkt sind.
	 */
	@Test
	public void test(){
		
		 Client usertest = new Client();
		 List<Produkt> prod = usertest.getProduktListe();
		 boolean produktcheck = false;
		 
		 for (Produkt i : prod){
				if(i.getClass() == Produkt.class){
					produktcheck = true;
				}
				else{
					produktcheck = false; 
				}
					
			 }
		 assertEquals(true,produktcheck);
			 
		
	}

}
