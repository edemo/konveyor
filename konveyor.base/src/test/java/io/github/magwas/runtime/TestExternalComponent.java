package io.github.magwas.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestExternalComponent {
	@Autowired
	public LoggerService logger;

	public void doDebug() {
		logger.debug("debug");
	}

	public void doWarning() {
		logger.warning("warning");
	}

	public void doInfo() {
		logger.info("info");
	}
}
