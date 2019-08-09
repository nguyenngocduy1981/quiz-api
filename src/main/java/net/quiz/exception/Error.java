package net.quiz.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

  private String requestId;

  private int code;

  private String message;

  @JsonProperty("extra_data")
  private Object extraData;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Set<Error> errors;

  public Error(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public Error(String requestId, int code, String message) {
    this.requestId = requestId;
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Set<Error> getErrors() {
    return errors;
  }

  public void setErrors(Set<Error> errors) {
    this.errors = errors;
  }

  public Object getExtraData() {
    return extraData;
  }

  public void setExtraData(Object extraData) {
    this.extraData = extraData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Error)) return false;
    Error error = (Error) o;
    return code == error.code;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
