package hjelpeklasser;

import javax.persistence.Embeddable;

@Embeddable
public class Passord {

	private String passord_hash;
    private String passord_salt;
    
	private Passord(String hash, String salt) {
		passord_hash = hash;
		passord_salt = salt;
	}
	
	public Passord() {}

	public String getHashPassord() {
		return passord_hash;
	}
	
	public String getSalt() {
		return passord_salt;
	}
	
	@Override
	public String toString() {
		return "[passord_hash=" + passord_hash + ", passord_salt=" + passord_salt + "]";
	}

	public static Passord lagPassord(String passordKlartekst) {
		String salt = Passordhjelper.genererTilfeldigSalt();
		String hash = Passordhjelper.hashMedSalt2(passordKlartekst, salt);
		
		return new Passord(hash, salt);
	}
}
