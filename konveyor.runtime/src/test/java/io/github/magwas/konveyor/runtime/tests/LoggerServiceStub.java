package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import io.github.magwas.konveyor.runtime.ConsoleDependency;
import io.github.magwas.konveyor.runtime.DebugState;
import io.github.magwas.konveyor.runtime.LoggerDependency;
import io.github.magwas.konveyor.runtime.LoggerService;

public class LoggerServiceStub {
	public static LoggerService stub() throws NoSuchFieldException, IllegalAccessException {
		var loggerService = new LoggerService();
		loggerService.debugState = new DebugState();
		Field loggerDepField = LoggerService.class.getDeclaredField("loggerDependency");
		LoggerDependency loggerDepMock = mock(LoggerDependency.class);
		loggerDepMock.logger = mock(Logger.class);
		loggerDepField.setAccessible(true);
		loggerDepField.set(loggerService, loggerDepMock);
		Field consoleDepField = LoggerService.class.getDeclaredField("consoleDependency");
		ConsoleDependency consoleDepMock = mock(ConsoleDependency.class);
		consoleDepMock.syserr = System.err;
		consoleDepField.setAccessible(true);
		consoleDepField.set(loggerService, consoleDepMock);
		LoggerService spied = spy(loggerService);
		doAnswer(invocation -> {
					loggerService.debug(10, invocation.getArguments());
					return null;
				})
				.when(spied)
				.debug(any());
		return spied;
	}
}
