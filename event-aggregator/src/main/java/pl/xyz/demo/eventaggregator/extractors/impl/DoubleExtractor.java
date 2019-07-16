package pl.xyz.demo.eventaggregator.extractors.impl;

import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

public class DoubleExtractor implements VariableExtractor<Map.Entry<String, Double>, Map<String, Object>> {
    public static final String SERIALIZER_NAME = "double";

    @Override
    public Optional<Map.Entry<String, Double>> getValue(Map<String, Object> from) {
        Optional<Double> extractedValue = Optional.ofNullable((Double) from.get(DOUBLE_VALUE));
        return extractedValue.map(aDouble -> new AbstractMap.SimpleImmutableEntry<String, Double>((String) from.get(VARIABLE_NAME),
                aDouble));
    }
}
