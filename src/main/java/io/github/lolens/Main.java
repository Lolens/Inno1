package io.github.lolens;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.parser.NumericArrayParser;
import io.github.lolens.reader.NumericArrayReader;
import io.github.lolens.service.array.ArrayCreationService;
import io.github.lolens.service.sortstrategy.SortService;
import io.github.lolens.service.sortstrategy.SortStrategy;
import io.github.lolens.service.sortstrategy.impl.SortServiceImpl;
import io.github.lolens.service.sortstrategy.impl.BubbleSortStrategy;
import io.github.lolens.validator.impl.ArrayDataValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    ArrayCreationService service = new ArrayCreationService(
        new NumericArrayReader(),
        new ArrayDataValidatorImpl(),
        new NumericArrayParser()
    );

    NumericArrayWrapper<BigInteger> doubleArrayWrapper = service.createFromFile(FILE_PATH, BigInteger.class, BigInteger::new);

    System.out.println(doubleArrayWrapper.toString());

    SortService sortService = new SortServiceImpl();
    sortService.sort(doubleArrayWrapper, SortStrategy.bubble());

    System.out.println(doubleArrayWrapper);



  }

}