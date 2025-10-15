package io.github.magwas.runtime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

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
