package io.github.magwas.konveyor.runtime;

import java.io.PrintStream;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Dependencies {
	public Logger logger;
	public PrintStream syserr;
	public PrintStream sysout;

	@PostConstruct
	void initialize() {
		try {
			logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			syserr = System.err;
			sysout = System.out;
		} catch (Exception e) {
			throw new RuntimeException("Error initializing dependencies", e);
		}
	}
}
