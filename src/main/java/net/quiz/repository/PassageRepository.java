package net.quiz.repository;

import net.quiz.models.Passage;
import net.quiz.models.QuestionType;
import net.quiz.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassageRepository extends JpaRepository<Passage, Integer> {
}
