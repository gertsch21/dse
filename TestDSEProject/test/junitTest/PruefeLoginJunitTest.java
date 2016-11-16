/**
 * In diesem Package werden die Mehtoden des Projektes überprüft und kontrolliert
 */
package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import network.client.Client;

public class PruefeLoginJunitTest{
	
	/**
	 * Dise Methode testet den Login des Users "mirzi".
	 * Hierbei geben wir den Username und das dazugehörige Passwort an
	 * und erwarten einen Bool'schen Wert.
	 * 
	 */

	@Test
	public void testLogin1(){
		Client test = new Client();
		boolean pruefe = test.pruefeLogin("mirzi", "hallo");
		assertEquals(true,pruefe);
	}
	
	/**
	 * Dise Methode testet den Login des Users "gertsch".
	 * Hierbei geben wir den Username und das dazugehörige Passwort an
	 * und erwarten einen Bool'schen Wert.
	 * 
	 */
	
	@Test
	public void testLogin2(){
		Client test = new Client();
		boolean pruefe = test.pruefeLogin("gertsch", "hallo");
		assertEquals(true,pruefe);
	}
	
	/**
	 * Dise Methode testet den Login des Users "gertsch2".
	 * Hierbei geben wir den Username und das dazugehörige Passwort an
	 * und erwarten einen Bool'schen Wert.
	 * 
	 */
	
	@Test
	public void testLogin3(){
		Client test = new Client();
		boolean pruefe = test.pruefeLogin("gertsch2", "hallo");
		assertEquals(true,pruefe);
	}
	
	/**
	 * Dise Methode testet den Login des Users "gertsch2".
	 * Hierbei geben wir den Username und das falsche Passwort an
	 * und erwarten einen Bool'schen Wert. In diesem Fall eben False, weil
	 * es nicht stimmen kann.
	 * 
	 */
	
	@Test
	public void testLogin4(){
		Client test = new Client();
		boolean pruefe = test.pruefeLogin("gertsch2", "Gurke");
		assertEquals(false,pruefe);
		
	}

}
