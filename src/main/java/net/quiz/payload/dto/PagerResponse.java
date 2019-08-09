package net.quiz.payload.dto;

public class PagerResponse {
  private Object data;
  private int pageCount;

  public PagerResponse(Object dataList, int pageCount) {
    this.data = dataList;
    this.pageCount = pageCount;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }
}
