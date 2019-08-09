package net.quiz.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.quiz.models.QuestionLevel;
import net.quiz.models.QuestionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

import static net.quiz.models.QuestionLevel.ELEMENTARY_A2;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class NewQuestionRequest {

  @NotNull(message = "1")
  @NotBlank(message = "1")
  private String sectionId;

  @NotNull(message = "2")
  @NotBlank(message = "2")
  private String categoryId;

  // khong phai cau hoi nao cung co passage
  private int passageId;

  private List<QuestionDTO> questions;

  public NewQuestionRequest() {
  }

  public String getSectionId() {
    return sectionId;
  }

  public void setSectionId(String sectionId) {
    this.sectionId = sectionId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public int getPassageId() {
    return passageId;
  }

  public void setPassageId(int passageId) {
    this.passageId = passageId;
  }

  public List<QuestionDTO> getQuestions() {
    return questions;
  }

  public void setQuestions(List<QuestionDTO> questions) {
    this.questions = questions;
  }
}
