package pl.xyz.demo.eventaggregator.extractors;

import java.util.Optional;

public interface VariableExtractor<T, F> {
    String VARIABLE_NAME = "variableName";
    String LONG_VALUE = "longValue";
    String BYTE_VALUE = "byteValue";
    String DOUBLE_VALUE = "doubleValue";
    String TEXT_VALUE = "textValue";

    Optional<T> getValue(F from);
}
