package net.quiz.repository;

import net.quiz.models.QuestionType;
import net.quiz.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SectionRepository extends JpaRepository<Section, Integer> {
  List<Section> findByCategoryId(int categoryId);

  List<Section> findByIdIn(Set<Integer> ids);

  Section findByCheckSum(String checkSum);

  /**
   * Vi khong query duoc "tieng viet" nen where check_sum nay
   */
  Section findByCheckSumAndQuestionType(String checkSum, QuestionType questionType);
}
