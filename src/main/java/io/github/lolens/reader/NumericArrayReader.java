package io.github.lolens.reader;

import io.github.lolens.exception.NumericArrayReaderException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class NumericArrayReader {

  private final Path filePath;

  public NumericArrayReader(Path path) {
    if (!Files.isReadable(path)) {
      throw new NumericArrayReaderException("Specified file does not exist or can't be read for other reasons");
    }
    filePath = path;
  }

  public Stream<String> lines() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));
      // opening stream with try-with-resources instantly closes lazily-populated stream after br.lines();
      return br.lines().onClose(() -> {
        try {
          br.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    } catch (FileNotFoundException e) {
      throw new NumericArrayReaderException("Specified file does not exist", e);
    }
  }

  public String readLine() {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
      return br.readLine();
    } catch (IOException e) {
      throw new NumericArrayReaderException("Encountered IOException while reading file", e);
    }
  }


}
