package io.github.lolens.inno1.service.sort.strategy.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.service.sort.strategy.SortStrategy;

public class BubbleSortStrategy implements SortStrategy {
  @Override
  public <T extends Number & Comparable<T>> NumericArrayWrapper<T> sort(NumericArrayWrapper<T> arrayWrapper) {
    T[] array = arrayWrapper.getArray();

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - i - 1; j++) {
        if (array[j].compareTo(array[j + 1]) > 0) {
            T temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
        }
      }
    }
    return NumericArrayWrapper.Factory.create(array);
  }
}
