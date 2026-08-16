package io.github.lolens.service.sortstrategy;

import io.github.lolens.entity.NumericArrayWrapper;

public interface SortService {

  <T extends Number & Comparable<T>> void sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy<T> strategy);
}
