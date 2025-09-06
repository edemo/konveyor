package io.github.magwas.runtime;

import static org.mockito.Mockito.mock;

public class LoggerServiceStub {
	static LoggerService stub() {
		LoggerService mock = mock(LoggerService.class);
		return mock;
	}
}
