package io.github.lolens;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.parser.NumericArrayParser;
import io.github.lolens.reader.NumericArrayReader;
import io.github.lolens.service.array.impl.ArrayCreationServiceImpl;
import io.github.lolens.service.math.MathOperationService;
import io.github.lolens.service.math.impl.MathOperationServiceImpl;
import io.github.lolens.service.sort.SortService;
import io.github.lolens.service.sort.strategy.SortStrategy;
import io.github.lolens.service.sort.impl.SortServiceImpl;
import io.github.lolens.validator.impl.ArrayDataValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  private static final Path FILE_PATH;

  static {
    try {
      FILE_PATH = Path.of(Main.class.getClassLoader().getResource("input.txt").toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }


  public static void main(String[] args) {

    // Service instantiation
    SortService sortService = new SortServiceImpl();
    ArrayCreationServiceImpl creationService = new ArrayCreationServiceImpl(
        new NumericArrayReader(),
        new ArrayDataValidatorImpl(),
        new NumericArrayParser()
    );

    NumericArrayWrapper<BigInteger> arrayWrapper = null;

    // ArrayWrapper creation
    try {
      arrayWrapper = creationService.createFromFile(FILE_PATH, BigInteger.class, BigInteger::new);


    } catch (FileNotFoundException e) {
      logger.warn("Specified file path does not contain a file");
    }

    // Sorting
    logger.info("ArrayWrapper before sorting: {}", arrayWrapper);

    NumericArrayWrapper<BigInteger> sorted = sortService.sort(arrayWrapper, SortStrategy.bubble());

    logger.info("ArrayWrapper after sorting: {}", sorted);

    // Math service
    MathOperationService mathService = new MathOperationServiceImpl();

    // Optional should always be present, so we get() it without isPresent()
    logger.info("ArrayWrapper max: {}", mathService.max(arrayWrapper).get());
    logger.info("ArrayWrapper min: {}", mathService.min(arrayWrapper).get());
    logger.info("ArrayWrapper average: {}", mathService.average(arrayWrapper).get());
    logger.info("ArrayWrapper sum: {}", mathService.sum(arrayWrapper).get());


  }

}