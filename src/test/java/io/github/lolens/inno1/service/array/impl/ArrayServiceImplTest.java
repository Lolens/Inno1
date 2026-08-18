package io.github.lolens.inno1.service.array.impl;

import io.github.lolens.inno1.entity.NumericArrayWrapper;
import io.github.lolens.inno1.parser.NumericArrayParser;
import io.github.lolens.inno1.reader.NumericArrayReader;
import io.github.lolens.inno1.repository.arraywrapper.Repository;
import io.github.lolens.inno1.repository.arraywrapper.impl.ArrayWrapperRepositoryImpl;
import io.github.lolens.inno1.validator.ArrayDataValidator;
import io.github.lolens.inno1.validator.impl.ArrayDataValidatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayServiceImplTest {


  private final Repository<Long, NumericArrayWrapper<?>> repository = new ArrayWrapperRepositoryImpl();
  private final NumericArrayReader reader = new NumericArrayReader();
  private final ArrayDataValidator validator = new ArrayDataValidatorImpl();
  private final NumericArrayParser parser = new NumericArrayParser();

  private final ArrayServiceImpl arrayCreationService = new ArrayServiceImpl(repository, reader, validator, parser);

  @Test
  void shouldCreateArrayWithValidData(@TempDir Path tempDir) throws IOException {
    Path inputPath = tempDir.resolve("input.txt");
    Files.writeString(inputPath, "1,2,3");

    NumericArrayWrapper<Integer> wrapper = arrayCreationService.createFromFile(inputPath, Integer.class, Integer::parseInt);

    assertArrayEquals(new int[]{1, 2, 3}, wrapper.getIntArray());
  }

}
