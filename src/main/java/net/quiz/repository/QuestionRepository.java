package net.quiz.repository;

import net.quiz.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
  int countBySectionId(Integer sectionId);

  /**
   * Khi luu, column check_sum = md5(question_text + sectionId) --> de dam bao trong 1 section khong co 2 question trung nhau
   */
  Question findByCheckSum(String checkSum);

  List<Question> findByIdIn(List<Integer> ids);

  Page<Question> findBySectionId(Integer sectionId, Pageable pager);
}
