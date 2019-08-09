package net.quiz.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static net.quiz.constants.DateTimeFormat.DDMMYYYY_HHMMSS;
import static net.quiz.constants.DateTimeFormat.YYYYMMDDHHMMSS;

public class DateUtils {
  /**
   * dd/MM/yyyy HHmmss
   */
  public static String getDDMMYYYY_HHMMSS() {
    return convert(DDMMYYYY_HHMMSS);
  }

  /**
   * yyyyMMddHHmmss
   */
  public static String getYYYYMMDDHHMMSS() {
    return convert(YYYYMMDDHHMMSS);
  }

  private static String convert(String format) {
    Calendar cal = Calendar.getInstance();
    return new SimpleDateFormat(format).format(cal.getTime());
  }
}
