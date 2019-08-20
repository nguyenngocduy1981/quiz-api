package net.quiz.controller;

import net.quiz.exception.SystemException;
import net.quiz.payload.dto.NewCategoryRequest;
import net.quiz.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController extends BaseController {

  @Autowired
  private CategoryService categoryService;


  @GetMapping
  public ResponseEntity<Object> findAll() {
    return ok(categoryService.findByParentIsNull());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findChildren(@PathVariable int id) {
//    return ok(categoryService.findChildren(id));
    throw new SystemException("Not support any more");
  }

  @GetMapping("/child/{id}")
  public ResponseEntity<Object> viewById(@PathVariable int id) {
    return ok(categoryService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Object> saveChild(@RequestBody @Valid NewCategoryRequest payload) {
    return ok(categoryService.save(payload));
  }

  @PostMapping("/init")
  public ResponseEntity<Object> initParent(@RequestBody List<String> payload) {
    return ok(categoryService.initParent(payload));
  }

}
