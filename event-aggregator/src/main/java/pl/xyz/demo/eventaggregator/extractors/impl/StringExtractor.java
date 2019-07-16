package pl.xyz.demo.eventaggregator.extractors.impl;

import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

public class StringExtractor implements VariableExtractor<Map.Entry<String, String>, Map<String, Object>> {
    public static final String SERIALIZER_NAME = "string";

    @Override
    public Optional<Map.Entry<String, String>> getValue(Map<String, Object> from) {
        return Optional.of(new AbstractMap.SimpleImmutableEntry<String, String>((String) from.get(VARIABLE_NAME),
                (String) from.get(TEXT_VALUE)));
    }
}
