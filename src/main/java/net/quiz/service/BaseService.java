package net.quiz.service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class BaseService {
  protected final int noOfRowInPage = 10;
  protected String md5(String text) {
    return Hashing.md5()
            .hashString(text, StandardCharsets.UTF_8)
            .toString();
  }

}
