package io.github.lolens.service.array;

import io.github.lolens.Main;
import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.parser.NumericArrayParser;
import io.github.lolens.reader.NumericArrayReader;
import io.github.lolens.validator.ArrayDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.stream.Stream;

public class ArrayCreationService {

  private static final Logger logger = LoggerFactory.getLogger(ArrayCreationService.class);

  private final NumericArrayReader reader;
  private final ArrayDataValidator validator;
  private final NumericArrayParser parser;

  public ArrayCreationService(
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
    Stream<String> stream = reader.lines();
    // validator
    validator.validate(stream);
    // parser
    T[] rawArray = parser.parse(clazz, reader.lines(), converter);


    return NumericArrayWrapper.of(rawArray);
  }


}
