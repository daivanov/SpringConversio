package springconversio.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InputNumeral {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private String numeral;

    public void setNumeral(String value) {
        numeral = value;
        logger.info("Numeral set to " + value);
    }

    public String getNumeral() {
        return numeral;
    }

}