package io.github.lolens.inno1.exception;


// Streams are used in ArrayReader and Exception cannot be used in lambdas.
public class NumericArrayReaderException extends RuntimeException {
  public NumericArrayReaderException(String message) {
    super(message);
  }

  public NumericArrayReaderException(String message, Throwable cause) {
    super(message, cause);
  }

  public NumericArrayReaderException(Throwable cause) {
    super(cause);
  }

  public NumericArrayReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public NumericArrayReaderException() {
  }
}
