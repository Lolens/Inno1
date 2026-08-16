package io.github.lolens.service.sortstrategy;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.impl.BubbleSortStrategy;
import io.github.lolens.service.sortstrategy.impl.InsertionSortStrategy;
import io.github.lolens.service.sortstrategy.impl.SelectionSortStrategy;

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

  <T extends Number & Comparable<T>> void sort(NumericArrayWrapper<T> arrayWrapper);
}
