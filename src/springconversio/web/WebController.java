package springconversio.web;

import java.util.List;

import springconversio.domain.InputNumeral;
import springconversio.domain.Pair;

import springconversio.service.ConversionService;
import springconversio.service.InputNumeralValidator;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Controller
@RequestMapping("/convert.htm")
@SessionAttributes("inputNumeral")
public class WebController {
 
	protected final Log logger = LogFactory.getLog(getClass());

	private ConversionService conversionService;

	@Autowired
	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	
	private InputNumeralValidator inputNumeralValidator;

	@Autowired
    private void setInputNumeralValidator(InputNumeralValidator inputNumeralValidator) {
        this.inputNumeralValidator = inputNumeralValidator;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String showConversionForm(ModelMap model) {
		InputNumeral numeral = new InputNumeral();
		model.addAttribute(numeral);
		List<Pair<String,String>> history = conversionService.fetchNumerals();
		model.addAttribute("history", history);
		logger.info("GET " + numeral.getNumeral());
		return "convert";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("inputNumeral") InputNumeral numeral) {
		conversionService.convert(numeral);
		logger.info("POST " + numeral.getNumeral());
		return "redirect:convert.htm";
	}
}