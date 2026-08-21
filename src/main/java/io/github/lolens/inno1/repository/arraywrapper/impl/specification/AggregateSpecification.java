package io.github.lolens.inno1.repository.arraywrapper.impl.specification;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.repository.specification.Specification;
import io.github.lolens.inno1.repository.specification.impl.AggregateFunction;
import io.github.lolens.inno1.repository.specification.impl.ComparisonOperator;

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
