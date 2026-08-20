package io.github.lolens.inno1.repository.arraywrapper.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.listener.RepositoryListener;
import io.github.lolens.inno1.repository.arraywrapper.Repository;
import io.github.lolens.inno1.repository.arraywrapper.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ArrayWrapperRepositoryImpl implements Repository<Long, NumericArrayWrapper<?>> {

  private static final Logger logger = LoggerFactory.getLogger(ArrayWrapperRepositoryImpl.class);

  private static ArrayWrapperRepositoryImpl INSTANCE;

  private final Map<Long, NumericArrayWrapper<?>> STORAGE = new HashMap<>();
  private final List<RepositoryListener<NumericArrayWrapper<?>>> listeners = new ArrayList<>();

  public static ArrayWrapperRepositoryImpl instance() {
    if (INSTANCE == null) {
      INSTANCE = new ArrayWrapperRepositoryImpl();
    }
    return INSTANCE;
  }

  @Override
  public NumericArrayWrapper<?> save(NumericArrayWrapper<?> value) {
    var result = STORAGE.put(value.getId(), value);
    listeners.forEach(l -> l.onSave(value, result));
    logger.debug("saved: {} | result of map.put() {}", value, result);
    return result;
  }

  @Override
  public void delete(Long id) {
    var result = STORAGE.put(id, null);
    listeners.forEach(l -> l.onDelete(result));
    logger.debug("deleted: {} | result of map.put() {}", id, result);
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

  @Override
  public List<NumericArrayWrapper<?>> findAll(Specification<NumericArrayWrapper<?>> specification) {
    return STORAGE.values().stream()
        .filter(specification)
        .toList();
  }

  public void addListener(RepositoryListener<NumericArrayWrapper<?>> listener) {
    listeners.add(listener);
  }

  public void removeListener(RepositoryListener<NumericArrayWrapper<?>> listener) {
    listeners.remove(listener);
  }

}
