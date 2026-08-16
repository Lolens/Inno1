package io.github.lolens.validator.impl;

import io.github.lolens.exception.ArrayDataValidatorException;
import io.github.lolens.validator.ArrayDataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ArrayDataValidatorImpl implements ArrayDataValidator {

  // Blank lines do not pass this pattern, so they are omitted in Reader "phase"
  private static final Pattern ALLOWED_SYMBOLS_PATTERN = Pattern.compile("^[0-9.]+(,[0-9.]+)*$");
  private static final Pattern LEADING_ZERO_PATTERN = Pattern.compile("0\\d+");

  @Override
  public void validate(String line) {

    if (!ALLOWED_SYMBOLS_PATTERN.matcher(line).matches()) {
      throw new ArrayDataValidatorException("Bad input (stream)");
    }

    Matcher zeroMatcher = LEADING_ZERO_PATTERN.matcher(line);
    if (zeroMatcher.find()) {
      throw new ArrayDataValidatorException("Number mustn't have leading zeros. Pos: " + zeroMatcher.start());
    }
  }

  @Override
  public void validate(Stream<String> stream) {
    stream.forEach(this::validate);
  }
}
