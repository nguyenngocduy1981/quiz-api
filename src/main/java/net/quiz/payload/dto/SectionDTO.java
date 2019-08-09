package net.quiz.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.quiz.models.QuestionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SectionDTO {
  private Integer id;
  private Integer questionCount;

  @NotNull(message = "1")
  @NotBlank(message = "1")
  private String text;

  @NotNull(message = "2")
  private QuestionType questionType;

  private String category;

  private List<String> options;

  private PassageDTO passage;

  public SectionDTO() {
  }

  public SectionDTO(String text, QuestionType questionType, String category) {
    this.text = text;
    this.questionType = questionType;
    this.category = category;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public QuestionType getQuestionType() {
    return questionType;
  }

  public void setQuestionType(QuestionType questionType) {
    this.questionType = questionType;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public PassageDTO getPassage() {
    return passage;
  }

  public void setPassage(PassageDTO passage) {
    this.passage = passage;
  }
}
