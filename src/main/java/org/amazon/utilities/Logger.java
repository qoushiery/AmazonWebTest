package org.amazon.utilities;

import org.slf4j.LoggerFactory;

public class Logger {
    private final org.slf4j.Logger logger;

    public Logger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void log(String message) {
        logger.info(message);
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
    public void logError(String message) {
        logger.error(message);
    }
}
