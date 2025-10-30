package io.github.magwas.konveyor.runtime.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.konveyor.runtime.ConsoleDependency;
import io.github.magwas.konveyor.runtime.LoggerService;

@Component
public class TestExternalComponent implements RuntimeTestData {
	@Autowired
	LoggerService logger;

	@Autowired
	ConsoleDependency consoleDependency;

	public void doDebug() {
		logger.debug("debug");
	}

	public void doWarning() {
		logger.warning("warning");
	}

	public void doInfo() {
		logger.info("info");
	}

	public void doPrint() {
		consoleDependency.sysout.println(PREAMBLE);
	}

	public void doPrintStderr() {
		consoleDependency.syserr.println(PREAMBLE);
	}
}
