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
System.out.println("b");
System.out.println("c");
System.out.println("d");
System.out.println("a1");
System.out.println("a2");
System.out.println("a3");

System.out.println("B1");
System.out.println("B2");
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper();
  }
}
