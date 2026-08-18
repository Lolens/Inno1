package io.github.lolens.inno1.service.math.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.service.math.MathOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;

public class MathOperationServiceImpl implements MathOperationService {

  private static final Logger logger = LoggerFactory.getLogger(MathOperationServiceImpl.class);

  @Override
  public <T extends Number & Comparable<T>> Optional<T> min(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();
    return Arrays.stream(array).min(Comparator.naturalOrder());
  }

  @Override
  public <T extends Number & Comparable<T>> Optional<T> max(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();
    return Arrays.stream(array).max(Comparator.naturalOrder());
  }

  @Override
  public <T extends Number & Comparable<T>> Optional<Double> sum(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    return Optional.of(
        Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .sum()
    );
  }

  @Override
  public <T extends Number & Comparable<T>> Optional<Double> average(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    OptionalDouble avg = Arrays.stream(array)
        .mapToDouble(Number::doubleValue)
        .average();

    return avg.isPresent() ? Optional.of(avg.getAsDouble()) : Optional.empty();
  }

}
