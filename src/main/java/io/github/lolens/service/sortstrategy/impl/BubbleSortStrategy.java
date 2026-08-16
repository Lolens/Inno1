package io.github.lolens.service.sortstrategy.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.service.sortstrategy.SortStrategy;

import java.util.Arrays;

public class BubbleSortStrategy implements SortStrategy {
  @Override
  public <T extends Number & Comparable<T>> void sort(NumericArrayWrapper<T> arrayWrapper) {
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
  }
}
