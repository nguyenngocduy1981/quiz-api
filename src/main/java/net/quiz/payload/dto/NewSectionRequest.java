package net.quiz.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.quiz.models.QuestionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewSectionRequest {
  private Integer id;

  @NotNull(message = "1")
  @NotBlank(message = "1")
  private String text;

  @NotNull(message = "2")
  private QuestionType questionType;

  private int categoryId;

  private List<String> sectionOptions;

  private String passageText;
  private List<String> passageOptions;

  public NewSectionRequest() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public QuestionType getQuestionType() {
    return questionType;
  }

  public void setQuestionType(QuestionType questionType) {
    this.questionType = questionType;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public List<String> getSectionOptions() {
    return sectionOptions;
  }

  public void setSectionOptions(List<String> sectionOptions) {
    this.sectionOptions = sectionOptions;
  }

  public List<String> getPassageOptions() {
    return passageOptions;
  }

  public void setPassageOptions(List<String> passageOptions) {
    this.passageOptions = passageOptions;
  }

  public String getPassageText() {
    return passageText;
  }

  public void setPassageText(String passageText) {
    this.passageText = passageText;
  }
}
