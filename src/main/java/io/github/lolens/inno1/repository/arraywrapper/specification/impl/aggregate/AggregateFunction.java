package io.github.lolens.inno1.repository.arraywrapper.specification.impl.aggregate;

import java.util.Arrays;

public enum AggregateFunction {


  MAX {
    @Override
    public double result(Number[] array) {
      return Arrays.stream(array).mapToDouble(Number::doubleValue).max()
          .orElseThrow(() -> new IllegalStateException("Empty array has no max"));
    }
  },
  MIN {
    @Override
    public double result(Number[] array) {
      return Arrays.stream(array).mapToDouble(Number::doubleValue).min()
          .orElseThrow(() -> new IllegalStateException("Empty array has no min"));
    }
  },
  SUM {
    @Override
    public double result(Number[] array) {
      return Arrays.stream(array).mapToDouble(Number::doubleValue).sum();
    }
  },
  AVERAGE {
    @Override
    public double result(Number[] array) {
      return Arrays.stream(array).mapToDouble(Number::doubleValue).average()
          .orElseThrow(() -> new IllegalStateException("Empty array has no average"));
    }
  },
  COUNT {
    @Override
    public double result(Number[] array) {
      return array.length;
    }
  };

  public abstract double result(Number[] array);


}
