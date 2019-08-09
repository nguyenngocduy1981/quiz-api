package net.quiz.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_EXAM")
public class Exam implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "questions")
  private String questions; // JSON value

  private boolean approve;
  private boolean token;// exam is token

  public Exam() {
  }

  public Exam(String title, String questions) {
    this.title = title;
    this.questions = questions;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
