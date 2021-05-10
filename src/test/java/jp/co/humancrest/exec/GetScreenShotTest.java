package jp.co.humancrest.exec;

import static jp.co.humancrest.core.Config.FILE_PATH;
import static jp.co.humancrest.core.Config.SP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetScreenShotTest {


  private final String path = new File(".").getAbsoluteFile().getParent();
  private final StringBuilder sb = new StringBuilder();
  private final String useURL = sb.append("file://").append(path).append(SP).append("src")
      .append(SP).append("test").append(SP).append("resources").append(SP).append("testSample.html")
      .toString();
  WebDriver webDriver = null;

  @BeforeAll
  static void beforeAll() {
    File testFile = new File(FILE_PATH + "chrome");
    File[] listFiles = testFile.listFiles();
    if (listFiles != null) {
      for (File listFile : listFiles) {
        if (listFile.exists() && listFile.isFile()) {
          listFile.delete();
        }
      }
    }

    File testFile1 = new File(FILE_PATH + "firefox");
    File[] listFiles1 = testFile1.listFiles();
    if (listFiles1 != null) {
      for (File file : listFiles1) {
        if (file.exists() && file.isFile()) {
          file.delete();
        }
      }
    }

    File testFile2 = new File(FILE_PATH + "edge");
    File[] listFiles2 = testFile2.listFiles();
    if (listFiles2 != null) {
      for (File file : listFiles2) {
        if (file.exists() && file.isFile()) {
          file.delete();
        }
      }
    }

  }

  @BeforeEach
  public void setUp() {
    WebDriverManager driverManager = WebDriverManager.chromedriver();
    driverManager.clearResolutionCache();
    driverManager.setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--lang=ja");
    webDriver = new ChromeDriver(options);

  }

  @Test
  public void testPass() {
    GetScreenShot getScreenShot = new GetScreenShot(webDriver);
    getScreenShot.getScreenShot(useURL, "mac", "chrome", "1", "test1");
    File file = new File(FILE_PATH + "chrome" + SP + "1_test1.png");
    assertTrue(file.exists());
    getScreenShot.getScreenShot(useURL, null, "chrome", "1", "test2");
    File file1 = new File(FILE_PATH + "chrome" + SP + "1_test2.png");
    assertTrue(file1.exists());
    getScreenShot.getScreenShot(useURL, "mac", "chrome", "1", null);
    File file2 = new File(FILE_PATH + "chrome" + SP + "1_null.png");
    assertTrue(file2.exists());
  }

  @Test
  public void testEdgePass() {
    if (webDriver != null) {
      webDriver.quit();
    }

    WebDriverManager driverManager = WebDriverManager.edgedriver();
    driverManager.clearResolutionCache();
    driverManager.setup();
    EdgeOptions options = new EdgeOptions();
    options.setHeadless(true);
    webDriver = new EdgeDriver(options);

    GetScreenShot getScreenShot = new GetScreenShot(webDriver);
    getScreenShot.getScreenShot(useURL, "mac", "edge", "2", "test1");
    File file = new File(FILE_PATH + "edge" + SP + "2_test1.png");
    assertTrue(file.exists());
  }

  @Test
  public void testFirefoxPass() {

    if (webDriver != null) {
      webDriver.quit();
    }

    WebDriverManager driverManager = WebDriverManager.firefoxdriver();
    driverManager.clearResolutionCache();
    driverManager.setup();
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");
    options.addArguments("--lang=ja");
    webDriver = new FirefoxDriver(options);

    GetScreenShot getScreenShot = new GetScreenShot(webDriver);
    getScreenShot.getScreenShot(useURL, "mac", "firefox", "3", "test1");
    File file = new File(FILE_PATH + "firefox" + SP + "3_test1.png");
    assertTrue(file.exists());
  }

  @Test
  public void testURLFail() {

    GetScreenShot getScreenShot = new GetScreenShot(webDriver);
    assertThrows(
        InvalidArgumentException.class,
        () -> getScreenShot.getScreenShot("", "mac", "chrome", "4", "test1"));
    File file = new File(FILE_PATH + "chrome" + SP + "4_test1.png");
    assertFalse(file.exists());
  }

  @Test
  public void testBrowserNameNull() {

    GetScreenShot getScreenShot = new GetScreenShot(webDriver);
    assertThrows(NullPointerException.class,
        () -> getScreenShot.getScreenShot(useURL, "mac", null, "5", "test1"));
    File file = new File(FILE_PATH + "null" + SP + "5_test1.png");
    assertFalse(file.exists());
  }

  @AfterEach
  public void tearDown() {
    if (webDriver != null) {
      webDriver.quit();
    }

  }

}
