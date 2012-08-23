package springconversio.service;

import springconversio.service.Romanizer;
import junit.framework.TestCase;


public class RomanizerTests extends TestCase {

    private Romanizer romanizer;

    protected void setUp() throws Exception {
        romanizer = new Romanizer();
    }

    public void testConversion() {
		String[] decimals = new String[] { "536", "2166", "1909",
				"1567", "1079", "2870", "2389",
				"1863", "24", "1259", "3000", "0", "3001", null};
		String[] romans = new String[] { "DXXXVI", "MMCLXVI", "MCMIX",
				"MDLXVII", "MLXXIX", "MMDCCCLXX", "MMCCCLXXXIX",
				"MDCCCLXIII", "XXIV", "MCCLIX", "MMM", null, null, null};
    	for (int i = 0; i < decimals.length && i < romans.length; ++i) {
    		String roman = null;
    		try {
    			roman = romanizer.convertDecimalToRoman(decimals[i]);
                assertEquals(romans[i], roman);
    		} catch (IllegalArgumentException e) {
    			assertNull(roman);
    		}
    	}
    } 
}