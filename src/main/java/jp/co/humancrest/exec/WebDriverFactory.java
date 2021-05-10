package jp.co.humancrest.exec;

import com.beust.jcommander.ParameterException;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.logging.Logger;
import lombok.Builder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

@Builder
public class WebDriverFactory {

  private static final Logger logger = Logger.getLogger(WebDriverFactory.class.getName());

  public WebDriver start(String osName, String browserName) {
    WebDriver webDriver = null;

    switch (browserName) {
      case "chrome" : {
        WebDriverManager driverManager = WebDriverManager.chromedriver();
        driverManager.clearResolutionCache();
        driverManager.setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--lang=ja");
        options.addArguments("--window-size=1920,1080");
        webDriver = new ChromeDriver(options);
      }
      break;
      case "firefox" : {
        WebDriverManager driverManager = WebDriverManager.firefoxdriver();
        driverManager.clearResolutionCache();
        driverManager.setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--lang=ja");
        options.addArguments("--window-size=1920,1080");
        webDriver = new FirefoxDriver(options);
      }
      break;
      case "edge" : {
        WebDriverManager driverManager = WebDriverManager.edgedriver();
        driverManager.clearResolutionCache();
        driverManager.setup();
        EdgeOptions options = new EdgeOptions();
        options.setHeadless(true);
        options.addArguments("--window-size=1920,1080");
        webDriver = new EdgeDriver(options);
      }
      break;
      case "safari" : {
        if (System.getProperty("os.name").toLowerCase().contains("mac") && osName.equals("mac")) {
          webDriver = new SafariDriver();
        } else {
          throw new ParameterException("The os doesn't have safari.");
        }

      }
      break;
    }
    return webDriver;
  }
}
