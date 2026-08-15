package io.github.lolens.exception;

public class MathOperationServiceException extends RuntimeException {
  public MathOperationServiceException(String message) {
    super(message);
  }

  public MathOperationServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public MathOperationServiceException(Throwable cause) {
    super(cause);
  }

  public MathOperationServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public MathOperationServiceException() {
  }
}
