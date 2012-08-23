package springconversio.service;

import java.util.List;

import springconversio.domain.InputNumeral;
import springconversio.domain.Pair;

public interface ConversionService {

	public void convert(InputNumeral numeral);

	public List<Pair<String,String>> fetch();
}
