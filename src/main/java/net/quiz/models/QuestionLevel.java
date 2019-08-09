package net.quiz.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.quiz.deserializer.QuestionLevelDeserializer;

import java.util.Arrays;

@JsonDeserialize(using = QuestionLevelDeserializer.class)
public enum QuestionLevel {
  BEGINNER_A1("BEGINNER/A1"),
  ELEMENTARY_A2("ELEMENTARY/A2"),
  INTERMEDIATE_B1("INTERMEDIATE/B1"),
  UPPER_INTERMEDIATE_B2("UPPER INTERMEDIATE/B2"),
  ADVANCED_C1("ADVANCED/C1"),
  PROFICIENT_C2("PROFICIENT/C2");

  private final String level;

  QuestionLevel(String level) {
    this.level = level;
  }

  public static QuestionLevel fromText(String text) {
    return Arrays.stream(QuestionLevel.values())
            .filter(q -> q.name().equals(text))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Question Level sai"));
  }
}
