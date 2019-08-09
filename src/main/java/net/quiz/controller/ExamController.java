package net.quiz.controller;

import net.quiz.exception.SystemException;
import net.quiz.payload.dto.ExamDTO;
import net.quiz.payload.dto.ExamGenerate;
import net.quiz.service.ExamService;
import net.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/exams")
public class ExamController extends BaseController {

  @Autowired
  private QuestionService questionService;

  @Autowired
  private ExamService examService;

  @GetMapping
  public ResponseEntity<Object> findAll() {
    return ok(examService.findIdAndTitle());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> viewExam(@PathVariable int id) {
    return ok(examService.findById(id));
  }

  @PostMapping("/{id}")
  public ResponseEntity<Object> saveExam(@PathVariable int id, @RequestBody String questions) {
    return ok(examService.updateExam(id, questions));
  }

  @PostMapping()
  public ResponseEntity<Object> generateExam(@RequestBody ExamDTO exam) {
    return ok(examService.findAndSave(exam));
  }

  @PostMapping("/generate")
  public ResponseEntity<Object> generateExam(@RequestBody @Valid ExamGenerate exam) {
    return ok(questionService.generateExam(exam));
  }

  @PostMapping("/upload")
  public ResponseEntity<Object> generateExam(@RequestParam(value = "name") String name, @RequestParam("file") MultipartFile file) {
    throw new SystemException("KHong support nua");
//    try {
//      name = name.toLowerCase().replace(".json", "");
//      InputStream in = file.getInputStream();
//      List<GetQuestionBySectionResponse> questions = mapper.readValue(in, List.class);
//      questionService.saveExam(questions, name);
//
//      return ok("ok");
//    } catch (Exception e) {
//      throw new SystemException(e.getMessage());
//    }
  }

  @PostMapping("/preview")
  public ResponseEntity<Object> previewExam(@RequestBody Map<Integer, List<Integer>> payload) {
    return ok(questionService.findBySectionQuesIdList(payload));
  }

}
