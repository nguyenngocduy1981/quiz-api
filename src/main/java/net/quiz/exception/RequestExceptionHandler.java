package net.quiz.exception;

import net.quiz.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.quiz.constants.Errors.BAD_RQ;

@ControllerAdvice
public class RequestExceptionHandler {

  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity handleHttpMessageNotReadableException(
          HttpMessageNotReadableException ex) {
    String msg = ex.getMostSpecificCause().getMessage();

    ErrorResponse res = new ErrorResponse(new Error(BAD_RQ, msg));
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity handleMethodArgumentNotValidException(
          MethodArgumentNotValidException ex) {
    Set<Error> errorMap = new HashSet<>();

    BindingResult bindingResult = ex.getBindingResult();
    List<ObjectError> errors = bindingResult.getAllErrors();
    int size = errors.size();

    for (int i = 0; i < size; i++) {
      errorMap.add(this.extractError(errors.get(i)));
    }

    if (errorMap.size() == 1) {
      return new ResponseEntity<>(new ErrorResponse(errorMap.iterator().next()), HttpStatus.OK);
    }

    Error error = new Error(BAD_RQ, "Request sai");
    error.setErrors(errorMap);
    ErrorResponse res = new ErrorResponse(error);

    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  private Error extractError(ObjectError error) {
    FieldError fieldError = (FieldError) error;
    return new Error(new Integer(fieldError.getDefaultMessage()),
            fieldError.getField() + " khong nhap hoac gia tri khong dung");
  }

//  @ExceptionHandler({AuthenException.class})
//  public ResponseEntity<ErrorResponse> handleAuthenException(
//          AuthenException ex) {
//    ErrorResponse res = new ErrorResponse(ex.getError());
//    return new ResponseEntity<>(res, HttpStatus.OK);
//  }

  @ExceptionHandler({BadRequestException.class})
  public ResponseEntity<ErrorResponse> handleBadRequestException(
          BadRequestException ex) {
    ErrorResponse res = new ErrorResponse(ex.getError());
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @ExceptionHandler({SystemException.class})
  public ResponseEntity<ErrorResponse> handleSystemException(
          SystemException ex) {
    Error error = ex.getError();

    String msg = ex.getError().getMessage();
    String[] arr;
    if (msg != null && (arr = msg.split("#")).length > 1) {
      error.setMessage(arr[0]);
      error.setCode(Integer.parseInt(arr[1].trim()));
    }

    ErrorResponse res = new ErrorResponse(error);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
