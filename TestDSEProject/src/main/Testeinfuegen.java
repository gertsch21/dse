package main;

import management.Produktgruppeverwaltung;
import management.Produktverwaltung;
import modell.Produkt;

public class Testeinfuegen {

	public static void main(String[] args) {
		
		Produktgruppeverwaltung prodgru = Produktgruppeverwaltung.getinstance();
		Produktverwaltung prover = Produktverwaltung.getinstance();
		
//		prodgru.produktgruppeAnlegen("Gitarren");
//		prover.produktAnlegen("Eisenhammer", 10, "mirzi", "Hammer", 10, "ein Eisenhammer");
		prover.produktAnlegen("Plastikhammer", 10, "mirzi", "Hammer", 10, "ein Plastikhammer");
		
		for(Produkt p : prover.getAlleProdukt()){
			System.out.println(p);
		}
	}

}
