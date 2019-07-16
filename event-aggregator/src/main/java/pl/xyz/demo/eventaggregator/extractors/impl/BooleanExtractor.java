package pl.xyz.demo.eventaggregator.extractors.impl;

import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

public class BooleanExtractor implements VariableExtractor<Map.Entry<String, Boolean>, Map<String, Object>> {
    public static final String SERIALIZER_NAME = "boolean";

    @Override
    public Optional<Map.Entry<String, Boolean>> getValue(Map<String, Object> from) {
        Optional<Integer> extractedValue = Optional.ofNullable((Integer) from.get(LONG_VALUE));
        return extractedValue.map(integer -> new AbstractMap.SimpleImmutableEntry<String, Boolean>((String) from.get(VARIABLE_NAME),
                integer != 0));
    }
}