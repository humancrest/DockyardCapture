package jp.co.humancrest.args;

import static jp.co.humancrest.core.Config.SP;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.beust.jcommander.ParameterException;
import java.io.File;
import org.junit.jupiter.api.Test;

public class ExistFileTest {

  private final String path = new File(".").getAbsoluteFile().getParent();
  private final StringBuilder sb = new StringBuilder();
  private final String filePath = sb.append(path).append(SP).append("src").append(SP).append("test")
      .append(SP)
      .append("resources").append(SP).append("sample.csv").toString();

  @Test
  public void ParameterPass() {
    ExistFile existFile = new ExistFile();
    assertDoesNotThrow(() -> existFile.validate("--fileName", filePath));
  }

  @Test
  public void ParameterFail() {
    ExistFile existFile = new ExistFile();
    assertThrows(ParameterException.class,
        () -> existFile.validate("--fileName", "sample.csv"));
    assertThrows(ParameterException.class,
        () -> existFile.validate("--fileName", "src/test/resources/sample"));
  }

}
