package io.github.lolens.entity;

import io.github.lolens.exception.NumericArrayWrapperException;

import java.util.Arrays;

public class NumericArrayWrapper<T extends Number & Comparable<T>> {
  private final T[] array;

  private NumericArrayWrapper(T[] array) {
    this.array = array;
  }

  public T get(int index) throws NumericArrayWrapperException {
    if (index < 0 || index > array.length - 1) {
      throw new NumericArrayWrapperException("Index out of bounds");
    }

    return array[index];
  }

  public void set(int index, T value) {
    array[index] = value;
  }

  public T[] getArray() {
    return this.array;
  }

  public int length() {
    return array.length;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("GenericArrayWrapper{");
    builder.append("array=");
    builder.append(Arrays.toString(array));
    builder.append("}");
    // javac should take care of these inefficient operations by itself,
    // so it is not necessary to do so
    return builder.toString();
  }

  // basically Arrays.hashCode() implementation
  @Override
  public int hashCode() {
    if (array == null) {
      return 0;
    }
    // result = 1; to eliminate collision on arrays like Integer[] {0} and Integer[] {}
    int result = 1;
    for (T element : array) {
      // 31 is a number which when used as a multiplier gives even bit distribution
      result = 31 * result + (element == null ? 0 : element.hashCode());
    }
    return result;
  }

  @Override
  public boolean equals(Object other) {
    // Check if the same object
    if (this == other) return true;

    // Check for null and class match
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    // Now it is safe to cast the other object to GenericArrayWrapper
    @SuppressWarnings("unchecked")
    NumericArrayWrapper<T> wrapper = (NumericArrayWrapper<T>) other;

    return areEqualInside(this, wrapper);
  }

  private boolean areEqualInside(NumericArrayWrapper<T> wrapper1, NumericArrayWrapper<T> wrapper2) {
    T[] current = wrapper1.array;
    T[] other = wrapper2.array;
    // Check if the same object (in case of two nulls doesn't proceed)
    if (current == other) return true;

    // Check if any is null
    if (current == null || other == null) return false;

    // Check length
    if (current.length != other.length) return false;

    // .equals() for each object that lays in arrays
    for (int i = 0; i < current.length; i++) {
      T a = current[i];
      T b = other[i];
      // Same as Objects.equals()
      if (a == null ? b != null : !a.equals(b)) {
        return false;
      }
    }
    return true;
  }

  /// STATIC FACTORY METHOD ///

  public static <T extends Number & Comparable<T>> NumericArrayWrapper<T> of(T[] array) {
    return new NumericArrayWrapper<>(array);
  }

}
