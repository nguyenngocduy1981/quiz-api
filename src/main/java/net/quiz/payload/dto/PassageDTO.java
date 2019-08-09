package net.quiz.payload.dto;

import net.quiz.models.QuestionType;

import java.util.List;

public class PassageDTO {

  private Integer id;
  private String text;
  private QuestionType questionType;
  private List<String> options;

  public PassageDTO() {
  }

  public PassageDTO(Integer id, String text, QuestionType questionType) {
    this.id = id;
    this.text = text;
    this.questionType = questionType;
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

  public List<String> getOptions() {
    return options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }
}
