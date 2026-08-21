package io.github.lolens.inno1.repository;

import io.github.lolens.inno1.repository.specification.Specification;

import java.util.List;
import java.util.Optional;

public interface Repository<K, V> {

  V save(V value);
  void delete(K id);

  boolean exists(K id);

  Optional<V> findById(K id);

  List<V> findAll();
  List<V> findAll(Specification<V> specification);

}
