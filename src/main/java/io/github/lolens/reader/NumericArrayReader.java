package io.github.lolens.reader;

import io.github.lolens.exception.NumericArrayReaderException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class NumericArrayReader {

  private Path path;

  public NumericArrayReader() {

  }

  public void changeFilePath(Path path) throws FileNotFoundException {
    checkReadPossibility(path);
    this.path = path;
  }

  private void checkPath() {
    if (path == null) {
      throw new NumericArrayReaderException("File path is null");
    }
  }

  private void checkReadPossibility(Path path) {
    if (!Files.isReadable(path)) {
      throw new NumericArrayReaderException("Specified file does not exist or can't be read for other reasons");
    }
  }

  public Stream<String> lines() {
    checkPath();

    try {
      BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
      // opening stream with try-with-resources instantly closes lazily-populated stream after br.lines();
      return br.lines()
          .filter(s -> !s.isBlank()) // drop blank lines
          .onClose(() -> {
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



}
