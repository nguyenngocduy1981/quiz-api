package net.quiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.quiz.converter.QuestionConverter;
import net.quiz.converter.SectionConverter;
import net.quiz.exception.BadRequestException;
import net.quiz.exception.SystemException;
import net.quiz.models.*;
import net.quiz.payload.dto.*;
import net.quiz.repository.PassageRepository;
import net.quiz.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static net.quiz.constants.Errors.*;
import static net.quiz.constants.QuestionConstants.*;
import static org.springframework.util.StringUtils.isEmpty;

@Service
@Transactional(readOnly = true)
public class QuestionService extends BaseService {

  private Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ExamService examService;

  @Autowired
  private PassageRepository passageRepository;

  @Autowired
  private SectionService sectionService;

  @Autowired
  private QuestionRepository questionRepository;

  public int countBySectionId(int secId) {
    return questionRepository.countBySectionId(secId);
  }

  /**
   * Khi luu, column check_sum = md5(question_text + sectionId) --> de dam bao trong 1 section khong co 2 question trung nhau
   */
  public boolean checkByTextAndSectionId(String questionText, int sectionId) {
    String checkSum = this.md5(this.combine4CheckSum(questionText, sectionId));
    if (questionRepository.findByCheckSum(checkSum) != null) {
      String msg = String.format(Q_EXISTED, questionText);
      throw new SystemException(msg);
    }
    return true;
  }

  private List<Integer> toList(Map<Integer, List<Integer>> payload) {
    List<Integer> list = new ArrayList<>();
    Set<Integer> keys = payload.keySet();
    for (Integer key : keys) {
      list.addAll(payload.get(key));
    }

    return list;
  }

  @Transactional
  public boolean saveExam(List<GetQuestionBySectionResponse> questions, String title) {
    if (examService.checkExisted(title)) {
      String msg = String.format("bài thi %s đã được tạo rồi", title);
      throw new SystemException(msg);
    }

    try {
      ExamDTO dto = new ExamDTO(title, objectMapper.writeValueAsString(questions));
      examService.save(dto);

      return true;
    } catch (JsonProcessingException e) {
      LOGGER.error("loi khi goi writeValueAsString");
      throw new SystemException("Loi khi save exam");
    }
  }

  @Transactional
  public List<GetQuestionBySectionResponse> generateExam(ExamGenerate exam) {

    if (examService.checkExisted(exam.getTitle())) {
      String msg = String.format("bài thi %s đã được tạo rồi", exam.getTitle());
      throw new SystemException(msg);
    }

    List<GetQuestionBySectionResponse> questions = this.findBySectionQuesIdList(exam.getPayload());

    try {
      ExamDTO dto = new ExamDTO(exam.getTitle(), objectMapper.writeValueAsString(questions));
      examService.save(dto);

      return questions;
    } catch (JsonProcessingException e) {
      LOGGER.error("loi khi goi writeValueAsString");
      throw new SystemException("Loi khi save exam");
    }
  }

  public List<GetQuestionBySectionResponse> findBySectionQuesIdList(Map<Integer, List<Integer>> payload) {
    Set<Integer> ids = payload.keySet();
    List<Section> sections = sectionService.findByIdInAndGetEntity(ids);
    return sections.stream()
            .map(sec -> {
              GetQuestionBySectionResponse item = new GetQuestionBySectionResponse(SectionConverter.convert2DTO(sec));
              List<Question> questions = questionRepository.findByIdIn(payload.get(sec.getId()));

              this.convertToDtoList(item, questions, sec);
              return item;
            }).collect(Collectors.toList());
  }
//
//  public Map<Integer, List<QuestionDTO>> findByIdsList(List<Integer> ids) {
//    return toSectionQuesMap(questionRepository.findByIdIn(ids));
//  }
//
//  public Map<Integer, List<QuestionDTO>> findSectionQuestionMap() {
//    return toSectionQuesMap(questionRepository.findAll());
//  }

