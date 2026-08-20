package io.github.lolens.inno1;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.listener.RepositoryListener;
import io.github.lolens.inno1.parser.NumericArrayParser;
import io.github.lolens.inno1.reader.NumericArrayReader;
import io.github.lolens.inno1.repository.arraywrapper.impl.ArrayWrapperRepositoryImpl;
import io.github.lolens.inno1.repository.arraywrapper.specification.impl.TypeSpecification;
import io.github.lolens.inno1.service.array.impl.ArrayServiceImpl;
import io.github.lolens.inno1.service.math.MathOperationService;
import io.github.lolens.inno1.service.math.impl.MathOperationServiceImpl;
import io.github.lolens.inno1.service.sort.SortService;
import io.github.lolens.inno1.service.sort.impl.SortServiceImpl;
import io.github.lolens.inno1.service.sort.strategy.SortStrategy;
import io.github.lolens.inno1.validator.impl.ArrayDataValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);
  private static final Path FILE_PATH_1;
  private static final Path FILE_PATH_2;
  private static final Path FILE_PATH_3;

  static {
    try {
      FILE_PATH_1 = getFilePathFromResources("input1.txt");
      FILE_PATH_2 = getFilePathFromResources("input2.txt");
      FILE_PATH_3 = getFilePathFromResources("input3.txt");
    } catch (Exception e) {
      // If file can't be used program should stop execution as further actions cannot be done without a data source
      throw new RuntimeException("Failed to initialize FILE_PATH", e);
    }

  }

  private static Path getFilePathFromResources(String resourceName) throws FileNotFoundException, URISyntaxException {
    URL fileURL = Main.class.getClassLoader().getResource(resourceName);
    if (fileURL == null) throw new FileNotFoundException("Specified file is null.");
    return Path.of(fileURL.toURI());
  }


  public static void main(String[] args) {

    // Service instantiation
    SortService sortService = new SortServiceImpl();
    ArrayServiceImpl arrayService = new ArrayServiceImpl(
        ArrayWrapperRepositoryImpl.instance(),
        new NumericArrayReader(),
        new ArrayDataValidatorImpl(),
        new NumericArrayParser()
    );

    List<NumericArrayWrapper<BigInteger>> bigIntegerWrapperList;
    List<NumericArrayWrapper<Integer>> integerWrapperList;
    List<NumericArrayWrapper<Double>> doubleWrapperList;

    // ArrayWrapper creation

    bigIntegerWrapperList = arrayService.createFromFile(FILE_PATH_1, BigInteger.class, BigInteger::new, NumericArrayParser.ParseMode.LIMITED_BY_LINE);
    integerWrapperList = arrayService.createFromFile(FILE_PATH_2, Integer.class, Integer::parseInt, NumericArrayParser.ParseMode.LIMITED_BY_LINE);
    doubleWrapperList = arrayService.createFromFile(FILE_PATH_3, Double.class, Double::parseDouble, NumericArrayParser.ParseMode.LIMITED_BY_LINE);

    // Sorting
    NumericArrayWrapper<Double> wrapper = doubleWrapperList.getFirst();
    logger.info("ArrayWrapper before sorting: {}", wrapper);
    NumericArrayWrapper<Double> sorted = sortService.sort(wrapper, SortStrategy.bubble());
    logger.info("ArrayWrapper after sorting: {}", sorted);

    // Math service
    MathOperationService mathService = new MathOperationServiceImpl();


    logResult(mathService.max(wrapper), "max");
    logResult(mathService.min(wrapper), "min");
    logResult(mathService.average(wrapper), "average");
    logResult(mathService.sum(wrapper), "sum");

    // Repository

    ArrayWrapperRepositoryImpl.instance().addListener(new RepositoryListener<>() {
      @Override
      public void onSave(NumericArrayWrapper<?> savedElement, NumericArrayWrapper<?> oldElement) {
        logger.info("listener: {}, {}", savedElement, oldElement);
      }
    });

    bigIntegerWrapperList.forEach(arrayService::persist);
    integerWrapperList.forEach(arrayService::persist);
    doubleWrapperList.forEach(arrayService::persist);

    logger.info(arrayService.getAll(new TypeSpecification(Integer.class)).toString());

    logger.info(arrayService.getAll(Integer.class).toString());




  }

  private static <T> void logResult(Optional<T> result, String operationName) {
    result.ifPresentOrElse(
        value -> logger.info("ArrayWrapper {}: {}", operationName, value),
        () -> logger.info("ArrayWrapper {} is not present", operationName)
    );
  }


}