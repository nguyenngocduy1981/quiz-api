package net.quiz.constants;

import net.quiz.models.QuestionType;

import java.util.Arrays;
import java.util.List;

public class QuestionConstants {
  public static List<QuestionType> TEXT_TYPES = Arrays.asList(
          QuestionType.TEXT,
          QuestionType.PASSAGE_TEXT,
          QuestionType.PASSAGE_OPTION_FROM_GIVEN,
          QuestionType.OPTION_FROM_GIVEN
  );

  public static List<QuestionType> OPTION_TYPES = Arrays.asList(
          QuestionType.PASSAGE_OPTION,
          QuestionType.OPTION
  );
  public static List<QuestionType> PASSAGE_TYPES = Arrays.asList(
          QuestionType.PASSAGE_TEXT,
          QuestionType.PASSAGE_OPTION,
          QuestionType.PASSAGE_OPTION_FROM_GIVEN
  );
  public static List<QuestionType> WITH_ANSWER_TYPES = Arrays.asList(
          QuestionType.TEXT,
          QuestionType.OPTION,
          QuestionType.PASSAGE_OPTION,
          QuestionType.REORDER_SENTENCE,
          QuestionType.PASSAGE_TEXT,
          QuestionType.PASSAGE_OPTION_FROM_GIVEN,
          QuestionType.OPTION_FROM_GIVEN
  );
}
