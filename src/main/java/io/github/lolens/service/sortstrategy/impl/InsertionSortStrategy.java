package io.github.lolens.service.sortstrategy.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.SortStrategy;

public class InsertionSortStrategy<T extends Number & Comparable<T>> implements SortStrategy<T> {
  @Override
  public void sort(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    for (int i = 1; i < array.length; i++) {
      T key = array[i];
      int j = i - 1;

      while (j >= 0 && array[j].compareTo(key) > 0) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = key;
    }
  }
}
