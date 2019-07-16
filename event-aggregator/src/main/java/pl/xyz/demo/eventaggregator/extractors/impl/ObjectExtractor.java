package pl.xyz.demo.eventaggregator.extractors.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

public class ObjectExtractor implements VariableExtractor<Map.Entry<String, Object>, Map<String, Object>> {
    private static Logger LOGGER = LoggerFactory.getLogger(ObjectExtractor.class);
    public static final String SERIALIZER_NAME = "serializable";

    @Override
    public Optional<Map.Entry<String, Object>> getValue(Map<String, Object> from) {
        Optional<String> extractedValue = Optional.ofNullable((String) from.get(BYTE_VALUE));
        try {
            return extractedValue.isPresent() ? Optional.of(new AbstractMap.SimpleImmutableEntry<String, Object>((String) from.get(VARIABLE_NAME),
                    (new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(extractedValue.get())))).readObject())) : Optional.empty();
        } catch (IOException e) {
            LOGGER.error("Unable to extract Object", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Unable to extract Object", e);
        }
        return Optional.empty();
    }
}

