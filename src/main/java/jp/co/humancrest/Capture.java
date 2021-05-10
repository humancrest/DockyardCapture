package jp.co.humancrest;

import com.beust.jcommander.JCommander;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import jp.co.humancrest.args.CommandLineArgs;
import jp.co.humancrest.exec.GetScreenShot;
import jp.co.humancrest.exec.ReadFile;
import jp.co.humancrest.exec.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class Capture {

  private static final Logger logger = Logger.getLogger(Capture.class.getName());
  private static WebDriver webDriver;

  public void start(String[] args) throws IOException {

    //TODO: Get path from command line parameter
    CommandLineArgs cmdArgs = new CommandLineArgs();
    JCommander.newBuilder()
        .addObject(cmdArgs)
        .build()
        .parse(args);

    String fileName = cmdArgs.getFileName();
    String osName = cmdArgs.getOsName();
    String browserName = cmdArgs.getBrowserName();
    logger.info("Get path from command line parameter");

    //TODO: Read csv file
    ReadFile readFile = new ReadFile();
    List<List<String>> fileInfo = readFile.execute(fileName);
    logger.info(fileInfo.toString());

    //TODO: Get screenshot
    try {

      fileInfo.forEach((inputURL) -> {
        WebDriverFactory webDriverFactory = WebDriverFactory
            .builder()
            .build();
        webDriver = webDriverFactory.start(osName, browserName);
        GetScreenShot getScreenShot = new GetScreenShot(webDriver);
        getScreenShot
            .getScreenShot(inputURL.get(2), osName, browserName, inputURL.get(0), inputURL.get(1));
        webDriver.quit();
      });
    } catch (Exception e) {
      e.printStackTrace();
      if (webDriver != null) {
        webDriver.quit();
      }
    }

  }
}