  public PagerResponse findBySectionId(int sectionId, int page) {
    Section sec = sectionService.findByIdAndGetEntity(sectionId);
    GetQuestionBySectionResponse res = new GetQuestionBySectionResponse(SectionConverter.convert2DTO(sec));

    page = page <= 0 ? 0 : page - 1;
    PageRequest p = PageRequest.of(page, noOfRowInPage);

    Page<Question> pager = questionRepository.findBySectionId(sectionId, p);

    int pageCount = pager.getTotalPages();
    List<Question> list = pager.getContent();

    this.convertToDtoList(res, list, sec);

//    QuestionType type = sec.getQuestionType();
//    List<QuestionDTO> questions = list.stream()
//            .map(QuestionConverter::convert2DTO)
//            .collect(Collectors.toList());
//
//    if (PASSAGE_TYPES.contains(type)) {
//      Passage pa = sec.getPassage();
//      PassageDTO paDto = new PassageDTO(pa.getId(), pa.getText(), type);
//      List<Option> options = pa.getOptions();
//      if (!CollectionUtils.isEmpty(options)) {
//        paDto.setOptions(options.stream().map(Option::getText).collect(Collectors.toList()));
//      }
//      res.setPassage(paDto);
//    }
//
//    res.setQuestions(questions);
    return new PagerResponse(res, pageCount);
  }

  private void convertToDtoList(GetQuestionBySectionResponse res,
                                List<Question> list,
                                Section sec) {
    QuestionType type = sec.getQuestionType();
    List<QuestionDTO> questions = list.stream()
            .map(QuestionConverter::convert2DTO)
            .collect(Collectors.toList());

    if (PASSAGE_TYPES.contains(type)) {
      Passage pa = sec.getPassage();
      PassageDTO paDto = new PassageDTO(pa.getId(), pa.getText(), type);
      List<Option> options = pa.getOptions();
      if (!CollectionUtils.isEmpty(options)) {
        paDto.setOptions(options.stream().map(Option::getText).collect(Collectors.toList()));
      }
      res.setPassage(paDto);
    }

    res.setQuestions(questions);
  }

  @Transactional
  public boolean remove(int questionid) {
    try {
      questionRepository.deleteById(questionid);
      return true;
    } catch (Exception ex) {
      LOGGER.error("Remove question: ", ex);
      return false;
    }
  }

  @Transactional
  public boolean save(NewQuestionRequest payload) {
    Section section = sectionService.findByIdAndGetEntity(Integer.parseInt(payload.getSectionId()));
    if (section == null) {
      throw new BadRequestException(SEC_NO_EXISTED);
    }

    QuestionType type = section.getQuestionType();

    long c = payload.getQuestions().stream()
            .filter(q -> isEmpty(q.getAnswer()))
            .count();
    if (OPTION_TYPES.contains(type) && c > 0) {
      throw new BadRequestException(Q_R);
    }

    payload.getQuestions().forEach(q -> this.saveOne(payload, section, q));

    return true;
  }

  private boolean saveOne(NewQuestionRequest payload, Section section, QuestionDTO dto) {
    int secId = Integer.parseInt(payload.getSectionId());
    // trong 1 section, khong duoc co question trung nhau --> checksum phai ket hop 2 value: question_text + section_id
    String checkSum = this.checkExisted(secId, dto);
    QuestionType type = section.getQuestionType();

    Question ques = QuestionConverter.convert2Entity(dto);
    ques.setSection(section);
    ques.setCheckSum(checkSum);
    ques.setType(type);

    Question savedQues = questionRepository.save(ques);

    if (PASSAGE_TYPES.contains(type)) {
//      Passage pa = new Passage();
//      pa.setId(payload.getPassageId());
//      pa.setSection(section);
      savedQues.setPassage(section.getPassage());
    }
    if (OPTION_TYPES.contains(type)) {
      savedQues.setPossibleAnswers(this.extractPossibleAnswer(dto));
    }

    if (WITH_ANSWER_TYPES.contains(type)) {
      savedQues.setAnswer(new Answer(dto.getAnswer(), savedQues));
    }

    return true;
  }

  private List<PossibleAnswer> extractPossibleAnswer(QuestionDTO dto) {
    return dto.getPossibleAnswers()
            .stream()
            .map(QuestionConverter::toPossibleAnswerEntity)
            .collect(Collectors.toList());
  }

  private String combine4CheckSum(String questionText, Integer sectionId) {
    return questionText + sectionId;
  }

  private String checkExisted(int secId, QuestionDTO dto) {
    String checkSum = this.md5(this.combine4CheckSum(dto.getText(), secId));
    Question ques = questionRepository.findByCheckSum(checkSum);
    if (ques != null) {
      String msg = String.format(Q_EXISTED, dto.getText());
      throw new BadRequestException(msg);
    }

    return checkSum;
  }
//
//  public Map<Integer, List<QuestionDTO>> toSectionQuesMap(List<Question> list) {
//    return list.stream()
//            .map(QuestionConverter::convert2DTO)
//            .collect(Collectors.groupingBy(QuestionDTO::getSectionId));
//  }
}
