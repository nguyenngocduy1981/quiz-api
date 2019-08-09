package net.quiz.deserializer;

import net.quiz.models.QuestionType;

public class QuestionTypeDeserializer extends BaseDeserializer<QuestionType> {

  @Override
  public QuestionType fromText(String text) {
    return QuestionType.fromText(text);
  }
}
