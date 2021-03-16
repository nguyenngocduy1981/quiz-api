package net.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
System.out.println("1111A");
System.out.println("2222B");
System.out.println("a");
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper();
  }
}
