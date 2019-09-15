CREATE TABLE T_ANSWER
(
  question_id INT          NOT NULL
    PRIMARY KEY,
  answer_text VARCHAR(200) NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE T_CATEGORY
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  cat_name  VARCHAR(100) NOT NULL,
  parent_id VARCHAR(50)  NULL
)
  ENGINE = InnoDB;

CREATE TABLE T_EXAM
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  title     VARCHAR(100)           NULL
  COMMENT 'dd-MM-yyyy_UserName_metadata. De dam bao moi User moi ngay chi lam 1 Exam. Neu muon lam nhieu exam thi them vao metadata',
  questions TEXT                   NOT NULL,
  approve   TINYINT(1) DEFAULT '0' NULL,
  token     TINYINT(1) DEFAULT '0' NULL,
  CONSTRAINT T_EXAM_title_uindex
  UNIQUE (title)
)
  ENGINE = InnoDB;

CREATE TABLE T_OPTIONS_FOR_PASSAGE
(
  passage_id  INT          NOT NULL,
  option_text VARCHAR(200) NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX T_OPTIONS_FOR_PASSAGE_id_fk
  ON T_OPTIONS_FOR_PASSAGE (passage_id);

CREATE TABLE T_OPTIONS_FOR_SECTION
(
  section_id  INT          NOT NULL,
  option_text VARCHAR(200) NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX T_OPTIONS_T_SECTION_id_fk
  ON T_OPTIONS_FOR_SECTION (section_id);

CREATE TABLE T_PASSAGE
(
  section_id       INT AUTO_INCREMENT
    PRIMARY KEY,
  passage_text     TEXT        NULL,
  type_of_question VARCHAR(50) NULL
)
  ENGINE = InnoDB;

ALTER TABLE T_OPTIONS_FOR_PASSAGE
  ADD CONSTRAINT T_OPTIONS_FOR_PASSAGE_id_fk
FOREIGN KEY (passage_id) REFERENCES T_PASSAGE (section_id);

CREATE TABLE T_PASSAGE_QUESTION
(
  passage_id  INT NOT NULL,
  question_id INT NOT NULL,
  PRIMARY KEY (passage_id, question_id),
  CONSTRAINT T_PASSAGE_QUESTION_T_PASSAGE_id_fk
  FOREIGN KEY (passage_id) REFERENCES T_PASSAGE (section_id)
)
  ENGINE = InnoDB;

CREATE INDEX T_PASSAGE_QUESTION_T_QUESTION_id_fk
  ON T_PASSAGE_QUESTION (question_id);

CREATE TABLE T_POSSIBLE_ANSWER
(
  question_id          INT          NOT NULL,
  possible_answer_text VARCHAR(200) NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX question_id
  ON T_POSSIBLE_ANSWER (question_id);

CREATE TABLE T_QUESTION
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  question_text TEXT                                NOT NULL,
  created_on    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  section_id    INT                                 NULL,
  question_type VARCHAR(50)                         NOT NULL,
  `_level`      VARCHAR(50) DEFAULT 'BEGINNER'      NOT NULL
  COMMENT 'BEGINNER/A1
	ELEMENTARY/A2
	INTERMEDIATE/B1
	UPPER INTERMEDIATE/B2
	ADVANCED/C1
	PROFICIENT/C2',
  check_sum     VARCHAR(35)                         NOT NULL
  COMMENT 'MD5 toi da 32 chars',
  CONSTRAINT T_QUESTION_check_sum_uindex
  UNIQUE (check_sum)
)
  ENGINE = InnoDB;

CREATE INDEX T_QUESTION_T_SECTION_id_fk
  ON T_QUESTION (section_id);

ALTER TABLE T_ANSWER
  ADD CONSTRAINT T_ANSWER_T_QUESTION_id_fk
FOREIGN KEY (question_id) REFERENCES T_QUESTION (id);

ALTER TABLE T_PASSAGE_QUESTION
  ADD CONSTRAINT T_PASSAGE_QUESTION_T_QUESTION_id_fk
FOREIGN KEY (question_id) REFERENCES T_QUESTION (id);

ALTER TABLE T_POSSIBLE_ANSWER
  ADD CONSTRAINT T_POSSIBLE_ANSWER_ibfk_1
FOREIGN KEY (question_id) REFERENCES T_QUESTION (id);

CREATE TABLE T_SECTION
(
  id               INT AUTO_INCREMENT
    PRIMARY KEY,
  section_name     VARCHAR(200)                        NOT NULL,
  created_on       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  type_of_question VARCHAR(50)                         NOT NULL,
  `desc`           VARCHAR(200)                        NULL,
  check_sum        VARCHAR(500)                        NOT NULL,
  cat_id           INT                                 NULL,
  CONSTRAINT T_SESSION_id_uindex
  UNIQUE (id),
  CONSTRAINT T_SECTION_section_name_uindex
  UNIQUE (section_name)
)
  ENGINE = InnoDB;

CREATE INDEX T_SECTION_T_CATEGORY_id_fk
  ON T_SECTION (cat_id);

ALTER TABLE T_OPTIONS_FOR_SECTION
  ADD CONSTRAINT T_OPTIONS_T_SECTION_id_fk
FOREIGN KEY (section_id) REFERENCES T_SECTION (id);

ALTER TABLE T_QUESTION
  ADD CONSTRAINT T_QUESTION_T_SECTION_id_fk
FOREIGN KEY (section_id) REFERENCES T_SECTION (id);

CREATE TABLE T_USER
(
  id        INT         NOT NULL
    PRIMARY KEY,
  full_name VARCHAR(50) NOT NULL,
  role      VARCHAR(10) NOT NULL,
  user_id   VARCHAR(50) NOT NULL,
  CONSTRAINT T_USER_user_id_uindex
  UNIQUE (user_id)
)
  ENGINE = InnoDB;

