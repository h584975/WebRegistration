package hjelpeklasser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Passordhjelper {

	public static String genererTilfeldigSalt() {
	    SecureRandom sr;
	    byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		    sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    return DatatypeConverter.printHexBinary(salt);
	}

	public static String hashMedSalt(String passord, String salt) { 
		
		byte[] passhash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);
			md.update(saltbytes);
			passhash = md.digest(passord.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(passhash);
	}
	
	public static boolean validerMedSalt(String passord, String salt, String passordhash) {
		return passordhash.equals(hashMedSalt(passord, salt));
	}
	
	public static String hashMedSalt2(String passord, String salt) { 
		
		char[] passchar = passord.toCharArray();
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);
		
		byte[] keyhash = null;
		try {
			PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			keyhash = skf.generateSecret(pks).getEncoded();
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(keyhash);
	}
	
	public static boolean validerMedSalt2(String passord, String salt, String passordhash) {
		return passordhash.equals(hashMedSalt2(passord, salt));
	}
	

}
