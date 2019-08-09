package net.quiz.converter;

import net.quiz.models.Option;
import net.quiz.models.Passage;
import net.quiz.payload.dto.PassageDTO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PassageConverter {
  public static PassageDTO convert2DTO(Passage pa) {
    if (pa == null) return null;

    PassageDTO dto = new PassageDTO(pa.getId(), pa.getText(), pa.getQuestionType());
    List<Option> options = pa.getOptions();
    if (!CollectionUtils.isEmpty(options)) {
      dto.setOptions(options.stream()
              .map(Option::getText)
              .collect(Collectors.toList()));
    }

    return dto;
  }
}
