package net.quiz.converter;

import net.quiz.models.Option;
import net.quiz.models.QuestionType;
import net.quiz.models.Section;
import net.quiz.payload.dto.SectionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class SectionConverter {
  public static SectionDTO convert2DTO(Section sec) {
    SectionDTO dto = new SectionDTO();
    dto.setId(sec.getId());
    dto.setText(sec.getSectionName());
    dto.setQuestionType(sec.getQuestionType());
    dto.setCategory(sec.getCategory().getCatName());
;
    if (sec.getQuestionType() == QuestionType.OPTION_FROM_GIVEN) {
      List<String> options = sec.getOptions()
              .stream()
              .map(Option::getText)
              .collect(Collectors.toList());
      dto.setOptions(options);
    }

    return dto;
  }
}
