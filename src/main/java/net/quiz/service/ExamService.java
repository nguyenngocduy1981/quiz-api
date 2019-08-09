package net.quiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.quiz.exception.BadRequestException;
import net.quiz.exception.SystemException;
import net.quiz.models.Exam;
import net.quiz.payload.dto.ExamDTO;
import net.quiz.payload.dto.GetQuestionBySectionResponse;
import net.quiz.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static net.quiz.constants.Errors.EXAM_TOKEN_ALREADY;

@Service
@Transactional
public class ExamService {

  @Autowired
  private ExamRepository examRepository;

  @Autowired
  private ObjectMapper objectMapper;

  public ExamDTO save(ExamDTO dto) {
    Exam exam = new Exam(dto.getTitle(), dto.getQuestions());
    examRepository.save(exam);

    return dto;
  }

  public boolean findAndSave(ExamDTO dto) {
    Integer id = new Integer(dto.getId());
    Exam exam = examRepository.findById(id).orElseThrow(() -> new BadRequestException(404, "Not found"));
    exam.setQuestions(dto.getQuestions());
    exam.setApprove(true);
    examRepository.save(exam);

    return true;
  }

  public ExamDTO findById(int id) {
    Exam exam = examRepository.findById(id).orElseThrow(() -> new BadRequestException(404, "Not found"));
    return new ExamDTO(exam.getTitle(), exam.getQuestions(), exam.isApprove(), exam.isToken());
  }

  public int updateExam(int id, String questions) {
    if (examRepository.findIdByIdAndToken(id) != null) {
      throw new SystemException(EXAM_TOKEN_ALREADY);
    }
    return examRepository.updateExam(id, questions);
  }

  public List<ExamDTO> findIdAndTitle() {
    List<Object[]> list = examRepository.findIdAndTitle();
    return list.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
  }

  private ExamDTO toDto(Object[] arr) {
    ExamDTO dto = new ExamDTO(arr[1].toString());
    dto.setId(arr[0].toString());
    dto.setApprove(Boolean.valueOf(arr[2].toString()));
    dto.setToken(Boolean.valueOf(arr[3].toString()));

    return dto;
  }

  public boolean checkExisted(String title) {
    return examRepository.findByTitle(title) != null;
  }

}
