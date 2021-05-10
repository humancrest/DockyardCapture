package jp.co.humancrest.args;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.util.Arrays;
import java.util.List;

public class BrowserValidator implements IParameterValidator {

  public void validate(String browserName, String value) throws ParameterException {
    List<String> browserNames = Arrays.asList("chrome", "firefox", "edge", "safari", "all");
    if (!browserNames.contains(value)) {
      throw new ParameterException("Browser name must be a value in " + browserNames);
    }
  }
}
