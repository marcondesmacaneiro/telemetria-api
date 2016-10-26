package br.edu.unidavi.telemetria.infrastructure.spring;

import br.edu.unidavi.telemetria.domain.exception.InvalidArgumentException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
@Component
class LocalTimeOfString implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        if (!isNullOrEmpty(source)) {
            try {
                return LocalTime.parse(source);
            } catch (DateTimeParseException ex) {
                throw new InvalidArgumentException("Time must be in ISO-8601!");
            }
        }
        return null;
    }
}
