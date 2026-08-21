package io.github.lolens.inno1.service.array.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.exception.NumericArrayReaderException;
import io.github.lolens.inno1.parser.NumericArrayParser;
import io.github.lolens.inno1.reader.NumericArrayReader;
import io.github.lolens.inno1.repository.Repository;
import io.github.lolens.inno1.repository.specification.Specification;
import io.github.lolens.inno1.service.array.ArrayService;
import io.github.lolens.inno1.validator.ArrayDataValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ArrayServiceImpl implements ArrayService {

  private static final Logger logger = LoggerFactory.getLogger(ArrayServiceImpl.class);

  private final Repository<Long, NumericArrayWrapper<?>> repository;
  private final NumericArrayReader reader;
  private final ArrayDataValidator validator;
  private final NumericArrayParser parser;

  public ArrayServiceImpl(
      Repository<Long, NumericArrayWrapper<?>> repository,
      NumericArrayReader reader,
      ArrayDataValidator validator,
      NumericArrayParser parser
  ) {
    this.repository = repository;
    this.reader = reader;
    this.parser = parser;
    this.validator = validator;
  }

  @Override
  public <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> createFromFile(
      Path filePath, Class<T> clazz, Function<String, T> converter
  ) throws NumericArrayReaderException {
    return createFromFile(filePath, clazz, converter, NumericArrayParser.ParseMode.SINGLE);
  }

  @Override
  public <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> createFromFile(
      Path filePath, Class<T> clazz, Function<String, T> converter, NumericArrayParser.ParseMode parseMode
  ) {
    reader.changeFilePath(filePath);

    try (Stream<String> stream = reader.stream()) {
      validator.validate(stream);
    }

    List<T[]> listOfArrays;
    try (Stream<String> stream = reader.stream()) {
      listOfArrays = parser.parse(clazz, stream, converter, parseMode);
    }

    List<NumericArrayWrapper<T>> result = new LinkedList<>();
    for (T[] array : listOfArrays) {
      result.add(NumericArrayWrapper.Factory.create(array));
    }

    return result;
  }

  // Repository

  @Override
  public <T extends Number & Comparable<T>> NumericArrayWrapper<?> persist(NumericArrayWrapper<T> arrayWrapper) {
    return repository.save(arrayWrapper);
  }

  @Override
  public void delete(long id) {
    repository.delete(id);
  }

  @Override
  public Optional<NumericArrayWrapper<?>> get(long id) {
    return repository.findById(id);
  }

  @Override
  public boolean isExists(long id) {
    return repository.exists(id);
  }

  @Override
  public <T extends Number & Comparable<T>> Optional<NumericArrayWrapper<T>> get(long id, Class<T> expectedType) {
    //noinspection unchecked
    return repository.findById(id)
        .filter(wrapper -> wrapper.getType().equals(expectedType))
        .map(wrapper -> (NumericArrayWrapper<T>) wrapper);
  }

  @Override
  public <T extends Number & Comparable<T>> Optional<NumericArrayWrapper<T>> get(NumericArrayWrapper<T> arrayWrapper) {
    //noinspection unchecked
    return repository.findById(arrayWrapper.getId())
        .filter(wrapper -> wrapper.getType().equals(arrayWrapper.getType()))
        .map(wrapper -> (NumericArrayWrapper<T>) wrapper);
  }

  @Override
  public List<NumericArrayWrapper<?>> getAll() {
    return repository.findAll();
  }

  @Override
  public List<NumericArrayWrapper<?>> getAll(Specification<NumericArrayWrapper<?>> specification) {
    return repository.findAll(specification);
  }

  @Override
  public <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> getAll(Class<T> clazz, Specification<NumericArrayWrapper<T>> specification) {
    return getAll(clazz).stream()
        .filter(specification)
        .toList();
  }

  @Override
  public <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> getAll(Class<T> clazz) {
    //noinspection unchecked
    return repository.findAll().stream()
        .filter(wrapper -> wrapper.getType().equals(clazz))
        .map(wrapper -> (NumericArrayWrapper<T>) wrapper)
        .toList();
  }

}
