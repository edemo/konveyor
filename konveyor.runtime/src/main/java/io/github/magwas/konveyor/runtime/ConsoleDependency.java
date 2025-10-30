package io.github.magwas.konveyor.runtime;

import java.io.PrintStream;

import org.springframework.stereotype.Component;

@Component
public class ConsoleDependency {
	public PrintStream syserr;
	public PrintStream sysout;

	public ConsoleDependency() {
		try {
			syserr = System.err;
			sysout = System.out;
		} catch (Exception e) {
			throw new RuntimeException("Error initializing dependencies", e);
		}
	}
}
