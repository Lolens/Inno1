package io.github.lolens.inno1.entity;

import io.github.lolens.inno1.exception.NumericArrayWrapperException;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NumericArrayWrapperTest {

  private NumericArrayWrapper<Integer> arrayWrapper;
  private Integer[] arrayContents;

  @BeforeEach
  void setUp() {
    arrayContents = new Integer[] {1, 5, 2, 53, 1242, 1, 25, 1254, 124, 12};
    arrayWrapper = NumericArrayWrapper.Factory.create(arrayContents);
  }


  // Factory method is used because constructor is private and
  // I don't want to use reflection to instantiate NumericArrayWrapper for tests
  // If constructor is open its usage becomes a recommendation and not a must

  @Test
  void shouldLengthReturnInnerArrayLength() {
    assertEquals(arrayWrapper.length(), arrayWrapper.getArray().length);
  }

  @Nested()
  @DisplayName("Immutability tests")
  class ImmutabilityTests {

    @Test
    void shouldWrapperCopyConstructorCopyInnerArray() {
      NumericArrayWrapper<Integer> copy = NumericArrayWrapper.Factory.create(arrayWrapper);
      assertNotSame(copy.getArray(), arrayWrapper.getArray());
    }

    @Test
    void shouldArrayCopyConstructorCopyInnerArray() {
      NumericArrayWrapper<Integer> copy = NumericArrayWrapper.Factory.create(arrayContents);
      assertNotSame(copy.getArray(), arrayWrapper.getArray());
    }

    @Test
    void shouldWithValueCreateCopy() {
      NumericArrayWrapper<Integer> copy = arrayWrapper.withValue(0,10);
      assertNotEquals(copy.get(0), arrayWrapper.get(0));
    }


  }

  @Nested()
  @DisplayName("get() method")
  class GetMethodTests {

    @Test
    void shouldReturnRequiredElementByIndex() {
      assertEquals(5, arrayWrapper.get(1));
    }

    @Test
    void shouldThrowExceptionForNegativeIndex() {
      assertThrows(NumericArrayWrapperException.class, () -> arrayWrapper.get(-1));
    }

    @Test
    @DisplayName("length() method")
    void shouldThrowExceptionOnIndexEqualToLength() {
      assertThrows(NumericArrayWrapperException.class, () -> arrayWrapper.get(arrayWrapper.length()));
    }

    @Nested()
    @DisplayName("generated (self-implemented) methods")
    class GeneratedMethodsTest {

      @Test
      void shouldHashCodeProduceSameHashValue() {
        int expectedHash = Arrays.hashCode(arrayWrapper.getArray());
        int classHash = arrayWrapper.hashCode();
        assertEquals(expectedHash, classHash);
      }

      @Test
      void shouldNotBeEqualOnDifferentClasses() {
        int[] arr = new int[]{1, 2, 3};
        assertNotEquals(arr, arrayWrapper);
      }

      @Test
      void shouldBeEqualOnSameObject() {
        assertEquals(arrayWrapper, arrayWrapper);
      }

      @Test
      void shouldNotBeEqualOnNull() {
        assertNotEquals(null, arrayWrapper);
      }

      @Test
      void shouldNotBeEqualOnSameInnerArrayContents() {
        Integer[] arr = new Integer[]{1, 2, 3};
        NumericArrayWrapper<Integer> wrapper = NumericArrayWrapper.Factory.create(new Integer[]{1, 2, 3});
        assertNotEquals(arr, wrapper);
      }

      @Test
      void shouldNotBeEqualOnSameInnerArrayReference() {
        Integer[] arr = new Integer[]{1, 2, 3};
        NumericArrayWrapper<Integer> wrapper = NumericArrayWrapper.Factory.create(arr);
        assertNotEquals(arr, wrapper);
      }

      @Test
      void shouldNotBeEqualOnDifferentLength() {
        NumericArrayWrapper<Integer> wrapper = NumericArrayWrapper.Factory.create(new Integer[]{1, 2, 3});
        assertNotEquals(wrapper, arrayWrapper);
      }


    }

  }


}
