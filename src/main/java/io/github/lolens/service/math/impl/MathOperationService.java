package io.github.lolens.service.math.impl;

import io.github.lolens.Main;
import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.math.MathOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;

public class MathOperationService<T extends Number & Comparable<T>> implements MathOperation<T> {

  private static final Logger logger = LoggerFactory.getLogger(MathOperationService.class);

  @Override
  public Optional<T> min(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();
    return Arrays.stream(array).min(Comparator.naturalOrder());
  }

  @Override
  public Optional<T> max(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();
    return Arrays.stream(array).max(Comparator.naturalOrder());
  }

  @Override
  public Optional<Double> sum(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    return Optional.of(
        Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .sum()
    );
  }

  @Override
  public Optional<Double> average(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    OptionalDouble avg = Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .average();

    return avg.isPresent() ? Optional.of(avg.getAsDouble()) : Optional.empty();
  }

}
