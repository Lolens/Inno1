package io.github.lolens.inno1.repository.arraywrapper.specification.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.repository.arraywrapper.specification.Specification;
import io.github.lolens.inno1.repository.arraywrapper.specification.impl.aggregate.AggregateFunction;
import io.github.lolens.inno1.repository.arraywrapper.specification.impl.aggregate.ComparisonOperator;

public class AggregateSpecification implements Specification<NumericArrayWrapper<?>> {

  private final AggregateFunction function;
  private final ComparisonOperator operator;
  private final double expected;

  public AggregateSpecification(AggregateFunction function, ComparisonOperator operator, double expected) {
    this.function = function;
    this.operator = operator;
    this.expected = expected;
  }

  @Override
  public boolean test(NumericArrayWrapper<?> arrayWrapper) {
    double value = function.result(arrayWrapper.getArray());
    return operator.result(value, expected);
  }
}
