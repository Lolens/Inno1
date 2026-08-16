package io.github.lolens.exception;

public class ArrayDataValidatorException extends RuntimeException {
  public ArrayDataValidatorException(String message) {
    super(message);
  }

  public ArrayDataValidatorException(String message, Throwable cause) {
    super(message, cause);
  }

  public ArrayDataValidatorException(Throwable cause) {
    super(cause);
  }

  public ArrayDataValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ArrayDataValidatorException() {
  }
}
