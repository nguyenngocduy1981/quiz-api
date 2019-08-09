package net.quiz.payload.dto;

import java.util.List;

public class GetQuestionBySectionResponse {
  private SectionDTO section;
  private PassageDTO passage;
  private List<QuestionDTO> questions;

  public GetQuestionBySectionResponse(SectionDTO section) {
    this.section = section;
  }

  public SectionDTO getSection() {
    return section;
  }

  public void setSection(SectionDTO section) {
    this.section = section;
  }

  public List<QuestionDTO> getQuestions() {
    return questions;
  }

  public void setQuestions(List<QuestionDTO> questions) {
    this.questions = questions;
  }

  public PassageDTO getPassage() {
    return passage;
  }

  public void setPassage(PassageDTO passage) {
    this.passage = passage;
  }
}
