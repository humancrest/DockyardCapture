package jp.co.humancrest.args;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.io.File;

public class ExistFile implements IParameterValidator {

  public void validate(String filename, String value)
      throws ParameterException {
    File dir = new File(value);
    if (!dir.isFile()) {
      throw new ParameterException(
          "Parameter." + filename + "should be a valid file (found" + value + "is not a file)");
    }
  }
}
