package net.quiz.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.quiz.deserializer.QuestionTypeDeserializer;

import java.util.Arrays;

@JsonDeserialize(using = QuestionTypeDeserializer.class)
public enum QuestionType {
  TEXT("TEXT"),
  REORDER_SENTENCE("REORDER_SENTENCE"),

  PASSAGE_TEXT("PASSAGE_TEXT"),
  PASSAGE_OPTION("PASSAGE_OPTION"),
  PASSAGE_OPTION_FROM_GIVEN("PASSAGE_OPTION_FROM_GIVEN"),

  OPTION("OPTION"),
  OPTION_FROM_GIVEN("OPTION_FROM_GIVEN");

  private final String type;

  QuestionType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public static QuestionType fromText(String text) {
    return Arrays.stream(QuestionType.values())
            .filter(q -> q.type.equals(text))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Question Type sai"));
  }
}
