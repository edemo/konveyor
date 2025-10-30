package io.github.magwas.konveyor.runtime.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.magwas.konveyor.runtime.Dependencies;
import io.github.magwas.konveyor.runtime.LoggerService;

@Component
public class TestExternalComponent implements RuntimeTestData {
	@Autowired
	LoggerService logger;

	@Autowired
	Dependencies dependencies;

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
		dependencies.sysout.println(PREAMBLE);
	}

	public void doPrintStderr() {
		dependencies.syserr.println(PREAMBLE);
	}
}
