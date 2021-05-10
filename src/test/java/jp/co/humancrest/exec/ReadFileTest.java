package jp.co.humancrest.exec;

import static jp.co.humancrest.core.Config.SP;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ReadFileTest {

  private final String path = new File(".").getAbsoluteFile().getParent();

  @ParameterizedTest
  @CsvFileSource(resources = "/sample.csv")
  public void testFileDataNotnull(Integer no, String name, String url) {
    assertNotNull(no);
    assertNotNull(name);
    assertNotNull(url);
  }

  @Test
  public void testFileData() throws IOException {
    StringBuilder sb = new StringBuilder();
    String fileName = sb.append(path).append(SP).append("src").append(SP).append("test").append(SP)
        .append("resources").append(SP).append("sample.csv").toString();
    ReadFile readFile = new ReadFile();
    List<List<String>> expect = Arrays.asList(
        Arrays.asList("0", "hc-top", "https://www.humancrest.co.jp"),
        Arrays.asList("1", "ques-top", "https://quesqa.com/"));
    assertEquals(expect, readFile.execute(fileName));
    assertDoesNotThrow(() -> readFile.execute(fileName));
  }

  @Test
  public void testFileName() {
    String fileName = "sample.csv";
    ReadFile readFile = new ReadFile();
    assertThrows(IOException.class, () -> readFile.execute(fileName));
  }


}
