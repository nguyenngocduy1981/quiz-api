package net.quiz.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDTO {
  @NotNull(message = "1")
  @NotBlank(message = "1")
  private String id;
  private String title;
  private String questions; // JSON value
  private boolean approve;
  private boolean token;

  public ExamDTO() {
  }

  public ExamDTO(String title) {
    this.title = title;
  }

  public ExamDTO(String title, String questions) {
    this.title = title;
    this.questions = questions;
  }

  public ExamDTO(String title, String questions, boolean approve, boolean token) {
    this.title = title;
    this.questions = questions;
    this.approve = approve;
    this.token = token;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getQuestions() {
    return questions;
  }

  public void setQuestions(String questions) {
    this.questions = questions;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isApprove() {
    return approve;
  }

  public void setApprove(boolean approve) {
    this.approve = approve;
  }

  public boolean isToken() {
    return token;
  }

  public void setToken(boolean token) {
    this.token = token;
  }
}
