package io.github.lolens.service.math;

import io.github.lolens.entity.NumericArrayWrapper;

import java.util.Optional;

public interface MathOperation<T extends Number & Comparable<T>> {

  Optional<T> min(NumericArrayWrapper<T> arrayWrapper);
  Optional<T> max(NumericArrayWrapper<T> arrayWrapper);

  Optional<Double> sum(NumericArrayWrapper<T> arrayWrapper);

  Optional<Double> average(NumericArrayWrapper<T> arrayWrapper);

}
