package io.github.magwas.konveyor.runtime;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class LoggerDependency {
	public Logger logger;

	public LoggerDependency() {
		try {
			logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		} catch (Exception e) {
			throw new RuntimeException("Error initializing dependencies", e);
		}
	}
}
