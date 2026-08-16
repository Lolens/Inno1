package io.github.lolens.validator;

import io.github.lolens.exception.ArrayDataValidatorException;

import java.util.stream.Stream;

public interface ArrayDataValidator {

  void validate(String line) throws ArrayDataValidatorException;

  void validate(Stream<String> stream) throws ArrayDataValidatorException;

}
