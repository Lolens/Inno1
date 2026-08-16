package io.github.lolens.service.sortstrategy.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.SortService;
import io.github.lolens.service.sortstrategy.SortStrategy;

public class SortServiceImpl implements SortService {

  public SortServiceImpl() {

  }

  public <T extends Number & Comparable<T>> void sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy<T> strategy) {
    strategy.sort(arrayWrapper);
  }

}
