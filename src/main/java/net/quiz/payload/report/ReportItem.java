package net.quiz.payload.report;

import net.quiz.payload.dto.QuestionDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ReportItem {

  private int sectionId;
  private String sectionName;
  private String type;
  private List<String> option1; // used for OPTION_FROM_GIVEN
  private List<String> option2; // used for OPTION_FROM_GIVEN

  private List<QuestionDTO> question;

  public ReportItem() {
  }

  public ReportItem(int sectionId, String sectionName) {
    this.sectionId = sectionId;
    this.sectionName = sectionName;
  }

  public int getSectionId() {
    return sectionId;
  }

  public void setSectionId(int sectionId) {
    this.sectionId = sectionId;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public List<QuestionDTO> getQuestion() {
    return question;
  }

  public void setQuestion(List<QuestionDTO> question) {
    this.question = question;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<String> getOption1() {
    return option1;
  }

  public void setOption1(List<String> option1) {
    this.option1 = option1;
  }

  public List<String> getOption2() {
    return option2;
  }

  public void setOption2(List<String> option2) {
    this.option2 = option2;
  }
}
