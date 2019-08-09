package net.quiz.controller;

import net.quiz.exception.BadRequestException;
import net.quiz.payload.dto.ExamDTO;
import net.quiz.payload.report.ReportExport;
import net.quiz.service.ExamService;
import net.quiz.service.ReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reports")
public class ReporterController extends BaseController {

  @Autowired
  private ExamService examService;

  @Autowired
  private ReporterService reporterService;

  @GetMapping
  public ResponseEntity<Object> findAll() {
    return ok(examService.findIdAndTitle());
  }

  @GetMapping("/pdf/{id}")
  public ResponseEntity<byte[]> exportPdf(@PathVariable int id) {
    ReportExport rp = reporterService.export(id);
    String name = String.format("%s.json", rp.getTitle());
    return this.export(rp.getContent(), name, MediaType.APPLICATION_PDF);
  }

  @GetMapping("/json/{id}")
  public ResponseEntity<byte[]> exportJson(@PathVariable int id) {
    ExamDTO exam = examService.findById(id);
    String name = String.format("%s.json", exam.getTitle());
    return this.export(exam.getQuestions().getBytes(), name, MediaType.APPLICATION_JSON);
  }

  private ResponseEntity<byte[]> export(byte[] contents, String name, MediaType mediaType) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentLength(contents.length);
      headers.setContentType(mediaType);
      headers.setContentDispositionFormData("ten_file", name);
      return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    } catch (Exception e) {
      throw new BadRequestException("Loi khi tao exam");
    }
  }
}
