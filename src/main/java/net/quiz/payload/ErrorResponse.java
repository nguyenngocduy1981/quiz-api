package net.quiz.payload;

import net.quiz.exception.Error;

public class ErrorResponse {
  private Error error;

  public ErrorResponse(Error error) {
    this.error = error;
  }

  public Error getError() {
    return error;
  }
}
