package net.quiz.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Embeddable
public class Option {
  @Column(name = "option_text")
  private String text;

  public Option() {
  }

  public Option(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
