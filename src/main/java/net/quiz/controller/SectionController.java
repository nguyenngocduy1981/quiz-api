package net.quiz.controller;

import net.quiz.payload.dto.NewSectionRequest;
import net.quiz.payload.dto.SectionDTO;
import net.quiz.service.QuestionService;
import net.quiz.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/v1/sections")
//@Instrumentation.Timer("process sections")
public class SectionController extends BaseController {

  @Autowired
  private QuestionService questionService;

  @Autowired
  private SectionService sectionService;

  @GetMapping
  public ResponseEntity<Object> findAll() {
    return ok(sectionService.findAll());
  }

  @GetMapping("/by-cat/{id}")
  public ResponseEntity<Object> findByCategory(@PathVariable(value = "id") int id) {
    return ok(sectionService.findByCategoryId(id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findId(@PathVariable(value = "id") int id) {
    return ok(sectionService.findById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
    return ok(sectionService.remove(id));
  }

  @GetMapping("/{id}/questions")
  public ResponseEntity<Object> findBySectionId(@PathVariable(value = "id") int id, @PathParam(value = "p") int p) {
    return ok(questionService.findBySectionId(id, p));
  }

  @PostMapping("/check")
  public ResponseEntity<Object> checkExisted(@RequestBody @Valid SectionDTO dto) {
    System.out.println("HHHH: " + dto.getText());
    return ok(sectionService.checkExisted(dto));
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody List<NewSectionRequest> payload) {
    return ok(sectionService.save(payload));
  }

}
