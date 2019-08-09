package net.quiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.quiz.exception.BadRequestException;
import net.quiz.models.QuestionType;
import net.quiz.payload.dto.ExamDTO;
import net.quiz.payload.dto.QuestionDTO;
import net.quiz.payload.report.Report;
import net.quiz.payload.report.ReportExport;
import net.quiz.payload.report.ReportItem;
import net.quiz.utils.PdfReportUtil;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporterService {
  private Logger LOGGER = LoggerFactory.getLogger(ReporterService.class);

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private ExamService examService;

  @Autowired
  private ResourceLoader resourceLoader;

  public ReportExport export(int id) {
//    ExamDTO exam = new ExamDTO("heehehehehehehehehe");
    ExamDTO exam = examService.findById(id);
    try {


      byte[] xmlBytes = this.createXml(exam);
      byte[] pdfBytes = this.createPdf(xmlBytes);
//      return xml.toByteArray();

      return new ReportExport(exam.getTitle(), pdfBytes);
    } catch (Exception e) {
      String msg = "Loi khi tao report: " + e.getMessage();
      LOGGER.error(msg);
      throw new BadRequestException(msg);
    }
  }

  private byte[] createPdf(byte[] bytes) {
    Resource xsltResource = new ClassPathResource("exam-report.xslt");
    try {
      ByteArrayOutputStream pdfWriter = new ByteArrayOutputStream();

      Resource resource = new ClassPathResource("fop.xconf");

      FopFactory fopFac = FopFactory.newInstance(resource.getURI());
      Fop fop = fopFac.newFop("application/pdf", pdfWriter);
      Transformer tran = TransformerFactory.newInstance().newTransformer(new StreamSource(xsltResource.getInputStream()));
      tran.transform(new StreamSource(new ByteArrayInputStream(bytes)), new SAXResult(fop.getDefaultHandler()));

      return pdfWriter.toByteArray();
    } catch (Exception ex) {
      String msg = "Loi khi tao pdf: " + ex.getMessage();
      LOGGER.error(msg);
      throw new BadRequestException(msg);
    }
  }

  private byte[] createXml(ExamDTO exam) {
    try {
      Report report = toReport(exam);

      JAXBContext ct = JAXBContext.newInstance(Report.class);
      Marshaller ma = ct.createMarshaller();
      ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      ByteArrayOutputStream xml = new ByteArrayOutputStream();
      ma.marshal(report, new StreamResult(xml));
//      ma.marshal(report, new StreamResult(System.out));
      return xml.toByteArray();
    } catch (Exception ex) {
      String msg = "Loi khi tao XML: " + ex.getMessage();
      LOGGER.error(msg);
      throw new BadRequestException(msg);
    }
  }

  private byte[] createXml() {
    Resource resource = resourceLoader.getResource("classpath:report.xml");
    try {
      return Files.readAllBytes(Paths.get(resource.getURI()));
    } catch (IOException ex) {
      String msg = "Loi khi tao XML: " + ex.getMessage();
      LOGGER.error(msg);
      throw new BadRequestException(msg);
    }
  }

  private Report toReport(ExamDTO exam) {
    try {
      Map<String, List<LinkedHashMap>> map = mapper.readValue(exam.getQuestions(), Map.class);
      List<ReportItem> list = map.keySet()
              .stream()
              .map(k -> this.toItem(map.get(k)))
              .collect(Collectors.toList());

      return new Report(list);
    } catch (IOException e) {
      String msg = "Loi khi convert Report: " + e.getMessage();
      LOGGER.error(msg);
      throw new BadRequestException(msg);
    }
  }

  private ReportItem toItem(List<LinkedHashMap> questions) {
    try {
//      QuestionDTO f = mapper.convertValue(questions.get(0), QuestionDTO.class);
//      ReportItem report = new ReportItem(f.getSectionId(), f.getSectionName());
//      report.setType(f.getType().getType());
//      this.setOptions(report, f);

//      report.setQuestion(questions.stream().map(this::convert2Dto).collect(Collectors.toList()));
//
//      return report;
      return null;
    } catch (Exception e) {
      String msg = "Loi khi convert ReportItem: " + e.getMessage();
      LOGGER.error(msg);
      throw new BadRequestException(msg);
    }
  }

  private void setOptions(ReportItem report, QuestionDTO f) {
//    List<String> options = f.getOptions();
//    if (options == null || f.getType() != QuestionType.OPTION_FROM_GIVEN) return;
//
//
//    int len = options.size();
//    if (len < 3) {
//      report.setOption1(options);
//      return;
//    }
//
//    int to = (int) Math.ceil(len / 2F);
//    report.setOption1(options.subList(0, to));
//    report.setOption2(options.subList(to, len));
  }

  private QuestionDTO convert2Dto(LinkedHashMap map) {
    QuestionDTO dto = mapper.convertValue(map, QuestionDTO.class);
    String s = dto.getText()
            .replace("<br/>", " ")
            .replace("<br />", " ");
    dto.setText(s);

    return dto;
  }
}
