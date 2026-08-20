package io.github.lolens.inno1.repository.arraywrapper.specification.impl.aggregate;

import java.util.function.BiPredicate;

public enum ComparisonOperator {
  GREATER_THAN((a, b) -> a > b),
  LESS_THAN((a, b) -> a < b),
  EQUAL_TO((a, b) -> Double.compare(a, b) == 0);

  private final BiPredicate<Double, Double> biPredicate;

  ComparisonOperator(BiPredicate<Double, Double> biPredicate) {
    this.biPredicate = biPredicate;
  }

  public boolean result(double a, double b) {
    return biPredicate.test(a, b);
  }

}