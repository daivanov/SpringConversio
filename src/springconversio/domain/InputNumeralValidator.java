package springconversio.domain;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class InputNumeralValidator implements Validator {
    private int DEFAULT_MIN_VALUE = 1;
    private int DEFAULT_MAX_VALUE = 3000;

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return InputNumeral.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
    	InputNumeral in = (InputNumeral) obj;
        if (in == null) {
            errors.rejectValue("numeral", "error.not-digit", null, "Value required.");
        }
        else {
            try {
            	Integer number = Integer.valueOf(in.getNumeral());
                logger.info("Validating " + in.getNumeral());
                if (number <= DEFAULT_MIN_VALUE) {
                    errors.rejectValue("numeral", "error.too-low",
                        new Object[] {new Integer(DEFAULT_MIN_VALUE)}, "Value too low.");
                }
                if (number > DEFAULT_MAX_VALUE) {
                    errors.rejectValue("numeral", "error.too-high",
                        new Object[] {new Integer(DEFAULT_MAX_VALUE)}, "Value too high.");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("numeral", "error.not-digit", null, "Value required.");
            }
        }
    }
}
