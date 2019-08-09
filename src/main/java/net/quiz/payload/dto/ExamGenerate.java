package net.quiz.payload.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public class ExamGenerate {

  @NotNull(message = "1")
  @NotEmpty(message = "1")
  private String title;

  private Map<Integer, List<Integer>> payload; // Map<SectionId, ListOfQuesId>

  public ExamGenerate() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Map<Integer, List<Integer>> getPayload() {
    return payload;
  }

  public void setPayload(Map<Integer, List<Integer>> payload) {
    this.payload = payload;
  }
}
