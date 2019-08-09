package net.quiz.service;

import net.quiz.converter.CategoryConverter;
import net.quiz.exception.BadRequestException;
import net.quiz.models.Category;
import net.quiz.payload.dto.CategoryDTO;
import net.quiz.payload.dto.NewCategoryRequest;
import net.quiz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.quiz.constants.Errors.CAT_EXISTED;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public boolean initParent(List<String> names) {
    names.stream().forEach(this::saveParent);
    return true;
  }

  public void saveParent(String name) {
    categoryRepository.save(new Category(name));
  }

  public boolean save(NewCategoryRequest payload) {
    Integer id = payload.getParentId();
    List<Category> list = categoryRepository.findByParent(id);
    long count = list.stream().filter(c -> c.getCatName().equals(payload.getCatName())).count();
    if (count > 0) {
      throw new BadRequestException(CAT_EXISTED);
    }
    Category cat = new Category();
    cat.setCatName(payload.getCatName());
    cat.setParent(id);
    categoryRepository.save(cat);

    return true;
  }

  public List<CategoryDTO> findChildren(int parentId) {
    List<Category> list = categoryRepository.findByParent(parentId);

    return this.toDto(list);
  }

  public CategoryDTO findById(int childId) {
    Category category = categoryRepository.findById(childId).orElseThrow(() -> new BadRequestException("Child category not found: " + childId));
    CategoryDTO child = CategoryConverter.toDTO(category);
    Integer parent = category.getParent();
    if (parent == null) {
      throw new BadRequestException("Cannot get child category without parent ID");
    }

    Category parentCat = categoryRepository.findById(parent).orElseThrow(() -> new BadRequestException("Parent category not found: " + parent));
    child.setParent(CategoryConverter.toDTO(parentCat));

    return child;
  }

  public List<CategoryDTO> findByParentIsNull() {
    List<Category> list = categoryRepository.findByParentIsNull();

    return this.toDto(list);
  }

  private List<CategoryDTO> toDto(List<Category> list) {
    return list.stream()
            .map(CategoryConverter::toDTO)
            .collect(Collectors.toList());
  }
}
