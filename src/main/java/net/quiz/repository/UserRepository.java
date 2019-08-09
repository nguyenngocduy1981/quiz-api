package net.quiz.repository;

import net.quiz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUserId(String userId);
}
