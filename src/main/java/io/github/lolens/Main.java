package io.github.lolens;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.reader.NumericArrayReader;
import io.github.lolens.service.sortstrategy.SortService;
import io.github.lolens.service.sortstrategy.SortStrategy;
import io.github.lolens.service.sortstrategy.impl.SortServiceImpl;
import io.github.lolens.service.sortstrategy.impl.BubbleSortStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    NumericArrayWrapper<Integer> arrayWrapper =
        NumericArrayWrapper.of(new Integer[]{1, 58, 2, 77, 332, 111, 353, 4693, 11, 44, 55, 677, 9, 0, 12422, 2292});

    SortServiceImpl<Integer> service = new SortServiceImpl<>(new BubbleSortStrategy<Integer>());

    service.sort(arrayWrapper, SortStrategy.bubble());

    NumericArrayReader arrayParser = new NumericArrayReader(FILE_PATH);
    arrayParser.lines().forEach(System.out::println);


  }

}