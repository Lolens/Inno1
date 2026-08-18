package io.github.lolens.inno1.validator;

import java.util.stream.Stream;

public interface ArrayDataValidator {

  void validate(String line);

  void validate(Stream<String> stream);

}
