package io.github.lolens.inno1.repository.arraywrapper.impl.specification;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.repository.specification.Specification;

public class IdentifierSpecification implements Specification<NumericArrayWrapper<?>> {

  private final long id;

  public IdentifierSpecification(int id) {
    this.id = id;
  }

  @Override
  public boolean test(NumericArrayWrapper<?> arrayWrapper) {
    return arrayWrapper.getId() == this.id;
  }
}
