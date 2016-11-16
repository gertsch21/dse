/**
 * In diesem Package werden die Mehtoden des Projektes überprüft und kontrolliert
 */
package junitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * Diese Methoden verbindet alle Mehtoden die zu der Operation pruefeLogin
 * miteinbezogen sind und kontrolliert sie alle nacheinander.
 * Somit ist der Prozess Login in einem Testcase abgearbeitet.
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ GetBenutzerListJunittTest.class, PruefeLoginJunitTest.class })
public class PruefeLoginAllTest {

}
