package io.github.lolens.inno1.service.array;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.exception.NumericArrayReaderException;
import io.github.lolens.inno1.parser.NumericArrayParser;
import io.github.lolens.inno1.repository.specification.Specification;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ArrayService {

  <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> createFromFile(
      Path filePath,
      Class<T> clazz,
      Function<String, T> converter
  ) throws NumericArrayReaderException;

  <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> createFromFile(
      Path filePath,
      Class<T> clazz,
      Function<String, T> converter,
      NumericArrayParser.ParseMode parseMode
  ) throws NumericArrayReaderException;

  <T extends Number & Comparable<T>> NumericArrayWrapper<?> persist(NumericArrayWrapper<T> arrayWrapper);

  Optional<NumericArrayWrapper<?>> get(long id);

  <T extends Number & Comparable<T>> Optional<NumericArrayWrapper<T>> get(long id, Class<T> expectedType);

  <T extends Number & Comparable<T>> Optional<NumericArrayWrapper<T>> get(NumericArrayWrapper<T> arrayWrapper);

  List<NumericArrayWrapper<?>> getAll();

  <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> getAll(Class<T> clazz);

  List<NumericArrayWrapper<?>> getAll(Specification<NumericArrayWrapper<?>> specification);

  <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> getAll(Class<T> clazz, Specification<NumericArrayWrapper<T>> specification);

  boolean isExists(long id);

  void delete(long id);

}
