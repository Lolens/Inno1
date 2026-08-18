package io.github.lolens.service.array.impl;

import io.github.lolens.entity.NumericArrayWrapper;
import io.github.lolens.parser.NumericArrayParser;
import io.github.lolens.reader.NumericArrayReader;
import io.github.lolens.validator.ArrayDataValidator;
import io.github.lolens.validator.impl.ArrayDataValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayCreationServiceImplTest {


  private final NumericArrayReader reader = new NumericArrayReader();
  private final ArrayDataValidator validator = new ArrayDataValidatorImpl();
  private final NumericArrayParser parser = new NumericArrayParser();

  private final ArrayCreationServiceImpl arrayCreationService = new ArrayCreationServiceImpl(reader, validator, parser);

  @Test
  void shouldCreateArrayWithValidData(@TempDir Path tempDir) throws IOException {
    Path inputPath = tempDir.resolve("input.txt");
    Files.writeString(inputPath, "1,2,3");

    NumericArrayWrapper<Integer> wrapper = arrayCreationService.createFromFile(inputPath, Integer.class, Integer::parseInt);

    assertArrayEquals(new int[]{1, 2, 3}, wrapper.getIntArray());
  }

}
