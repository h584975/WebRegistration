package testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hjelpeklasser.Validator;

public class ValidatorTest {

	private Validator validator;
	
	@BeforeEach
	public void setUp() {
		 validator = new Validator();
	}
	
	@Test
	public void fornavnTest() {
		
		assertFalse(validator.fornavnValid(""));
		assertFalse(validator.fornavnValid("  "));
		assertFalse(validator.fornavnValid(null));
		assertFalse(validator.fornavnValid("An44der2s"));
		assertFalse(validator.fornavnValid("anders"));
		assertFalse(validator.fornavnValid("A"));
		assertFalse(validator.fornavnValid("Abcdefghijklmnopqrstuvwxyz"));
		assertFalse(validator.fornavnValid("John?Anders"));
		
		assertTrue(validator.fornavnValid("Anders"));
		assertTrue(validator.fornavnValid("John Anders"));
		assertTrue(validator.fornavnValid("John-Anders"));
		assertTrue(validator.fornavnValid("Øliver"));
		assertTrue(validator.fornavnValid("Kjærsti"));
	}
	
	/*
	 * "etternavnValid()" kaller "fornavnValid()". Derfor tester denne metoden bare det ekstra, nemlig mellomrom
	 */
	@Test
	public void etternavnTest() {
		
		assertFalse(validator.etternavnValid("Aarsa ether"));
		
		assertTrue(validator.etternavnValid("Aarsæther"));
		assertTrue(validator.etternavnValid(" Aarsæther "));
	}
	
	@Test
	public void mobilTest() {
		
		assertFalse(validator.mobilValid(""));
		assertFalse(validator.mobilValid("  "));
		assertFalse(validator.mobilValid(null));
		assertFalse(validator.mobilValid("123456789"));
		assertFalse(validator.mobilValid("1234567"));
		assertFalse(validator.mobilValid("1234567A"));
		
		assertTrue(validator.mobilValid("12345678"));
	}
	
	@Test
	public void kjonnTest() {
		
		assertFalse(validator.kjonnValid("mannen"));
		assertFalse(validator.kjonnValid(""));
		assertFalse(validator.kjonnValid(null));
		
		assertTrue(validator.kjonnValid("mann"));
		assertTrue(validator.kjonnValid("kvinne"));
	}
	
	@Test
	public void passordTest() {
		
		assertFalse(validator.passordValid(""));
		assertFalse(validator.passordValid(null));
		assertFalse(validator.passordValid("qwerty"));
		assertFalse(validator.passordValid("feilPasssord"));
		
		assertTrue(validator.passordValid("qwerty123"));
		assertTrue(validator.passordValid("riktigPassord"));
	}
	
}
