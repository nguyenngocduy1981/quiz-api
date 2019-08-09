package net.quiz.exception;

import static net.quiz.constants.Errors.SYS_ERROR;

public class SystemException extends RuntimeException {

  private final Error error;

  public SystemException(Error error) {
    super(error.getMessage());
    this.error = error;
  }

  public SystemException(String message) {
    super(message);
    this.error = new Error("", SYS_ERROR, message);
  }

  public SystemException(String message, String requestId) {
    super(message);
    this.error = new Error(requestId, SYS_ERROR, message);
  }

  public SystemException(String message, String requestId, int code) {
    super(message);
    this.error = new Error(requestId, code, message);
  }

  public Error getError() {
    return error;
  }
}
