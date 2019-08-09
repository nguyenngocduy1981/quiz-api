package net.quiz.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<Enum, String> {

  private String[] dataList;

  @Override
  public boolean isValid(String val, ConstraintValidatorContext ct) {
    if (val == null) {
      return false;
    }
    return Arrays.asList(dataList).contains(val);
  }

  @Override
  public void initialize(Enum constraint) {
    this.dataList = constraint.list();
  }
}
