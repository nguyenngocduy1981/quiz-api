package net.quiz.payload.report;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Report {

  private List<ReportItem> item;

  public Report() {
  }

  public Report(List<ReportItem> item) {
    this.item = item;
  }

  public List<ReportItem> getItem() {
    return item;
  }

  public void setItem(List<ReportItem> item) {
    this.item = item;
  }
}
