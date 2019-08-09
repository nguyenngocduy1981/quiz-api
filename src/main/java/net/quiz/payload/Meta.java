package net.quiz.payload;

public class Meta {
  private int code;

  public Meta() {
    this.code = 200;
  }

  public Meta(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
