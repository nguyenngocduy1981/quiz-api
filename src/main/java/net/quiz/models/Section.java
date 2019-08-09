package net.quiz.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "T_SECTION")
public class Section implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "section_name")
  private String sectionName;

  @Column(name = "type_of_question")
  @Enumerated(EnumType.STRING)
  private QuestionType questionType;

  @Column(name = "check_sum")
  private String checkSum;

  @Column(name = "created_on")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn = new Date();

  @ManyToOne
  @JoinColumn(name = "cat_id")
  private Category category;

  @ElementCollection
  @CollectionTable(name = "T_OPTIONS_FOR_SECTION",
          joinColumns = @JoinColumn(name = "section_id"))
  @Column(name = "option_text")
  private List<Option> options; // QuestionType.OPTION_FROM_GIVEN

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "section")
  private Passage passage;

  public Section() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public String getCheckSum() {
    return checkSum;
  }

  public void setCheckSum(String checkSum) {
    this.checkSum = checkSum;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public QuestionType getQuestionType() {
    return questionType;
  }

  public void setQuestionType(QuestionType questionType) {
    this.questionType = questionType;
  }

  /**
   * QuestionType.OPTION_FROM_GIVEN
   */
  public List<Option> getOptions() {
    return options;
  }

  /**
   * QuestionType.OPTION_FROM_GIVEN
   */
  public void setOptions(List<Option> options) {
    this.options = options;
  }

  public Passage getPassage() {
    return passage;
  }

  public void setPassage(Passage passage) {
    this.passage = passage;
  }
}

