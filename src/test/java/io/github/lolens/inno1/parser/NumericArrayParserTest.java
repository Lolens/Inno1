package io.github.lolens.inno1.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Written by AI
 */
class NumericArrayParserTest {

  private final NumericArrayParser parser = new NumericArrayParser();

  @Nested
  @DisplayName("parse(Class, Stream<String>, Function)")
  class ParseStreamTests {

    @Test
    @DisplayName("Should parse multiple lines with single number each")
    void shouldParseMultipleLines() {
      Stream<String> stream = Stream.of("1", "2", "3");

      Integer[] result = parser.parse(Integer.class, stream, Integer::parseInt);

      assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test
    @DisplayName("Should parse a single line containing multiple numbers")
    void shouldParseLineWithMultipleNumbers() {
      Stream<String> stream = Stream.of("1,2,3");

      Integer[] result = parser.parse(Integer.class, stream, Integer::parseInt);

      assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test
    @DisplayName("Should parse decimal numbers")
    void shouldParseDecimalNumbers() {
      Stream<String> stream = Stream.of("1.5,2.75,3.0");

      Double[] result = parser.parse(Double.class, stream, Double::parseDouble);

      assertArrayEquals(new Double[]{1.5, 2.75, 3.0}, result);
    }

    @Test
    @DisplayName("Should return empty array for empty stream")
    void shouldReturnEmptyArrayForEmptyStream() {
      Stream<String> stream = Stream.empty();

      Integer[] result = parser.parse(Integer.class, stream, Integer::parseInt);

      assertEquals(0, result.length);
    }

    @Test
    @DisplayName("Should skip lines without any number")
    void shouldSkipLinesWithoutNumbers() {
      Stream<String> stream = Stream.of("1,2", "no numbers here", "3");

      Integer[] result = parser.parse(Integer.class, stream, Integer::parseInt);

      assertArrayEquals(new Integer[]{1, 2, 3}, result);
    }

    @Test
    @DisplayName("Should apply custom converter function")
    void shouldApplyCustomConverter() {
      Stream<String> stream = Stream.of("5,10");

      Integer[] result = parser.parse(Integer.class, stream, s -> Integer.parseInt(s) * 2);

      assertArrayEquals(new Integer[]{10, 20}, result);
    }
  }

  @Nested
  @DisplayName("parse(Class, String, Function)")
  class ParseSingleLineTests {

    @Test
    @DisplayName("Should parse a single line with multiple numbers")
    void shouldParseSingleLine() {
      Integer[] result = parser.parse(Integer.class, "10,20,30", Integer::parseInt);

      assertArrayEquals(new Integer[]{10, 20, 30}, result);
    }

    @Test
    @DisplayName("Should parse single number without delimiter")
    void shouldParseSingleNumber() {
      Integer[] result = parser.parse(Integer.class, "42", Integer::parseInt);

      assertArrayEquals(new Integer[]{42}, result);
    }

    @Test
    @DisplayName("Should return empty array for line without numbers")
    void shouldReturnEmptyArrayForLineWithoutNumbers() {
      Integer[] result = parser.parse(Integer.class, "no numbers", Integer::parseInt);

      assertEquals(0, result.length);
    }

    @Test
    @DisplayName("Should parse decimal and integer numbers mixed in one line")
    void shouldParseMixedNumbers() {
      Double[] result = parser.parse(Double.class, "1,2.5,3", Double::parseDouble);

      assertArrayEquals(new Double[]{1.0, 2.5, 3.0}, result);
    }
  }
}