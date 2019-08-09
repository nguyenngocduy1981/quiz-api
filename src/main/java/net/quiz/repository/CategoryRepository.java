package net.quiz.repository;

import net.quiz.models.Category;
import net.quiz.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
  List<Category> findByParentIsNull();

  List<Category> findByParent(int parentId);
}
