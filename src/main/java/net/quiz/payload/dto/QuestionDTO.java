package net.quiz.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import net.quiz.models.QuestionLevel;
import net.quiz.models.QuestionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static net.quiz.models.QuestionLevel.ELEMENTARY_A2;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement
public class QuestionDTO {

  private Integer id;

  @NotNull(message = "1")
  @NotBlank(message = "1")
  private String text;

  private QuestionLevel level = ELEMENTARY_A2;

  @NotNull(message = "1")
  private QuestionType type;

  /**
   * right value cua Answer hoac PossibleAnswer<br/>
   * Neu TYPE kieu la free TEXt thi answer la dap an dung. Neu dap an don gian thi field nay empty cung duoc
   */
  private String answer;

  private List<String> possibleAnswers;

  public QuestionDTO() {
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

  public QuestionLevel getLevel() {
    return level;
  }

  public void setLevel(QuestionLevel level) {
    this.level = level;
  }

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  /**
   * right value cua Answer hoac PossibleAnswer<br/>
   * Neu TYPE kieu la free TEXt thi answer la dap an dung. Neu dap an don gian thi field nay empty cung duoc
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * right value cua Answer hoac PossibleAnswer<br/>
   * Neu TYPE kieu la free TEXt thi answer la dap an dung. Neu dap an don gian thi field nay empty cung duoc
   */
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public List<String> getPossibleAnswers() {
    return possibleAnswers;
  }

  public void setPossibleAnswers(List<String> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }
}
