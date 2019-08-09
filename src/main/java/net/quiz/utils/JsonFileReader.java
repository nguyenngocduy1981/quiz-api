package net.quiz.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonFileReader {
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileReader.class);

  public static Map read(String fileName) {
    InputStream in = null;
    try {
      ClassPathResource resource = new ClassPathResource(fileName);

      if (!resource.exists()) {
        throw new RuntimeException(fileName + " is not existed");
      }

      in = resource.getInputStream();
      return new ObjectMapper().readValue(in, Map.class);
    } catch (IOException e) {
      String msg = "Fail to read error mapper file: " + e.getMessage();
      LOGGER.error(msg);
      throw new RuntimeException(msg);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
        }
      }
    }
  }


}
