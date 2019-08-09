package net.quiz.exception;

import static net.quiz.constants.Errors.BAD_RQ;

public class BadRequestException extends RuntimeException {

  private final Error error;

  public BadRequestException(Error error) {
    super(error.getMessage());
    this.error = error;
  }

  public BadRequestException(int code, String message) {
    super(message);
    this.error = new Error("", code, message);
  }
  public BadRequestException(String requestId, int code, String message) {
    super(message);
    this.error = new Error(requestId, code, message);
  }

  public BadRequestException(String msg) {
    super(msg);
    this.error = new Error(BAD_RQ, msg);
  }

  public Error getError() {
    return error;
  }
}
