package net.quiz.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "T_ANSWER")
public class Answer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "question_id")
  private Integer questionId;

  @Column(name = "answer_text")
  private String text;

  @OneToOne
  @MapsId
  private Question question;

  public Answer() {
  }

  public Answer(String text) {
    this.text = text;
  }
  public Answer(String text, Question question) {
    this.text = text;
    this.question = question;
  }

  //  public Answer(Integer questionId, String text) {
//    this.questionId = questionId;
//    this.text = text;
//  }

  public Integer getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Integer questionId) {
    this.questionId = questionId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }
}
