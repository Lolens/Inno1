package io.github.lolens.inno1.entity;

import io.github.lolens.inno1.exception.NumericArrayWrapperException;

import java.util.Arrays;


/**
 * Immutable generic array wrapper that is used to allow mathematical operations
 * that are bound to be used on comparable number objects
 * @param <T> the type inner array will be using
 */
public class NumericArrayWrapper<T extends Number & Comparable<T>> {
  private final T[] array;

  /**
   * Shallow copy constructor
   **/
  public NumericArrayWrapper(T[] array) {
    // Although BigInteger/Double/Long... etc... are immutable,
    // <T extends Number & Comparable<T>> does not guarantee type immutability
    // and java doesn't have a way of making a deep copy so we make a shallow copy
    this.array = Arrays.copyOf(array, array.length);
  }

  public NumericArrayWrapper(NumericArrayWrapper<T> wrapper) {
    this.array = Arrays.copyOf(wrapper.array, wrapper.array.length);
  }

  public T get(int index) throws NumericArrayWrapperException {
    checkOutOfBounds(index);
    return array[index];
  }

  /**
   * @param index element index
   * @param value element value
   * @return immutable copy with element changed
   */
  public NumericArrayWrapper<T> withValue(int index, T value) {
    checkOutOfBounds(index);
    T[] newArray = Arrays.copyOf(array, array.length);
    newArray[index] = value;
    return new NumericArrayWrapper<>(newArray);
  }

  private void checkOutOfBounds(int index) {
    if (index < 0 || index > array.length - 1) {
      throw new NumericArrayWrapperException("Index out of bounds");
    }
  }

  /**
   * @return shallow copy of underlying array
   **/
  public T[] getArray() {
    return Arrays.copyOf(array, array.length);
  }

  public int[] getIntArray() {
    return Arrays.stream(array).mapToInt(Number::intValue).toArray();
  }

  public double[] getDoubleArray() {
    return Arrays.stream(array).mapToDouble(Number::doubleValue).toArray();
  }

  public long[] getLongArray() {
    return Arrays.stream(array).mapToLong(Number::longValue).toArray();
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
    // so it is not necessary to use
    // StringBuilder/StringJoiner on situations like that
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

  public static <T extends Number & Comparable<T>> NumericArrayWrapper<T> of(NumericArrayWrapper<T> arrayWrapper) {
    return new NumericArrayWrapper<>(arrayWrapper);
  }

  public static <T extends Number & Comparable<T>> NumericArrayWrapper<T> of(T[] array) {
    return new NumericArrayWrapper<>(array);
  }

}
