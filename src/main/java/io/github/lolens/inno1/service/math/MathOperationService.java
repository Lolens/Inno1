package io.github.lolens.inno1.service.math;

import io.github.lolens.inno1.entity.NumericArrayWrapper;

import java.util.Optional;

public interface MathOperationService {

  <T extends Number & Comparable<T>> Optional<T> min(NumericArrayWrapper<T> arrayWrapper);
  <T extends Number & Comparable<T>> Optional<T> max(NumericArrayWrapper<T> arrayWrapper);

  <T extends Number & Comparable<T>> Optional<Double> sum(NumericArrayWrapper<T> arrayWrapper);

  <T extends Number & Comparable<T>> Optional<Double> average(NumericArrayWrapper<T> arrayWrapper);

}
