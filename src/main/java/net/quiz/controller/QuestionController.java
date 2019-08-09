package net.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.quiz.exception.Valids;
import net.quiz.payload.dto.NewQuestionRequest;
import net.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/questions")
//@Instrumentation.Timer("process questions")
public class QuestionController extends BaseController {

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private QuestionService questionService;

  @GetMapping
  public ResponseEntity<Object> findAll() {
//    return ok(questionService.findSectionQuestionMap());
    return ok("not implemented");
  }

  @GetMapping("/{sectionId}")
  public ResponseEntity<Object> checkByTextAndSectionId(@PathVariable int sectionId, @PathParam(value = "text") String text) {
    return ok(questionService.checkByTextAndSectionId(text, sectionId));
  }

  @GetMapping("/del/{questionId}")
  public ResponseEntity<Object> removeQuestion(@PathVariable int questionId) {
    return ok(questionService.remove(questionId));
  }
//
//  @PostMapping
//  public ResponseEntity<Object> save(@RequestBody @Valid NewQuestionRequest payload) {
//    return ok(questionService.save(payload));
//  }

  @PostMapping("/bulk")
  @Valids
  public ResponseEntity<Object> save(@RequestBody @Valid NewQuestionRequest payload) {
    return ok(questionService.save(payload));
  }

}
