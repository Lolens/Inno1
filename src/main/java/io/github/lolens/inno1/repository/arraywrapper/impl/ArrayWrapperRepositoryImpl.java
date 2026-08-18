package io.github.lolens.inno1.repository.arraywrapper.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.repository.arraywrapper.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayWrapperRepositoryImpl implements Repository<Long, NumericArrayWrapper<?>> {

  private static final Logger logger = LoggerFactory.getLogger(ArrayWrapperRepositoryImpl.class);

  private final Map<Long, NumericArrayWrapper<?>> STORAGE = new HashMap<>();

  @Override
  public NumericArrayWrapper<?> save(NumericArrayWrapper<?> value) {
    var result = STORAGE.put(value.getId(), value);
    logger.debug("saved: {} -> {}", value, result);
    return result;
  }

  @Override
  public void delete(Long id) {
    var result = STORAGE.put(id, null);
    logger.debug("deleted: {} -> {}", id, result);
  }

  @Override
  public boolean exists(Long id) {
    return STORAGE.containsKey(id);
  }

  @Override
  public Optional<NumericArrayWrapper<?>> findById(Long id) {
    return Optional.ofNullable(STORAGE.get(id));
  }

  @Override
  public List<NumericArrayWrapper<?>> findAll() {
    return new ArrayList<>(STORAGE.values());
  }
}
