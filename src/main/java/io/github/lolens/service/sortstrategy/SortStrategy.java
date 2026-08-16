package io.github.lolens.service.sortstrategy;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.impl.BubbleSortStrategy;
import io.github.lolens.service.sortstrategy.impl.InsertionSortStrategy;
import io.github.lolens.service.sortstrategy.impl.SelectionSortStrategy;

public interface SortStrategy<T extends Number & Comparable<T>> {


  static <T extends Number & Comparable<T>> SortStrategy<T> bubble() {
    return new BubbleSortStrategy<T>();
  }

  static <T extends Number & Comparable<T>> SortStrategy<T> insertion() {
    return new InsertionSortStrategy<>();
  }

  static <T extends Number & Comparable<T>> SortStrategy<T> selection() {
    return new SelectionSortStrategy<>();
  }

  void sort(NumericArrayWrapper<T> arrayWrapper);
}
