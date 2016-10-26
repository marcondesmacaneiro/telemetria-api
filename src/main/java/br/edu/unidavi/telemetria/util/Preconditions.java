package br.edu.unidavi.telemetria.util;

import br.edu.unidavi.telemetria.domain.exception.EntityNotFoundException;
import br.edu.unidavi.telemetria.domain.exception.InvalidArgumentException;
import br.edu.unidavi.telemetria.domain.exception.NullArgumentException;

import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 10/10/16.
 */
public interface Preconditions {

    static <T> T checkNotNull(T value, String message) {
        if (Objects.isNull(value)) {
            throw new NullArgumentException(message);
        }
        return value;
    }

    static <T> T checkEntityNotFound(T value, String message) {
        if (Objects.isNull(value)) {
            throw new EntityNotFoundException(message);
        }
        return value;
    }

    static void checkArgument(boolean valid, String message) {
        if (!valid) {
            throw new InvalidArgumentException(message);
        }
    }
}
