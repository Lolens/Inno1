package io.github.lolens.exception;

public class NumericArrayValidatorException extends RuntimeException {
  public NumericArrayValidatorException(String message) {
    super(message);
  }

  public NumericArrayValidatorException(String message, Throwable cause) {
    super(message, cause);
  }

  public NumericArrayValidatorException(Throwable cause) {
    super(cause);
  }

  public NumericArrayValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public NumericArrayValidatorException() {
  }
}
