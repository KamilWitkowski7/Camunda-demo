package pl.xyz.demo.eventaggregator.extractors.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.xyz.demo.eventaggregator.extractors.VariableExtractor;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

public class SpinJsonExtractor implements VariableExtractor<Map.Entry<String, Object>, Map<String, Object>> {
    private static Logger LOGGER = LoggerFactory.getLogger(SpinJsonExtractor.class);
    public static final String SERIALIZER_NAME = "spin://application/json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<Map.Entry<String, Object>> getValue(Map<String, Object> from) {
        Optional<Map.Entry<String, Object>> optional = Optional.empty();
        try {
            Map productInfo = mapper.readValue(Base64.getDecoder().decode((String) from.get(BYTE_VALUE)), Map.class);
            optional = Optional.of(new AbstractMap.SimpleImmutableEntry<String, Object>((String) from.get(VARIABLE_NAME), productInfo));
        } catch (IOException e) {
            LOGGER.error("Unable to deserialize object", e);
        }
        return optional;
    }
}
