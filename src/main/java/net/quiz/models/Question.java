package net.quiz.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "T_QUESTION")
public class Question implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "question_text")
  private String text;

  @Column(name = "check_sum")
  private String checkSum;

  @Column(name = "created_on")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn = new Date();

  @Column(name = "_level")
  @Enumerated(EnumType.STRING)
  private QuestionLevel level = QuestionLevel.BEGINNER_A1;

  @Column(name = "question_type")
  @Enumerated(EnumType.STRING)
  private QuestionType type;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "question")
  private Answer answer;

  @ElementCollection
  @CollectionTable(name = "T_POSSIBLE_ANSWER",
          joinColumns = @JoinColumn(name = "question_id"))
  @Column(name = "possible_answer_text")
  private List<PossibleAnswer> possibleAnswers;

  @ManyToOne
  @JoinTable(name = "T_PASSAGE_QUESTION",
          joinColumns = {
                  @JoinColumn(name = "question_id", referencedColumnName = "id")},
          inverseJoinColumns = {
                  @JoinColumn(name = "passage_id", referencedColumnName = "section_id")})
  private Passage passage;

  @ManyToOne
  @JoinColumn(name = "section_id", referencedColumnName = "id")
  private Section section;

  public Question() {
  }

  public Question(Integer id) {
    this.id = id;
  }

  public Question(String text) {
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

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Answer getAnswer() {
    return answer;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
  }

  public List<PossibleAnswer> getPossibleAnswers() {
    return possibleAnswers;
  }

  public void setPossibleAnswers(List<PossibleAnswer> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }

  public Section getSection() {
    return section;
  }

  public void setSection(Section section) {
    this.section = section;
  }

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  public QuestionLevel getLevel() {
    return level;
  }

  public void setLevel(QuestionLevel level) {
    this.level = level;
  }

  public String getCheckSum() {
    return checkSum;
  }

  public void setCheckSum(String checkSum) {
    this.checkSum = checkSum;
  }

  public Passage getPassage() {
    return passage;
  }

  public void setPassage(Passage passage) {
    this.passage = passage;
  }
}
