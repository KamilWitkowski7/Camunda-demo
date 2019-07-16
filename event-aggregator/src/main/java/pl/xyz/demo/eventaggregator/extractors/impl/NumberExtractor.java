package pl.xyz.demo.eventaggregator.extractors.impl;

import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

public class NumberExtractor<T> implements VariableExtractor<Map.Entry<String, T>, Map<String, Object>> {
    @Override
    public Optional<Map.Entry<String, T>> getValue(Map<String, Object> from) {
        Optional<T> extractedValue = Optional.ofNullable((T) from.get(LONG_VALUE));
        return extractedValue.map(t -> new AbstractMap.SimpleImmutableEntry<String, T>((String) from.get(VARIABLE_NAME),
                (T) t));
    }

}
