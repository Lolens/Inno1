package io.github.lolens.inno1.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class NumericArrayParser {

  public NumericArrayParser() {

  }

  public enum ParseMode { SINGLE, LIMITED_BY_LINE }

  // After validation string already contains only delimiters,
  // double-precision number periods ( 20.182, 20123.211, etc..) and numbers
  private static final Pattern NUMBER_PATTERN = Pattern.compile("((\\d+\\.?\\d+)|(\\d+))");

  public <T extends Number & Comparable<T>> T[] parse(Class<T> clazz, Stream<String> stream, Function<String, T> converter) {
    return parseStream(clazz, stream, converter);
  }

  public <T extends Number & Comparable<T>> List<T[]> parse(Class<T> clazz,
                                                            Stream<String> stream,
                                                            Function<String, T> converter,
                                                            ParseMode mode) {
    return switch (mode) {
      case SINGLE -> Collections.singletonList(parseStream(clazz, stream, converter));
      case LIMITED_BY_LINE -> parseStreamLimitedByNewLine(clazz, stream, converter);
      case null -> throw new IllegalArgumentException("null passed as an argument to parse() method");
    };
  }

  /**
   * Parses stream and creates singular array from entire stream contents
   **/
  private <T extends Number & Comparable<T>> T[] parseStream(Class<T> clazz, Stream<String> stream, Function<String, T> converter) {
    List<T> list = new ArrayList<>();
    stream.forEach(line -> list.addAll(extractNumbers(line, converter)));
    return toArray(clazz, list);
  }

  // parseStreamLimitedByNewLine differentiates from parseStream() in that it creates array on each line stream provides
  /**
   * Parses stream and creates singular array from for each line stream returns
   **/
  private <T extends Number & Comparable<T>> List<T[]> parseStreamLimitedByNewLine(Class<T> clazz, Stream<String> stream, Function<String, T> converter) {
    List<T[]> result = new LinkedList<>();
    stream.forEach(line -> {
      result.add(toArray(clazz, extractNumbers(line, converter)));
    });
    return result;
  }

  public <T extends Number & Comparable<T>> T[] parse(Class<T> clazz, String line, Function<String, T> converter) {
    List<T> list = extractNumbers(line, converter);
    return toArray(clazz, list);
  }

  // generic type cannot be used in list.toArray(new T[0]) so it is needed to instantiate it by reflection
  private <T extends Number & Comparable<T>> T[] toArray(Class<T> clazz, List<T> list) {
    @SuppressWarnings("unchecked")
    T[] array = (T[]) Array.newInstance(clazz, list.size());
    return list.toArray(array);
  }

  private <T extends Number & Comparable<T>> List<T> extractNumbers(String line, Function<String, T> converter) {
    List<T> numbers = new ArrayList<>();
    Matcher matcher = NUMBER_PATTERN.matcher(line);
    while (matcher.find()) {
      numbers.add(converter.apply(matcher.group()));
    }
    return numbers;
  }


}
