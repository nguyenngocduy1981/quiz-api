package net.quiz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import net.quiz.exception.Error;
import net.quiz.payload.BaseResponse;
import net.quiz.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

//  public ResponseEntity<JsonNode> ok(JsonNode payload) {
//    return ResponseEntity.ok(payload);
//  }

  public ResponseEntity<Object> ok(Object payload) {
    BaseResponse res = new BaseResponse();
    res.setData(payload);

    return ResponseEntity.ok(res);
  }

  public ResponseEntity<Object> fail(HttpStatus status, Object payload) {
    Error error = new Error(status.value(), "Loi roi");
    ErrorResponse res = new ErrorResponse(error);
    return new ResponseEntity<>(res, status);
  }
}
