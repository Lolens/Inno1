package io.github.lolens.inno1.service.sort;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.service.sort.strategy.SortStrategy;

public interface SortService {

  <T extends Number & Comparable<T>> NumericArrayWrapper<T> sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy strategy);
}
