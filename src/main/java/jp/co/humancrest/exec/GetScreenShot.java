package jp.co.humancrest.exec;

import static jp.co.humancrest.core.Config.FILE_PATH;
import static jp.co.humancrest.core.Config.INT_WIDTH;
import static jp.co.humancrest.core.Config.SP;
import static jp.co.humancrest.core.Config.WAIT_TIME;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.logging.Logger;
import jp.co.humancrest.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetScreenShot extends BasePage {

  private static final Logger logger = Logger.getLogger(GetScreenShot.class.getName());

  public GetScreenShot(WebDriver driver) {
    super(driver);
  }

  public void getScreenShot(String url, String osName, String browserName, String seq,
      String objectName) {

    //TODO: URL Open
    getURL(url);

    By locator = By.tagName("body");
    WebElement elm = driver.findElement(locator);

    //TODO: Wait 10s.
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

    //TODO: Scroll
    ScrollButton(browserName, elm);

    //TODO: Set WindowsSize.
    Dimension idealWindowsSize = SetIdealWindowSize(elm, browserName);
    driver.manage().window().setSize(idealWindowsSize);

    //TODO: Get ScreenShot
    String filePath = FILE_PATH + SP + browserName + SP;
    StringBuilder fileName = new StringBuilder();
    fileName.append(seq).append("_").append(objectName).append(".png");
    Path CapturePath = Paths.get(filePath, fileName.toString());
    File newDir = new File(filePath);
    if (!newDir.exists()) {
      newDir.mkdirs();
    }
    try {
      Files.write(CapturePath, elm.getScreenshotAs(OutputType.BYTES));
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.info("Done: Get ScreenShot. " + seq);
  }

  private void getURL(String url) {
    openURL(url);
  }

  private void ScrollButton(String browserName, WebElement elm) {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
    String Script0 = "window.scrollTo(0, document.body.scrollHeight/2);";
    javascriptExecutor.executeScript(Script0);
    String Script1 = "window.scrollTo(0, document.body.scrollHeight);";
    javascriptExecutor.executeScript(Script1);

    //TODO: Return Upon.
    if (browserName.equals("edge")) {
      String Script2 = "window.scrollTo(0, 0);";
      javascriptExecutor.executeScript(Script2);
    } else {
      elm.sendKeys(Keys.HOME);
    }

  }

  private Dimension SetIdealWindowSize(WebElement element, String browserName) {

    Dimension BodyWidthSize = element.getSize();
    int BodyHeight;
    if (browserName.equals("firefox") || browserName.equals("chrome")) {
      BodyHeight = BodyWidthSize.getHeight() + 350;

    } else {
      BodyHeight = BodyWidthSize.getHeight();
    }
    return new Dimension(INT_WIDTH, BodyHeight);
  }

}
