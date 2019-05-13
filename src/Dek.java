
public class Dek {
	private String[] kleur = { "S-", "H-", "K-", "R-" };
	private String[] rang = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
	private String[] dek = new String[52];
	private int kaartTeller = -1;

	// dek constructor
	public Dek() {
		vulDek();
		schudDek();
	}

	// vul dek
	void vulDek() {
		for (int i = 0; i < dek.length; i++) {
			dek[i] = kleur[i / 13] + rang[i % 13];
		}
	}

	// schud dek
	void schudDek() {
		kaartTeller = -1;
		
		for (int i = 0; i < dek.length; i++) {
			int nieuwePlek = (int) (Math.random() * dek.length);
			String oudePlek = dek[i];
			dek[i] = dek[nieuwePlek];
			dek[nieuwePlek] = oudePlek;
		}
	}

	// get dek
	String[] getDek() {
		return dek;
	}

	// pak kaart uit dek
	String getKaart() {
		kaartTeller++;
		return dek[kaartTeller];
	}
	
	// print dek
	void printDek() {
		for (String s : dek) {
			System.out.println(s);
		}
	}
}
