package pl.xyz.demo.eventaggregator.extractors;

import pl.xyz.demo.eventaggregator.extractors.impl.BooleanExtractor;
import pl.xyz.demo.eventaggregator.extractors.impl.DateExtractor;
import pl.xyz.demo.eventaggregator.extractors.impl.DoubleExtractor;
import pl.xyz.demo.eventaggregator.extractors.impl.NumberExtractor;
import pl.xyz.demo.eventaggregator.extractors.impl.ObjectExtractor;
import pl.xyz.demo.eventaggregator.extractors.impl.SpinJsonExtractor;
import pl.xyz.demo.eventaggregator.extractors.impl.StringExtractor;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class EventVariableExtractorUtil {
    public static final String SERIALIZER_NAME = "serializerName";
    private static final Map<String, VariableExtractor> VARIABLE_EXTRACTOR_MAP =
            Map.ofEntries(
                    entry(StringExtractor.SERIALIZER_NAME, new StringExtractor()),
                    entry(BooleanExtractor.SERIALIZER_NAME, new BooleanExtractor()),
                    entry("short", new NumberExtractor<Short>()),
                    entry("integer", new NumberExtractor<Integer>()),
                    entry("long", new NumberExtractor<Long>()),
                    entry(DoubleExtractor.SERIALIZER_NAME, new DoubleExtractor()),
                    entry(DateExtractor.SERIALIZER_NAME, new DateExtractor()),
                    entry(ObjectExtractor.SERIALIZER_NAME, new ObjectExtractor()),
                    entry(SpinJsonExtractor.SERIALIZER_NAME, new SpinJsonExtractor()));

    public static Optional<Map.Entry<String, Object>> extractValue(Map<String, Object> from) {
        Optional extractedValue = Optional.ofNullable(VARIABLE_EXTRACTOR_MAP.get(from.get(SERIALIZER_NAME)))
                .map(value -> value.getValue(from));
        return extractedValue.isPresent() ? (Optional<Map.Entry<String, Object>>) extractedValue.get() : Optional.empty();
    }

}
