package net.quiz.converter;

import net.quiz.models.*;
import net.quiz.payload.dto.QuestionDTO;

import java.util.List;
import java.util.stream.Collectors;

import static net.quiz.constants.QuestionConstants.OPTION_TYPES;
import static net.quiz.models.QuestionType.OPTION_FROM_GIVEN;

public class QuestionConverter {
  public static PossibleAnswer toPossibleAnswerEntity(String possibleAnswerText) {
    return new PossibleAnswer(possibleAnswerText);
  }

//  public static Passage toPassage(PassageDTO dto) {
//    return new Passage(dto.getAnswer(), dto.getQuestionNo());
//  }
//
//  public static PassageDTO toPassageDTO(Passage pa) {
//    return new PassageDTO(pa.getAnswer(), pa.getQuestionNo());
//  }

  public static QuestionDTO convert2DTO(Question ques) {
    QuestionDTO dto = new QuestionDTO();

    QuestionType type = ques.getType();
    dto.setId(ques.getId());
    dto.setType(type);
    dto.setText(ques.getText());

    //cac loai cau hoi != OPTION se phai co answer
    Answer answer = ques.getAnswer();
    if (answer != null) {
      dto.setAnswer(answer.getText());
    }

    if (OPTION_TYPES.contains(type)) {
      dto.setPossibleAnswers(ques.getPossibleAnswers()
              .stream()
              .map(PossibleAnswer::getText)
              .collect(Collectors.toList()));
    }

    Section section = ques.getSection();
//    dto.setSectionId(section.getId());
//    dto.setSectionName(section.getSectionName());
    if (OPTION_FROM_GIVEN == section.getQuestionType()) {
      List<String> options = section.getOptions().stream()
              .map(Option::getText)
              .collect(Collectors.toList());
//      dto.setOptions(options);
    }

    return dto;
  }

  /**
   * Only set Question's columns
   */
  public static Question convert2Entity(QuestionDTO dto) {
    Question ques = new Question();
    ques.setText(dto.getText());
    ques.setLevel(dto.getLevel());

    return ques;
  }
}
