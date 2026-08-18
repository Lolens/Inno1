package io.github.lolens.validator.impl;

import io.github.lolens.exception.ArrayDataValidatorException;
import io.github.lolens.validator.ArrayDataValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ArrayDataValidatorImpl implements ArrayDataValidator {
  /**
   * 1. [0-9]+ -> Required integer part of the number. ex: 123<br>
   * 2. (\.[0-9]+)? -> Optional decimal part of the number. Together with p.1 -> ex: 123.23<br>
   * p.1 and p.2 result in valid integer/decimal number selection<br>
   * 3. (,[0-9]+(\.[0-9]+)?)* -> Optional same (p.1 and p.2) group (,number)*<br>
   * So pattern is "number (,number)*"<br>
   * Line is valid as long as it contains at least one number. Any additional number require comma and same number pattern<br><br>
   * Blank lines (\n) do not pass this pattern, so they are omitted in Reader "phase"<br>
   * Leading zeros are not rejected by this pattern and that check is delegated to LEADING_ZERO_PATTERN.
   */
  private static final Pattern LINE_PATTERN = Pattern.compile("[0-9]+(\\.[0-9]+)?(,[0-9]+(\\.[0-9]+)?)*");

  private static final Pattern LEADING_ZERO_PATTERN = Pattern.compile("0\\d+");

  @Override
  public void validate(String line) {

    if (!LINE_PATTERN.matcher(line).matches()) {
      throw new ArrayDataValidatorException("Bad input. Line doesn't match required pattern");
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
