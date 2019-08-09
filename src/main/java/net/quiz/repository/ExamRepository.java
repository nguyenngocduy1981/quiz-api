package net.quiz.repository;

import net.quiz.models.Exam;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
  Exam findByTitle(String title);

  @Query(nativeQuery = true, value = "select id, title, approve, token from T_EXAM")
  List<Object[]> findIdAndTitle();

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "update T_EXAM e set e.questions = :questions, token=true where e.id = :id")
  int updateExam(@Param("id") int id, @Param("questions") String questions);

  @Query(nativeQuery = true, value = "select id from T_EXAM where id=:id and token=true")
  Object findIdByIdAndToken(@Param("id") int id);
}
