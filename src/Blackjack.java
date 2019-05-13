/**
 * 
 * 			helaas niet afgekregen :(
 * 			mist nog een aantal functies
 * 
 */

import java.util.Scanner;

public class Blackjack {

	// main methode
	public static void main(String[] args) {
		new Blackjack().startSpel(); // uit de main methode zodat niet alles static hoeft te zijn
	}

	// start spel
	public void startSpel() {
		System.out.println("Welkom bij Blackjack");
		Speler[] spelers = getSpelers(); // is meerdere constructors met dezelfde naaam netjes?
		Dek dek = new Dek();
		// dek.printDek();
		System.out.println(
				"Alle spelers beginnen met €100, het spel eidigt als alle spelers of blut zijn of de tafel hebben verlaten. Succes!");
		System.out.println("");
		Bank bank = new Bank(); // bank aanmaken

		// SPEL LOOP
		while (checkGeld(spelers)) {
			// fase 1, inzet
			int[] spelerInzet = new int[spelers.length];
			for (int i = 0; i < spelers.length; i++) {
				spelers[i].setInzet();
				spelerInzet[i] = spelers[i].getInzet();
			}

			// fase 2, kaarten delen
			System.out.println("De kaarten worden verdeeld");
			for (int i = 0; i < spelers.length; i++) {
				spelers[i].geefKaart(dek.getKaart());
				spelers[i].geefKaart(dek.getKaart());
				spelers[i].setHandWaarde();
				spelers[i].printHand();
			}
			bank.geefKaart(dek.getKaart());
			bank.geefKaart(dek.getKaart());
			bank.setHandWaarde();
			bank.printHand();

			// fase 3, hit pass quit
//			for (int i = 0; i < spelers.length; i++) {
//				String keuze = spelers[i].getKeuze();
//				boolean aanHetKiezen = true;
//				while(aanHetKiezen) {
//					if(keuze.equals("h")) {
//						spelers[i].geefKaart(dek.getKaart());
//					}
//					else if(keuze.equals("p")) {
//						aanHetKiezen = false;
//					}
//					else {
//						aanHetKiezen = false;
//						System.exit(0);
//					}
//				}
//			}
			
			// fase 4, winnaars bepalen
			for (int i = 0; i < spelers.length; i++) {
				spelers[i].bepaalWinstOfVerlies(bank.getHandWaarde());
				spelers[i].resetHand();
			}
			bank.resetHand();
			System.out.println("\n[Nieuwe ronde]");
			
		}
		System.out.println("Niemand heeft geld, spel eindigt");
	}

	// maak spelers
	Speler[] getSpelers() {
		@SuppressWarnings("resource") // scanner sluit niet waarschuwing onderdrukken
		Scanner s = new Scanner(System.in);
		boolean geldigeInput = false;
		int aantalSpelers = 0;

		do { // exception handeling
			try {
				System.out.print("Met hoeveel spelers speelt u? "); // aantal spelers invullen
				aantalSpelers = s.nextInt();
				System.out.println("[" + aantalSpelers + " spelers aangemeld]");
				geldigeInput = true;
			} catch (Exception e) {
				System.out.println("[ongeldige input, probeer opnieuw]");
				s.next(); // verwijder ongeldige input
			}
		} while (!geldigeInput);

		Speler[] spelers = new Speler[aantalSpelers]; // array maken met alle spelers erin
		for (int i = 0; i < spelers.length; i++) {
			spelers[i] = new Speler();
			spelers[i].setNaam(i);
		}
		return spelers;
	}

	// kijk of spelers geld hebben
	boolean checkGeld(Speler[] spelers) {
		boolean geld = true;
		for (int i = 0; i < spelers.length; i++) {
			if (spelers[i].getGeld() == 0) {
				geld = false;
			}
		}
		return geld;
	}

}
