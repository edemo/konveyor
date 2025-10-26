package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import io.github.magwas.konveyor.runtime.LogUtil;
import io.github.magwas.konveyor.runtime.LoggerService;

public class LoggerServiceStub {
	static LoggerService stub() {
		LoggerService mock = mock(LoggerService.class);
		doAnswer(call -> {
					LogUtil.debug(10, call.getArguments());
					return null;
				})
				.when(mock)
				.debug(any());
		return mock;
	}
}
