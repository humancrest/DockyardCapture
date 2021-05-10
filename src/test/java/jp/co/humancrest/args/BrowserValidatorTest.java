package jp.co.humancrest.args;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Test;

public class BrowserValidatorTest {

  @Test
  public void ParameterPass() {
    BrowserValidator browserValidator = new BrowserValidator();
    assertDoesNotThrow(() -> browserValidator.validate("--browserName", "chrome"));
    assertDoesNotThrow(() -> browserValidator.validate("--browserName", "firefox"));
    assertDoesNotThrow(() -> browserValidator.validate("--browserName", "edge"));
    assertDoesNotThrow(() -> browserValidator.validate("--browserName", "safari"));
    assertDoesNotThrow(() -> browserValidator.validate("--browserName", "all"));
  }

  @Test
  public void ParameterFail() {
    BrowserValidator browserValidator = new BrowserValidator();
    assertThrows(ParameterException.class,
        () -> browserValidator.validate("--browserName", "miss"));
  }

}
