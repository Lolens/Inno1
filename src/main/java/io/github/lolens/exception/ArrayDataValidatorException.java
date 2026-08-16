package io.github.lolens.exception;

// Exception should extend RuntimeException because it is thrown when
// non-recoverable situation happens (input.txt contains invalid data,
// which should result in immediate program shutdown)
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
