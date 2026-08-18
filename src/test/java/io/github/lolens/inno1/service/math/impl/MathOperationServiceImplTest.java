package io.github.lolens.inno1.service.math.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.service.math.MathOperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MathOperationServiceImplTest {

  private final MathOperationService service = new MathOperationServiceImpl();

  private NumericArrayWrapper<Double> arrayWrapper;
  private Double[] wrapperContents;

  private double[] primitiveContents;

  @BeforeEach
  void setUp() {
    primitiveContents = new double[] {1.0, 5.0, 2.0, 53.0, 1242.0, 1.0, 25.0, 1254.0, 124.0, 12.0};
    wrapperContents = new Double[] {1.0, 5.0, 2.0, 53.0, 1242.0, 1.0, 25.0, 1254.0, 124.0, 12.0};
    arrayWrapper = NumericArrayWrapper.Factory.create(wrapperContents);
  }

  @Test
  void shouldReturnCorrectSum() {
    double expected = Arrays.stream(primitiveContents).sum();
    double real = service.sum(arrayWrapper).get();

    assertEquals(expected, real);
  }

  @Test
  void shouldReturnCorrectAverage() {
    double expected = Arrays.stream(primitiveContents).average().getAsDouble();
    double real = service.average(arrayWrapper).get();

    assertEquals(expected, real);
  }

  @Test
  void shouldReturnCorrectMin() {
    double expected = Arrays.stream(primitiveContents).min().getAsDouble();
    double real = service.min(arrayWrapper).get();

    assertEquals(expected, real);
  }

  @Test
  void shouldReturnCorrectMax() {
    double expected = Arrays.stream(primitiveContents).max().getAsDouble();
    double real = service.max(arrayWrapper).get();

    assertEquals(expected, real);
  }



}
