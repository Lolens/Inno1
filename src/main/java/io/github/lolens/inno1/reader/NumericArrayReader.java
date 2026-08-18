package io.github.lolens.inno1.reader;

import io.github.lolens.inno1.exception.NumericArrayReaderException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumericArrayReader {

  private Path path;

  public NumericArrayReader() {

  }

  public void changeFilePath(Path path) {
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

  public List<String> strings() {
    checkPath();

    try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
      return br.lines()
          .filter(s -> !s.isBlank())
          .collect(Collectors.toList());
    } catch (FileNotFoundException e) {
      throw new NumericArrayReaderException("Specified file does not exist", e);
    } catch (IOException e) {
      throw new NumericArrayReaderException("Failed to read file", e);
    }
  }

  /**
   * Caller should close the stream after terminal operation
   * @return lazily populated Stream of Strings
   */
  public Stream<String> stream() {
    checkPath();

    try {
      BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
      // opening stream with try-with-resources instantly closes lazily-populated stream after br.lines();
      return br.lines()
          .filter(s -> !s.isBlank()) // drop blank lines
          // .onClose will only fire when stream is closed via try-with-resources or .close()
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
