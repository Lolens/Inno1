package io.github.lolens.inno1.validator.impl;

import io.github.lolens.inno1.exception.ArrayDataValidatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayDataValidatorImplTest {

  private final ArrayDataValidatorImpl validator = new ArrayDataValidatorImpl();

  @Test
  void shouldThrowOnSpaces() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2 ,3");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1, 2,3");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate(" 1,2,3");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2,3 ");
    });
  }

  @Test
  void shouldPassOnValidString() {
    assertDoesNotThrow(() -> validator.validate("1,2,3"));
  }

  @Test
  void shouldThrowOnSymbols() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("#1,2 ,3");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2 ,3#");
    });
  }

  @Test
  void shouldThrowOnLetters() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2,3s");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("s1,2,3");
    });
  }

  @Test
  void shouldThrowOnLeadingZero() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("010");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("0010");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("00.123");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("00");
    });
  }

  @Test
  void shouldThrowOnLeadingOrTrailingComma() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2,3,");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate(",1,2,3");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate(",,1,2,3");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2,3,,");
    });
  }

  @Test
  void shouldThrowOnDoubleComma() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1,2,,3");
    });
  }

  @Test
  void shouldThrowOnLeadingSinglePeriod() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate(".3");
    });
  }

  @Test
  void shouldThrowOnWronglyPositionedPeriod() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("3.2.52.1,.12");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate(".3,2104.2.3.2,1");
    });
  }

  @Test
  void shouldThrowOnOtherWrongInputs() {
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1a2b");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("a1a");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1@2");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("1@");
    });
    assertThrows(ArrayDataValidatorException.class, () -> {
      validator.validate("@");
    });
  }




}
