package br.edu.unidavi.telemetria.infrastructure.spring;

import br.edu.unidavi.telemetria.domain.exception.InvalidArgumentException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Created by marcondesmacaneiro on 12/07/16.
 */
@Component
class LocalDateOfString implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {
        if (!isNullOrEmpty(source)) {
            try {
                return LocalDate.parse(source);
            } catch (DateTimeParseException ex) {
                throw new InvalidArgumentException("Date must be in ISO-8601!");
            }
        }
        return null;
    }
}
