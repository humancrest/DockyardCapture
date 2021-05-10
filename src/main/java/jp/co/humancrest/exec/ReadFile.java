package jp.co.humancrest.exec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile {

  private static final String COMMA_DELIMITER = ",";

  public List<List<String>> execute(String fileName) throws IOException {
    List<List<String>> result = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        result.add(Arrays.asList(values));
      }
    }
    return result;
  }
}
