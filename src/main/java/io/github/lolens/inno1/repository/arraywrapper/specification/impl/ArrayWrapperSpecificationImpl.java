package io.github.lolens.inno1.repository.arraywrapper.specification.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.repository.arraywrapper.specification.ArrayWrapperSpecification;

public class ArrayWrapperSpecificationImpl implements ArrayWrapperSpecification {
  @Override
  public boolean specified(NumericArrayWrapper<?> arrayWrapper) {
    return false;
  }
}
