package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import io.github.magwas.konveyor.runtime.DebugState;
import io.github.magwas.konveyor.runtime.Dependencies;
import io.github.magwas.konveyor.runtime.LoggerService;

public class LoggerServiceStub {
	public static LoggerService stub() throws NoSuchFieldException, IllegalAccessException {
		LoggerService loggerService = new LoggerService();
		loggerService.debugState = new DebugState();
		Field depsField = LoggerService.class.getDeclaredField("dependencies");
		Dependencies depsMock = mock(Dependencies.class);
		depsMock.syserr = System.err;
		depsMock.logger = mock(Logger.class);
		depsField.setAccessible(true);
		depsField.set(loggerService, depsMock);
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
