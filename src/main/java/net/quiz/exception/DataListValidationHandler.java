package net.quiz.exception;

import net.quiz.payload.ErrorResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static net.quiz.constants.Errors.BAD_RQ;

@Aspect
@Component
@Order(value = 2)
public class DataListValidationHandler {

  @Around("execution(* *(..)) && @annotation(valids)")
  public Object logsActivityAnnotation(ProceedingJoinPoint point,
                                       Valids valids) throws Throwable {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    Set<Error> errors = new HashSet<>();
    Object[] args = point.getArgs();
    if (args != null && args.length > 0 && args[0] instanceof ArrayList) {
      ArrayList list = (ArrayList) args[0];
      for (int i = 0; i < list.size(); i++) {
        Object o = list.get(i);
        Set<ConstraintViolation<Object>> validate = validator.validate(o);
        if (validate.isEmpty()) {
          continue;
        }

        Set<Error> fieldErrors = validate.stream().map(v -> {
          String field = v.getPropertyPath().toString();
          String msg = v.getMessageTemplate();
          return new Error(new Integer(msg), field + " khong nhap hoac gia tri khong dung");
        }).collect(Collectors.toSet());

        errors.addAll(fieldErrors);
      }
    }

    if (errors.isEmpty()) {
      return point.proceed();
    }

    Error error = new Error(BAD_RQ, "Request khong hop le");
    error.setErrors(errors);
    ErrorResponse res = new ErrorResponse(error);

    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }
}
