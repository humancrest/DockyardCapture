package jp.co.humancrest.exec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


public class WebDriverFactoryTest {

  private static WebDriver webDriver;

  @Test
  public void testChrome() {
    WebDriverFactory webDriverFactory = WebDriverFactory.builder().build();
    webDriver = webDriverFactory.start("mac", "chrome");
    assertEquals("ChromeDriver", webDriver.getClass().getSimpleName());

  }

  @Test
  public void testFirefox() {
    WebDriverFactory webDriverFactory = WebDriverFactory.builder().build();
    webDriver = webDriverFactory.start("mac", "firefox");
    assertEquals("FirefoxDriver", webDriver.getClass().getSimpleName());
  }

  @Test
  public void testEdge() {
    WebDriverFactory webDriverFactory = WebDriverFactory.builder().build();
    webDriver = webDriverFactory.start("mac", "edge");
    assertEquals("EdgeDriver", webDriver.getClass().getSimpleName());
  }

  @Test
  public void testSafariMac() {
    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
      WebDriverFactory webDriverFactory = WebDriverFactory.builder().build();
      webDriver = webDriverFactory.start("mac", "safari");
      assertEquals("SafariDriver", webDriver.getClass().getSimpleName());
    } else {
      assertTrue(true);
    }

  }

  @Test
  public void testSafariWin() {
    WebDriverFactory webDriverFactory = WebDriverFactory.builder().build();
    Exception exception = assertThrows(ParameterException.class,
        () -> webDriverFactory.start("win", "safari"));
    assertEquals("The os doesn't have safari.", exception.getMessage());
  }

  @Test
  public void testNull() {
    WebDriverFactory webDriverFactory = WebDriverFactory.builder().build();
    webDriver = webDriverFactory.start("mac", "safari1");
    assertThrows(NullPointerException.class, () -> webDriver.getClass().getSimpleName());
  }


  @AfterEach
  public void tear_down() {
    if (webDriver != null) {
      webDriver.quit();
    }
  }

}
