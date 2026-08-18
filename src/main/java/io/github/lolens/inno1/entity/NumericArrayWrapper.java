package io.github.lolens.inno1.entity;

import io.github.lolens.inno1.exception.NumericArrayWrapperException;
import io.github.lolens.inno1.util.IdentifierCounter;

import java.util.Arrays;


/**
 * Immutable generic array wrapper that is used to allow mathematical operations
 * that are bound to be used on comparable number objects
 * @param <T> the type inner array will be using
 */
public class NumericArrayWrapper<T extends Number & Comparable<T>> {
  private static final IdentifierCounter counter = IdentifierCounter.zero();

  // Needed to store all wrappers in a single repository. Because Java has type-erasure
  private final Class<T> type;

  private final T[] array;
  private final long id;

  /** Constructor used to create a copy with new id **/
  private NumericArrayWrapper(long id, T[] array) {
    // Although BigInteger/Double/Long... etc... are immutable,
    // <T extends Number & Comparable<T>> does not guarantee type immutability
    // and java doesn't have a way of making a deep copy so we make a shallow copy
    this.id = id;
    this.array = Arrays.copyOf(array, array.length);
    //noinspection unchecked
    this.type = (Class<T>) array.getClass().getComponentType();
  }

  /** Constructor used to create a copy with new id **/
  private NumericArrayWrapper(long id, NumericArrayWrapper<T> wrapper) {
    this.id = id;
    this.array = Arrays.copyOf(wrapper.array, wrapper.array.length);
    //noinspection unchecked
    this.type = (Class<T>) wrapper.array.getClass().getComponentType();
  }

  /** Constructor used to create a copy  **/
  private NumericArrayWrapper(NumericArrayWrapper<T> wrapper) {
    this.id = wrapper.getId();
    this.array = Arrays.copyOf(wrapper.array, wrapper.array.length);
    //noinspection unchecked
    this.type = (Class<T>) wrapper.array.getClass().getComponentType();
  }

  public T get(int index) throws NumericArrayWrapperException {
    checkOutOfBounds(index);
    return array[index];
  }

  public long getId() {
    return id;
  }

  public Class<T> getType() {
    return type;
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
    return new NumericArrayWrapper<>(id, newArray);
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
    final StringBuilder sb = new StringBuilder("NumericArrayWrapper{");
    sb.append("type=").append(type);
    sb.append(", array=").append(Arrays.toString(array));
    sb.append(", id=").append(id);
    sb.append('}');
    return sb.toString();
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
    if (wrapper1.id != wrapper2.id) return false;

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

  public static class Factory {

    /** Factory method used to create a full copy  **/
    public static <T extends Number & Comparable<T>> NumericArrayWrapper<T> create(NumericArrayWrapper<T> arrayWrapper) {
      return new NumericArrayWrapper<>(arrayWrapper);
    }

    /** Factory method used to create a full copy with new id **/
    public static <T extends Number & Comparable<T>> NumericArrayWrapper<T> create(long id, NumericArrayWrapper<T> arrayWrapper) {
      return new NumericArrayWrapper<>(id, arrayWrapper);
    }

    /** Factory method used to create new NumericArrayWrapper **/
    public static <T extends Number & Comparable<T>> NumericArrayWrapper<T> create(T[] array) {
      return new NumericArrayWrapper<>(counter.getAndIncrement(), array);
    }
  }

}
