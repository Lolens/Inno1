package io.github.lolens.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class NumericArrayParser {

  public NumericArrayParser() {

  }

  // After validation string already contains only delimiters,
  // double-precision number periods ( 20.182, 20123.211, etc..) and numbers

  // pseudo-regex pattern: (1+ digit -> optional period -> 1+ digit)
  // works on lines with any delimiter present
  private static Pattern DECIMAL_NUMBER_PATTERN = Pattern.compile("((\\d+\\.?\\d+)|(\\d+))");

  public <T extends Number & Comparable<T>> T[] parse(Class<T> clazz, Stream<String> stream, Function<String, T> converter) {
    List<T> list = new ArrayList<>();
    stream.forEach(line -> {
      Matcher matcher = DECIMAL_NUMBER_PATTERN.matcher(line);
      while (matcher.find()) {
        list.add(converter.apply(matcher.group()));
      }
    });

    // generic type cannot be used in list.toArray(new T[0]) so it is needed to instantiate it by reflection
    @SuppressWarnings("unchecked")
    T[] array = list.toArray((T[]) Array.newInstance(clazz, list.size()));

    return array;
  }

  public <T extends Number & Comparable<T>> T[] parse(Class<T> clazz, String line) {
    throw new IllegalStateException("Not implemented");
  }


}
