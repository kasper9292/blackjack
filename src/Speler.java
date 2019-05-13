import java.util.Scanner;

public class Speler {

	private int nummer;
	private String naam;
	private int geld = 100;
	private int inzet;
	private int handWaarde = 0;
	private int kaartenInHand = 0;
	private String[] hand = new String[12];

	public Speler() {
	}

	void setNaam(int n) {
		nummer = ++n;
		System.out.print("[Speler " + nummer + "] typ hier uw naam: "); // vraag elke speler de naam
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		naam = s.nextLine();
	}

	String getNaam() {
		return naam;
	}

	int getGeld() {
		return geld;
	}

	void setInzet() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		boolean geldigeInput = false;

		do { // exception handeling
			try {
				System.out
						.print("[speler" + nummer + "] " + naam + " u heeft €" + geld + ", hoeveel wilt u inzetten?: ");
				inzet = s.nextInt();
				geldigeInput = true;
			} catch (Exception e) {
				System.out.println("[ongeldige input, probeer opnieuw]");
				s.next(); // verwijder ongeldige input
			}
		} while (!geldigeInput);
	}

	int getInzet() {
		geld -= inzet;
		if (inzet < 0) {
			inzet = 0;
		}
		return inzet;
	}

	void geefKaart(String k) {
		hand[kaartenInHand] = k;
		kaartenInHand++;
	}

	void resetHand() {
		kaartenInHand = 0;
	}

	void printHand() {
		System.out.print("[speler" + nummer + "] " + naam + ", hand: ");
		for (int i = 0; i < kaartenInHand; i++) {
			System.out.print(hand[i] + ", ");
		}
		System.out.println("(" + getHandWaarde() + ")");
	}

	void setHandWaarde() {
		handWaarde = 0;
		for (int i = 0; i < kaartenInHand; i++) {
			String rang = hand[i].substring(2);
			if (rang.equals("A")) {
				if (handWaarde <= 10) { // werkt (nog) niet als A je eerste kaar is en waarde veranderen later pas nodig
					handWaarde += 11;
				} else {
					handWaarde += 1;
				}
			} else if (rang.equals("J") || rang.equals("Q") || rang.equals("K")) {
				handWaarde += 10;
			} else {
				handWaarde += Integer.parseInt(rang);
			}
		}
	}

	int getHandWaarde() {
		return handWaarde;
	}

	String getKeuze() {
		System.out.println("Kies uw optie (h)it of (p)ass, druk een andere knop om het spel te beëindigen: ");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		return s.nextLine();
	}

	void bepaalWinstOfVerlies(int bw) {
		if (handWaarde > bw && handWaarde <= 21) {
			geld += inzet * 2;
			System.out.println("[speler" + nummer + "] " + naam + " heeft gewoonnen, nieuw geld = " + geld);
		} else if (handWaarde == bw) {
			geld += inzet;
			System.out.println("[speler" + nummer + "] " + naam + " heeft gelijkspel, geld blijft = " + geld);
		} else {
			if (geld < 0) {
				geld = 0;
			}
			System.out.println("[speler" + nummer + "] " + naam + " heeft verloren, nieuw geld = " + geld);
		}
	}
}
