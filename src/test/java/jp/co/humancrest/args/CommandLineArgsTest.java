package jp.co.humancrest.args;

import static jp.co.humancrest.core.Config.SP;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.io.File;
import org.junit.jupiter.api.Test;

public class CommandLineArgsTest {

  private final String path = new File(".").getAbsoluteFile().getParent();
  private final StringBuilder sb = new StringBuilder();
  private final String filePath = sb.append(path).append(SP).append("src").append(SP).append("test")
      .append(SP)
      .append("resources").append(SP).append("sample.csv").toString();

  @Test
  public void ParameterPass() {
    CommandLineArgs commandLineArgs = new CommandLineArgs();
    String[] argsTest1 = {"-fn", filePath, "-osn", "mac", "-brn", "firefox"};
    assertDoesNotThrow(
        () -> JCommander.newBuilder().addObject(commandLineArgs).build().parse(argsTest1));
  }

  @Test
  public void ParameterFail() {
    CommandLineArgs commandLineArgs = new CommandLineArgs();
    String[] argsTest2 = {"-fn1", filePath, "-osn", "mac", "-brn",
        "firefox"};
    assertThrows(ParameterException.class,
        () -> JCommander.newBuilder().addObject(commandLineArgs).build().parse(argsTest2));
    String[] argsTest3 = {"-fn", filePath, "-osname", "mac", "-brn",
        "firefox"};
    assertThrows(ParameterException.class,
        () -> JCommander.newBuilder().addObject(commandLineArgs).build().parse(argsTest3));
    String[] argsTest4 = {"-fn", filePath, "-osn", "mac", "-brName",
        "firefox"};
    assertThrows(ParameterException.class,
        () -> JCommander.newBuilder().addObject(commandLineArgs).build().parse(argsTest4));
    String[] argsTest5 = {"-all", filePath, "-osn", "mac", "-brn",
        "firefox"};
    assertThrows(ParameterException.class,
        () -> JCommander.newBuilder().addObject(commandLineArgs).build().parse(argsTest5));
  }

}
