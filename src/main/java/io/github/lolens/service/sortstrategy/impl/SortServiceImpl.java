package io.github.lolens.service.sortstrategy.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.SortService;
import io.github.lolens.service.sortstrategy.SortStrategy;

public class SortServiceImpl<T extends Number & Comparable<T>> implements SortService<T> {

  private final SortStrategy<T> sortStrategy;

  public SortServiceImpl(SortStrategy<T> sortStrategy) {
    this.sortStrategy = sortStrategy;
  }

  public void sort(NumericArrayWrapper<T> arrayWrapper, SortStrategy<T> strategy) {
    strategy.sort(arrayWrapper);
  }

}
