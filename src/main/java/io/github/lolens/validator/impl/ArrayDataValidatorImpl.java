package io.github.lolens.validator.impl;

import io.github.lolens.exception.ArrayDataValidatorException;
import io.github.lolens.validator.ArrayDataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ArrayDataValidatorImpl implements ArrayDataValidator {

  // Catches both "1.023,2024.21" and "1,5,12"
  // Only checks for allowed symbols
  private static final Pattern ALLOWED_SYMBOLS_PATTERN = Pattern.compile("(?<number>[0-9.]+)|(?<delimiter>,)");
  private static final Pattern LEADING_ZERO_PATTERN = Pattern.compile("0\\d+");

  @Override
  public void validate(String line) throws ArrayDataValidatorException {

    Matcher zeroMatcher = LEADING_ZERO_PATTERN.matcher(line);
    if (zeroMatcher.find()) {
      throw new ArrayDataValidatorException("Number mustn't have leading zeros. Pos: " +  zeroMatcher.start());
    }

    if (ALLOWED_SYMBOLS_PATTERN.matcher(line).matches()) {
      throw new ArrayDataValidatorException("Bad input (stream)");
    }

  }

  @Override
  public void validate(Stream<String> stream) throws ArrayDataValidatorException {
    stream.forEach(this::validate);
  }
}
