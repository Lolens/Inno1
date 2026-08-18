package io.github.lolens.service.sort.strategy;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sort.strategy.impl.BubbleSortStrategy;
import io.github.lolens.service.sort.strategy.impl.InsertionSortStrategy;
import io.github.lolens.service.sort.strategy.impl.SelectionSortStrategy;

public interface SortStrategy {


  static <T extends Number & Comparable<T>> SortStrategy bubble() {
    return new BubbleSortStrategy();
  }

  static <T extends Number & Comparable<T>> SortStrategy insertion() {
    return new InsertionSortStrategy();
  }

  static <T extends Number & Comparable<T>> SortStrategy selection() {
    return new SelectionSortStrategy();
  }

  <T extends Number & Comparable<T>> NumericArrayWrapper<T> sort(NumericArrayWrapper<T> arrayWrapper);
}
