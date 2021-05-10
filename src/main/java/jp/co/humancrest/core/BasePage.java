package jp.co.humancrest.core;

import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;

public class BasePage {

  private static final Logger logger = Logger.getLogger(BasePage.class.getName());
  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public BasePage() {
  }

  protected void openURL(String url) {
    openURL(url, "Open URL: " + url);
  }

  protected void openURL(String url, String message) {
    logger.info(message);
    driver.get(url);
  }

}
