package springconversio.service;

import java.util.List;
import java.util.Vector;

import springconversio.domain.InputNumeral;
import springconversio.domain.Pair;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConversionServiceImpl implements ConversionService {

	protected final Log logger = LogFactory.getLog(getClass());

	private Romanizer romanizer = new Romanizer();

	@Override
	public void convert(InputNumeral numeral) {
	    try {
	    	String roman =
	    			this.romanizer.convertDecimalToRoman(numeral.getNumeral());
	        logger.info("Converted successful" + numeral.getNumeral() +
	        		" to " +  roman);
	    } catch (IllegalArgumentException e) {
	        logger.info("Failed to convert numeral");
	    }
	}

	@Override
	public List<Pair<String,String>> fetch() {
        List<Pair<String,String>> pairs = new Vector<Pair<String,String>>();
        try {
            for (int i = 0; i < 10; ++i) {
                String decimal = "1234";
                String roman = this.romanizer.convertDecimalToRoman(decimal);
                Pair<String,String> pair = new Pair<String,String>(decimal, roman);
                pairs.add(pair);
            }
        } catch (IllegalArgumentException e) {
            logger.info("Failed to convert numeral");
        }
        return pairs;
	}
}
