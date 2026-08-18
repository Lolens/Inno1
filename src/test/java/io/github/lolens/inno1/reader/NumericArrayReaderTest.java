package io.github.lolens.inno1.reader;

import io.github.lolens.inno1.exception.NumericArrayReaderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Written by AI
 */
class NumericArrayReaderTest {

  @Nested
  @DisplayName("changeFilePath()")
  class ChangeFilePathTests {

    @Test
    @DisplayName("Should accept a readable existing file")
    void shouldAcceptReadableFile(@TempDir Path tempDir) throws IOException {
      Path file = tempDir.resolve("input.txt");
      Files.writeString(file, "1,2,3");

      NumericArrayReader reader = new NumericArrayReader();

      assertDoesNotThrow(() -> reader.changeFilePath(file));
    }

    @Test
    @DisplayName("Should throw exception for non-existent file")
    void shouldThrowForNonExistentFile(@TempDir Path tempDir) {
      Path missingFile = tempDir.resolve("missing.txt");

      NumericArrayReader reader = new NumericArrayReader();

      assertThrows(NumericArrayReaderException.class,
          () -> reader.changeFilePath(missingFile));
    }
  }

  @Nested
  @DisplayName("lines()")
  class LinesTests {

    @Test
    @DisplayName("Should throw exception when path was never set")
    void shouldThrowWhenPathNotSet() {
      NumericArrayReader reader = new NumericArrayReader();

      assertThrows(NumericArrayReaderException.class, reader::stream);
    }

    @Test
    @DisplayName("Should read all non-blank lines from file")
    void shouldReadAllNonBlankLines(@TempDir Path tempDir) throws IOException {
      Path file = tempDir.resolve("input.txt");
      Files.writeString(file, "1,2,3\n\n4,5,6\n");

      NumericArrayReader reader = new NumericArrayReader();
      reader.changeFilePath(file);

      List<String> lines;
      try (Stream<String> stream = reader.stream()) {
        lines = stream.collect(Collectors.toList());
      }

      assertEquals(List.of("1,2,3", "4,5,6"), lines);
    }

    @Test
    @DisplayName("Should skip blank lines including whitespace-only lines")
    void shouldSkipBlankAndWhitespaceLines(@TempDir Path tempDir) throws IOException {
      Path file = tempDir.resolve("input.txt");
      Files.writeString(file, "1,2\n   \n\n3,4\n");

      NumericArrayReader reader = new NumericArrayReader();
      reader.changeFilePath(file);

      List<String> lines;
      try (Stream<String> stream = reader.stream()) {
        lines = stream.collect(Collectors.toList());
      }

      assertEquals(List.of("1,2", "3,4"), lines);
    }

    @Test
    @DisplayName("Should return empty stream for file with only blank lines")
    void shouldReturnEmptyStreamForBlankFile(@TempDir Path tempDir) throws IOException {
      Path file = tempDir.resolve("input.txt");
      Files.writeString(file, "\n\n   \n");

      NumericArrayReader reader = new NumericArrayReader();
      reader.changeFilePath(file);

      long count;
      try (Stream<String> stream = reader.stream()) {
        count = stream.count();
      }

      assertEquals(0, count);
    }

    @Test
    @DisplayName("Should read file content correctly with UTF-8 characters")
    void shouldReadUtf8Content(@TempDir Path tempDir) throws IOException {
      Path file = tempDir.resolve("input.txt");
      Files.writeString(file, "1,2,3 — тест", StandardCharsets.UTF_8);

      NumericArrayReader reader = new NumericArrayReader();
      reader.changeFilePath(file);

      List<String> lines;
      try (Stream<String> stream = reader.stream()) {
        lines = stream.collect(Collectors.toList());
      }

      assertEquals(List.of("1,2,3 — тест"), lines);
    }
  }
}