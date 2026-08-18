package io.github.lolens.service.sort;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sort.strategy.SortStrategy;

public interface SortService {

  <T extends Number & Comparable<T>> NumericArrayWrapper<T> sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy strategy);
}
