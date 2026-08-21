package io.github.lolens.inno1.repository.arraywrapper.impl.specification;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.repository.specification.Specification;

public class TypeSpecification implements Specification<NumericArrayWrapper<?>> {

  private final Class<?> type;

  public TypeSpecification(Class<?> type) {
    this.type = type;
  }

  @Override
  public boolean test(NumericArrayWrapper<?> arrayWrapper) {
    return arrayWrapper.getType().equals(type);
  }
}
