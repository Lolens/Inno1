package io.github.lolens.inno1;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.parser.NumericArrayParser;
import io.github.lolens.inno1.reader.NumericArrayReader;
import io.github.lolens.inno1.repository.arraywrapper.impl.ArrayWrapperRepositoryImpl;
import io.github.lolens.inno1.service.array.impl.ArrayServiceImpl;
import io.github.lolens.inno1.service.math.MathOperationService;
import io.github.lolens.inno1.service.math.impl.MathOperationServiceImpl;
import io.github.lolens.inno1.service.sort.SortService;
import io.github.lolens.inno1.service.sort.strategy.SortStrategy;
import io.github.lolens.inno1.service.sort.impl.SortServiceImpl;
import io.github.lolens.inno1.validator.impl.ArrayDataValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  private static final Path FILE_PATH;

  static {
    try {
      URL fileURL = Main.class.getClassLoader().getResource("input.txt");
      if (fileURL == null) throw new FileNotFoundException("Specified file is null.");
      FILE_PATH = Path.of(fileURL.toURI());
    } catch (Exception e) {
      // If file can't be used program should stop execution as further actions cannot be done without a data source
      throw new RuntimeException("Failed to initialize FILE_PATH", e);
    }

  }


  public static void main(String[] args) {

    // Service instantiation
    SortService sortService = new SortServiceImpl();
    ArrayServiceImpl arrayService = new ArrayServiceImpl(
        new ArrayWrapperRepositoryImpl(),
        new NumericArrayReader(),
        new ArrayDataValidatorImpl(),
        new NumericArrayParser()
    );

    NumericArrayWrapper<BigInteger> arrayWrapper = null;

    // ArrayWrapper creation
    try {
      arrayWrapper = arrayService.createFromFile(FILE_PATH, BigInteger.class, BigInteger::new);
    } catch (FileNotFoundException e) {
      logger.error("Specified file path ({}) does not contain a file. Program can't proceed execution", FILE_PATH);
      System.exit(1);
    }

    // Sorting
    logger.info("ArrayWrapper before sorting: {}", arrayWrapper);
    NumericArrayWrapper<BigInteger> sorted = sortService.sort(arrayWrapper, SortStrategy.bubble());
    logger.info("ArrayWrapper after sorting: {}", sorted);

    // Math service
    MathOperationService mathService = new MathOperationServiceImpl();


    logResult(mathService.max(arrayWrapper), "max");
    logResult(mathService.min(arrayWrapper), "min");
    logResult(mathService.average(arrayWrapper), "average");
    logResult(mathService.sum(arrayWrapper), "sum");

    arrayService.persist(arrayWrapper);

    logger.info("Persists: {}", arrayService.isExists(0));
    logger.info(arrayService.getAll(Integer.class).toString());

  }

  private static <T> void logResult(Optional<T> result, String operationName) {
    result.ifPresentOrElse(
        value -> logger.info("ArrayWrapper {}: {}", operationName, value),
        () -> logger.info("ArrayWrapper {} is not present", operationName)
    );
  }

}