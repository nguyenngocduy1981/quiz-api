package net.quiz.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "T_PASSAGE")
public class Passage implements Serializable {
  @Id
  @Column(name = "section_id")
  private Integer id;

  @Column(name = "passage_text")
  private String text;

  @Column(name = "type_of_question")
  @Enumerated(EnumType.STRING)
  private QuestionType questionType;

  @ElementCollection
  @CollectionTable(name = "T_OPTIONS_FOR_PASSAGE",
          joinColumns = @JoinColumn(name = "passage_id"))
  @Column(name = "option_text")
  private List<Option> options; // QuestionType.PASSAGE_OPTION_FROM_GIVEN

  @OneToOne
  @MapsId
  private Section section;

  public Passage() {
  }

  public Passage(String text) {
    this.text = text;
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

  public List<Option> getOptions() {
    return options;
  }

  public void setOptions(List<Option> options) {
    this.options = options;
  }

  public QuestionType getQuestionType() {
    return questionType;
  }

  public void setQuestionType(QuestionType questionType) {
    this.questionType = questionType;
  }

  public Section getSection() {
    return section;
  }

  public void setSection(Section section) {
    this.section = section;
  }
}
