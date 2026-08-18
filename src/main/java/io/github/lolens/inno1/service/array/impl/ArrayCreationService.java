package io.github.lolens.inno1.service.array.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.function.Function;

public interface ArrayCreationService {

  <T extends Number & Comparable<T>> NumericArrayWrapper<T> createFromFile(
      Path filePath,
      Class<T> clazz,
      Function<String, T> converter
  ) throws FileNotFoundException;

}
