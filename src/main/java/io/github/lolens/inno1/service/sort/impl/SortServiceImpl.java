package io.github.lolens.inno1.service.sort.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.service.sort.SortService;
import io.github.lolens.inno1.service.sort.strategy.SortStrategy;

public class SortServiceImpl implements SortService {

  public <T extends Number & Comparable<T>> NumericArrayWrapper<T> sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy strategy) {
    return strategy.sort(arrayWrapper);
  }

}
