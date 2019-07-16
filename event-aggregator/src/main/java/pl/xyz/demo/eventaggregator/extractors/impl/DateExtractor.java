package pl.xyz.demo.eventaggregator.extractors.impl;

import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class DateExtractor implements VariableExtractor<Map.Entry<String, Date>, Map<String, Object>> {
    public static final String SERIALIZER_NAME = "date";

    @Override
    public Optional<Map.Entry<String, Date>> getValue(Map<String, Object> from) {
        Optional<Long> extractedValue = Optional.ofNullable((Long) from.get(LONG_VALUE));
        return extractedValue.map(aLong -> new AbstractMap.SimpleImmutableEntry<String, Date>((String) from.get(VARIABLE_NAME),
                new Date(aLong)));
    }
}
