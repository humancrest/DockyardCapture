package jp.co.humancrest.args;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class CommandLineArgs {

  @Parameter(names = {"--fileName", "-fn"},
      description = "Path to file contain CSV file.",
      required = true,
      validateWith = ExistFile.class)
  protected String fileName;

  @Parameter(names = {"--osName", "-osn"},
      description = "This script runs this os.",
      required = true,
      validateWith = OsValidator.class)
  protected String osName;

  @Parameter(names = {"--browserName", "-brn"},
      description = "This script runs this browser.",
      required = true,
      validateWith = BrowserValidator.class)
  protected String browserName;

}
