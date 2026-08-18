package io.github.lolens.inno1.util;

public final class IdentifierCounter {
  private long id;

  public IdentifierCounter(long initialValue) {
    this.id = initialValue;
  }

  public long currentValue() {
    return id;
  }

  public long getAndIncrement() {
    return this.id++;
  }

  public static IdentifierCounter startFrom(long initialValue) {
    return new IdentifierCounter(initialValue);
  }

  public static IdentifierCounter zero() {
    return new IdentifierCounter(0);
  }

}
