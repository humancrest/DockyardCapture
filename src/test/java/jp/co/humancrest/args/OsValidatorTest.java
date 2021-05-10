package jp.co.humancrest.args;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;

public class OsValidatorTest {

  @Test
  public void ParameterPass() {
    OsValidator osValidator = new OsValidator();
    assertDoesNotThrow(() -> osValidator.validate("--osName", "win"));
    assertDoesNotThrow(() -> osValidator.validate("--osName", "mac"));
    assertDoesNotThrow(() -> osValidator.validate("--osName", "all"));
  }

  @Test
  public void ParameterFail() {
    OsValidator osValidator = new OsValidator();
    assertThrows(ParameterException.class,
        () -> osValidator.validate("--osName", "Windows10"));
  }
}
