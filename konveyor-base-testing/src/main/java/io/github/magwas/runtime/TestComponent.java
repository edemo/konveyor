package io.github.magwas.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
	@Autowired
	public LoggerService logger;

	public void testDebug() {
		logger.debug("debug");
	}

	public void testWarning() {
		logger.warning("warning");
	}

	public void testInfo() {
		logger.info("info");
	}
}