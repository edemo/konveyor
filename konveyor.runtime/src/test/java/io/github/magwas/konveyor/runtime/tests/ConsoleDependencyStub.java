package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.mock;

import java.io.PrintStream;

import io.github.magwas.konveyor.runtime.ConsoleDependency;

public class ConsoleDependencyStub {
	public static ConsoleDependency stub() {
		ConsoleDependency mock = mock(ConsoleDependency.class);
		mock.syserr = mock(PrintStream.class);
		mock.sysout = mock(PrintStream.class);
		return mock;
	}
}
