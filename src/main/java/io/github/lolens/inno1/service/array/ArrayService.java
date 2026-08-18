package io.github.lolens.inno1.service.array;

import io.github.lolens.inno1.entity.NumericArrayWrapper;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ArrayService {

  <T extends Number & Comparable<T>> NumericArrayWrapper<T> createFromFile(
      Path filePath,
      Class<T> clazz,
      Function<String, T> converter
  ) throws FileNotFoundException;

  <T extends Number & Comparable<T>> NumericArrayWrapper<?> persist(NumericArrayWrapper<T> arrayWrapper);

  Optional<NumericArrayWrapper<?>> get(long id);

  <T extends Number & Comparable<T>> Optional<NumericArrayWrapper<T>> get(long id, Class<T> expectedType);

  <T extends Number & Comparable<T>> Optional<NumericArrayWrapper<T>> get(NumericArrayWrapper<T> arrayWrapper);

  List<NumericArrayWrapper<?>> getAll();

  <T extends Number & Comparable<T>> List<NumericArrayWrapper<T>> getAll(Class<T> clazz);

  boolean isExists(long id);

  void delete(long id);

}
