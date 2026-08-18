package io.github.lolens.inno1.service.sort.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.service.sort.SortService;
import io.github.lolens.inno1.service.sort.strategy.SortStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortServiceImplTest {

  final SortService service = new SortServiceImpl();

  private NumericArrayWrapper<Integer> arrayWrapper;

  private enum Strategy {
    BUBBLE(SortStrategy.bubble()),
    INSERTION(SortStrategy.insertion()),
    SELECTION(SortStrategy.selection());

    private final SortStrategy strategy;

    Strategy(SortStrategy strategy) {
      this.strategy = strategy;
    }

    public SortStrategy getStrategy() {
      return strategy;
    }
  }

  private <T extends Number & Comparable<T>> void sortWithEach(NumericArrayWrapper<T> arrayWrapper, T[] toCompare) {
    for (Strategy strategy : Strategy.values()) {
      NumericArrayWrapper<T> sorted = service.sort(arrayWrapper, strategy.getStrategy());
      assertArrayEquals(toCompare, sorted.getArray());
    }
  }

  // Test case scenarios are done using AI.

  // Factory method is used because constructor is private and
  // I don't want to use reflection to instantiate NumericArrayWrapper for tests
  // If constructor is open its usage becomes a recommendation and not a must

  @Test
  void emptyArray() {
    Integer[] wrapperInnerArray = new Integer[]{};
    Integer[] resultToCompare = new Integer[]{};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }

  @Test
  void singleElement() {
    Integer[] wrapperInnerArray = new Integer[]{5};
    Integer[] resultToCompare = new Integer[]{5};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }

  @Test
  void alreadySorted() {
    Integer[] wrapperInnerArray = new Integer[]{1, 2, 3};
    Integer[] resultToCompare = new Integer[]{1, 2, 3};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }

  @Test
  void reverseSorted() {
    Integer[] wrapperInnerArray = new Integer[]{3, 2, 1};
    Integer[] resultToCompare = new Integer[]{1, 2, 3};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }

  @Test
  void duplicates() {
    Integer[] wrapperInnerArray = new Integer[]{2, 1, 3, 1, 2};
    Integer[] resultToCompare = new Integer[]{1, 1, 2, 2, 3};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }

  @Test
  void allSame() {
    Integer[] wrapperInnerArray = new Integer[]{7, 7, 7};
    Integer[] resultToCompare = new Integer[]{7, 7, 7};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }

  @Test
  void negativeNumbers() {
    Integer[] wrapperInnerArray = new Integer[]{2, -1, -3, 0};
    Integer[] resultToCompare = new Integer[]{-3, -1, 0, 2};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperInnerArray);
    sortWithEach(arrayWrapper, resultToCompare);
  }
}
