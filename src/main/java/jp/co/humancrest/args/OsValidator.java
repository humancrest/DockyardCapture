package jp.co.humancrest.args;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.util.Arrays;
import java.util.List;

public class OsValidator implements IParameterValidator {

  public void validate(String osName, String value) throws ParameterException {
    List<String> osNames = Arrays.asList("win", "mac", "all");
    if (!osNames.contains(value)) {
      throw new ParameterException("OS name must be a value in " + osNames.toString());
    }
  }
}
