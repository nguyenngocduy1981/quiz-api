package net.quiz.utils;

import java.util.UUID;

public class IdGenerator {
  public static String randomUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
