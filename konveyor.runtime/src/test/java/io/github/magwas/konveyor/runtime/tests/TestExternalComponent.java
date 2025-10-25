package io.github.magwas.konveyor.runtime.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.konveyor.runtime.LoggerService;

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
