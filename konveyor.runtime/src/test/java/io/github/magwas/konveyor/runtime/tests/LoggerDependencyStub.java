package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.mock;

import java.util.logging.Logger;

import io.github.magwas.konveyor.runtime.LoggerDependency;

public class LoggerDependencyStub {
	public static LoggerDependency stub() {
		LoggerDependency mock = mock(LoggerDependency.class);
		mock.logger = mock(Logger.class);
		return mock;
	}
}
