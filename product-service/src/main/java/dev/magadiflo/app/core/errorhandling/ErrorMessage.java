package dev.magadiflo.app.core.errorhandling;

import java.time.LocalDateTime;

public record ErrorMessage(LocalDateTime timestamp,
                           int status,
                           String message) {
}
