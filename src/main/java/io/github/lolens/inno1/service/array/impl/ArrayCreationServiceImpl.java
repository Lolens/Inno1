package io.github.lolens.inno1.service.array.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.parser.NumericArrayParser;
import io.github.lolens.inno1.reader.NumericArrayReader;
import io.github.lolens.inno1.validator.ArrayDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public class ArrayCreationServiceImpl implements ArrayCreationService {

  private static final Logger logger = LoggerFactory.getLogger(ArrayCreationServiceImpl.class);

  private final NumericArrayReader reader;
  private final ArrayDataValidator validator;
  private final NumericArrayParser parser;

  public ArrayCreationServiceImpl(
      NumericArrayReader reader,
      ArrayDataValidator validator,
      NumericArrayParser parser
  ) {
    this.reader = reader;
    this.parser = parser;
    this.validator = validator;
  }

  public <T extends Number & Comparable<T>> NumericArrayWrapper<T> createFromFile(
      Path filePath, Class<T> clazz, Function<String, T> converter
  ) throws FileNotFoundException {
    // reader
    reader.changeFilePath(filePath);

    // validator
    try (Stream<String> stream = reader.stream()) {
      validator.validate(stream);
    }

    // parser
    T[] rawArray;
    try (Stream<String> stream = reader.stream()) {
      rawArray = parser.parse(clazz, stream, converter);
    }

    return NumericArrayWrapper.of(rawArray);
  }


}
