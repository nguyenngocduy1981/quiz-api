package net.quiz.payload.report;

public class ReportExport {

  private String title;
  private byte[] content;

  public ReportExport() {
  }

  public ReportExport(String fileName, byte[] content) {
    this.title = fileName;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }
}
