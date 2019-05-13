
public class Bank extends Speler {

	private int kaartenInHand = 0;
	private String[] hand = new String[12];
	private int handWaarde = 0;

	public Bank() {

	}

	void geefKaart(String k) {
		hand[kaartenInHand] = k;
		kaartenInHand++;
	}

	void printHand() {
		System.out.print("[bank] bank hand: ");
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
	
	void resetHand() {
		kaartenInHand = 0;
	}
}
