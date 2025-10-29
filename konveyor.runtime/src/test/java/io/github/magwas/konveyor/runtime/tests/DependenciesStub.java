package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.mock;

import java.io.PrintStream;
import java.util.logging.Logger;

import io.github.magwas.konveyor.runtime.Dependencies;

public class DependenciesStub {
	public static Dependencies stub() {
		Dependencies mock = mock(Dependencies.class);
		mock.logger = mock(Logger.class);
		mock.syserr = mock(PrintStream.class);
		return mock;
	}
}
