package net.quiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.quiz.converter.PassageConverter;
import net.quiz.converter.SectionConverter;
import net.quiz.exception.BadRequestException;
import net.quiz.models.*;
import net.quiz.payload.dto.NewSectionRequest;
import net.quiz.payload.dto.PassageDTO;
import net.quiz.payload.dto.SectionDTO;
import net.quiz.repository.CategoryRepository;
import net.quiz.repository.PassageRepository;
import net.quiz.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import static net.quiz.constants.QuestionConstants.PASSAGE_TYPES;
import static net.quiz.models.QuestionType.OPTION_FROM_GIVEN;
import static net.quiz.models.QuestionType.PASSAGE_OPTION_FROM_GIVEN;
import static org.springframework.util.StringUtils.isEmpty;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.quiz.constants.Errors.*;

@Service
@Transactional
public class SectionService extends BaseService {

  @Autowired
  private QuestionService questionService;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private PassageRepository passageRepository;

  @Autowired
  private SectionRepository sectionRepository;

  public boolean remove(Integer id) {
    if (questionService.countBySectionId(id) > 0) {
      throw new BadRequestException(SEC_CANNT_DEL);
    }
    sectionRepository.deleteById(id);
    return true;
  }

  @Transactional(readOnly = true)
  public SectionDTO findById(Integer id) {
    Section sec = this.findByIdAndGetEntity(id);
    SectionDTO dto = SectionConverter.convert2DTO(sec);
    dto.setPassage(PassageConverter.convert2DTO(sec.getPassage()));
    return dto;
  }

  public List<SectionDTO> findByIdIn(Set<Integer> ids) {
    List<Section> list = sectionRepository.findByIdIn(ids);
    if (CollectionUtils.isEmpty(list)) {
      new BadRequestException(String.format(SEC_NOT_FOUND, ids));
    }

    return this.convert2Dto(list);
  }

  public List<Section> findByIdInAndGetEntity(Set<Integer> ids) {
    List<Section> list = sectionRepository.findByIdIn(ids);
    if (CollectionUtils.isEmpty(list)) {
      new BadRequestException(String.format(SEC_NOT_FOUND, ids));
    }

    return list;
  }

  @Transactional(readOnly = true)
  public Section findByIdAndGetEntity(Integer id) {
    Section section = sectionRepository.findById(id)
            .orElseThrow(() -> new BadRequestException(String.format(SEC_NOT_FOUND, id)));

    return section;
  }

  @Transactional(readOnly = true)
  public SectionDTO findBySectionName(String sectionName) {
    Section section = sectionRepository.findByCheckSum(this.md5(sectionName.trim()));
    if (section == null) {
      return null;
    }

    return SectionConverter.convert2DTO(section);
  }

  @Transactional(readOnly = true)
  public Integer findBySectionNameToGetSectionId(String sectionName) {
    Section section = sectionRepository.findByCheckSum(this.md5(sectionName.trim()));
    if (section == null) {
      return null;
    }

    return section.getId();
  }

  @Transactional(readOnly = true)
  public List<SectionDTO> findByCategoryId(int catId) {
    return this.convert2Dto(sectionRepository.findByCategoryId(catId));
  }

  @Transactional(readOnly = true)
  public List<SectionDTO> findAll() {
    return this.convert2Dto(sectionRepository.findAll());
  }

  private SectionDTO countQuestion(SectionDTO sec) {
    sec.setQuestionCount(questionService.countBySectionId(sec.getId()));
    return sec;
  }

  public boolean checkExisted(SectionDTO dto) {
    // Vi khong query duoc "tieng viet" nen where check_sum nay
    return sectionRepository.findByCheckSumAndQuestionType(md5(dto.getText()), dto.getQuestionType()) != null;
  }

  public boolean save(List<NewSectionRequest> payload) {
    payload.stream().forEach(this::save);
    return true;
  }

  public boolean save(NewSectionRequest payload) {
    this.checkBeforeSave(payload);

    Section section = this.saveSection(payload);
    if (PASSAGE_TYPES.contains(payload.getQuestionType())) {
      Passage passage = this.savePassage(payload, section);
      section.setPassage(passage);
    }

    return true;
  }

  private Section saveSection(NewSectionRequest payload) {
    String text = payload.getText().trim();
    String checkSum = md5(text);
    QuestionType type = payload.getQuestionType();

    Section existedSec = sectionRepository.findByCheckSum(checkSum);
    if (existedSec != null && existedSec.getQuestionType() == type) {
      throw new BadRequestException(String.format(SEC_TEXT_EXISTED, text));
    }

    int catId = payload.getCategoryId();
    Category cat = categoryRepository.findById(catId)
            .orElseThrow(() -> new BadRequestException(String.format(CAT_NOT_EXISTED, payload.getCategoryId())));

    Section sec = new Section();
    sec.setSectionName(text);
    sec.setQuestionType(type);
    sec.setCheckSum(checkSum);
    sec.setCategory(cat);
    if (OPTION_FROM_GIVEN == type) {
      List<Option> secOptions = payload.getSectionOptions()
              .stream()
              .map(this::toSectionOption)
              .collect(Collectors.toList());
      sec.setOptions(secOptions);
    }

    return sectionRepository.save(sec);
  }

  private Passage savePassage(NewSectionRequest payload, Section section) {
    QuestionType type = payload.getQuestionType();

    Passage passage = new Passage(payload.getPassageText());
    passage.setQuestionType(type);

    if (PASSAGE_OPTION_FROM_GIVEN == type) {
      List<Option> paOptions = payload.getPassageOptions()
              .stream()
              .map(this::toSectionOption)
              .collect(Collectors.toList());
      passage.setOptions(paOptions);
    }

    passage.setSection(section);
    return passageRepository.save(passage);
  }

  private void checkBeforeSave(NewSectionRequest sec) {
    if (isEmpty(sec.getText())) {
      throw new BadRequestException(SEC_TEXT_R);
    }

    if (isEmpty(sec.getQuestionType())) {
      throw new BadRequestException(QUES_TYPE_R);
    }

    if (isEmpty(sec.getCategoryId())) {
      throw new BadRequestException(CAT_R);
    }

    boolean isOption = sec.getQuestionType() == OPTION_FROM_GIVEN;
    if (isOption && CollectionUtils.isEmpty(sec.getSectionOptions())) {
      throw new BadRequestException(OPTION_FROM_GIVEN_R);
    }

    boolean isPassage = PASSAGE_TYPES.contains(sec.getQuestionType());
    if (isPassage && CollectionUtils.isEmpty(sec.getPassageOptions())) {
      throw new BadRequestException(PASSAGE_R);
    }
  }

  private List<SectionDTO> convert2Dto(List<Section> list) {
    return list.stream()
            .map(SectionConverter::convert2DTO)
            .map(this::countQuestion)
            .collect(Collectors.toList());
  }

  private Option toSectionOption(String text) {
    return new Option(text);
  }
}
