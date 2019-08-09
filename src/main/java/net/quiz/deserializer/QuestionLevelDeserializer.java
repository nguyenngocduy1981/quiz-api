package net.quiz.deserializer;

import net.quiz.models.QuestionLevel;

public class QuestionLevelDeserializer extends BaseDeserializer<QuestionLevel> {
  @Override
  public QuestionLevel fromText(String text) {
    return QuestionLevel.fromText(text);
  }
}
