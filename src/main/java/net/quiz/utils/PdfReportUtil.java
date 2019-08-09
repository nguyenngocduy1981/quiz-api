package net.quiz.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfReportUtil {
  public List<String> splitD(String s) {
    String[] arr = s.split("<br/>");
    List<String> list = new ArrayList<>();
    for (String a : arr) {
      list.add("<fo:block>" + a + "</fo:block>");
    }
    return list;
  }

  public List<String> split(String s) {
    return Arrays.asList(s.split("<br/>"));
  }

  public String vert(String s) {
    return s.replace("<br/>", "<fo:block/>");
  }
}
