package net.quiz.converter;

import net.quiz.models.Category;
import net.quiz.payload.dto.CategoryDTO;

public class CategoryConverter {
  public static CategoryDTO toDTO(Category cat) {
    return new CategoryDTO(cat.getId(), cat.getCatName());
  }
}
