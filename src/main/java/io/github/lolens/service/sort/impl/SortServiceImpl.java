package io.github.lolens.service.sort.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sort.SortService;
import io.github.lolens.service.sort.strategy.SortStrategy;

public class SortServiceImpl implements SortService {

  public <T extends Number & Comparable<T>> NumericArrayWrapper<T> sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy strategy) {
    return strategy.sort(arrayWrapper);
  }

}
