package io.github.lolens.service.sortstrategy.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.SortStrategy;

public class SelectionSortStrategy implements SortStrategy {

  @Override
  public <T extends Number & Comparable<T>> void sort(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    for (int i = 0; i < array.length; i++) {
      T max = array[0];
      int maxPos = 0;
      for (int j = 0; j < array.length - i; j++) {

        if (max.compareTo(array[j]) < 0) { // max < array[j]
          max = array[j];
          maxPos = j;
        }

      }

      array[maxPos] = array[array.length - i - 1];
      array[array.length - i - 1] = max;
    }
  }



}
