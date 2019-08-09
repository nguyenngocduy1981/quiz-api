package net.quiz.models;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class PossibleAnswer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "possible_answer_text")
  private String text;

  public PossibleAnswer() {
  }

  public PossibleAnswer(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
