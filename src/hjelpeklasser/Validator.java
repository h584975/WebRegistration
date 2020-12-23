package hjelpeklasser;

public class Validator {
	
	private static final String ANY_LETTER = "[a-zA-ZæøåÆØÅ]";
	
	public Validator() {
		
	}
	
	public boolean fornavnValid(String fornavn) {
		
		boolean valid =  !(fornavn == null || fornavn.equals("")) 
				&& Character.isUpperCase(fornavn.charAt(0))
				&& fornavn.length() <= 20 && fornavn.length() >= 2;
		
		return (valid ? fornavn.matches("^" + ANY_LETTER + "+[ -]{0,1}" + ANY_LETTER + "*$") : false);
	}
	
	public boolean etternavnValid(String etternavn) {
		
		if(etternavn != null) {
			etternavn = etternavn.trim();
		}
		
		return fornavnValid(etternavn) && !etternavn.contains(" ");
	}
	
	public boolean mobilValid(String mobil) {
		
		return mobil != null && mobil.trim().matches("^[0-9]{8}$");
		
	}
	
	public boolean kjonnValid(String kjonn) {
		
		return kjonn != null && kjonn.matches("^(mann|kvinne)$");
	}
	
	/*
	 * Et riktig passord er mellom 8 og 64 tegn
	 * Kan ikke har tre av samme tegn på rad
	 */
	public boolean passordValid(String passord) {
		
		boolean valid = passord != null && !passord.equals("");
		
		if(valid) {
			
			int lengde = passord.length();
			if(lengde < 8 || lengde > 64) {
				return false;
			}
			
			int antall = 0;
			char temp,c = passord.charAt(0);
	
			for(int i = 1; i < lengde; i++) {
				temp = passord.charAt(i);
				
				if(temp == c) {
					antall++;
					if(antall >= 2) {
						return false;
					}
				}else {
					antall = 0;
					c = temp;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean passord2Valid(String passord, String passord2) {
		
		return passord2 != null && passord2.equals(passord);
	}
}
