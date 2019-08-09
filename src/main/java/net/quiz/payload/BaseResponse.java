package net.quiz.payload;

public class BaseResponse {

  private Object data;

  public BaseResponse() {
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}