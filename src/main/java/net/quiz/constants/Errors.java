package net.quiz.constants;

public interface Errors {

  int SYS_ERROR = 50000;
  int NOT_FOUND = 40400;
  int BAD_RQ = 40000;
  int UNAUTHEN = 40100;

  String Q_PASSAGE_R = "Thieu cau tra loi cho cau hoi";
  String Q_R = "Thieu cau tra loi";
  String S_R_4_Q = "Thieu section cho cau hoi";
  String C_R_4_Q = "Thieu category cho cau hoi";
  String Q_EXISTED = "Cau hoi <i class='err'>[%s]</i> da ton tai trong loai nay";
  String SEC_CANNT_DEL = "Không thể xóa loại câu hỏi này vì chứa nhiều câu hỏi. Hãy xóa câu hỏi trước";
  String SEC_NOT_FOUND = "Không tìm thấy loại câu hỏi: %s";
  String SEC_TEXT_R = "Tên loại câu hỏi thiếu";
  String SEC_NO_EXISTED = "Loại câu không tồn tại";
  String SEC_TEXT_EXISTED = "Tên loại câu [%s] đã tồn tại rồi";
  String CAT_NOT_EXISTED = "Category không tồn tại [%s]";
  String QUES_TYPE_R = "Kiểu câu hỏi thiếu";
  String CAT_R = "Danh mục thiếu";
  String OPTION_FROM_GIVEN_R = "Loại câu hỏi có kiểu OPTION_FROM_GIVEN buộc phải có options";
  String PASSAGE_R = "Loại câu hỏi có kiểu PASSAGE buộc phải có options";
  String CAT_EXISTED = "Danh mục đã tồn tại rồi";
  String EXAM_TOKEN_ALREADY = "Bài thi đã được làm rồi";
}
