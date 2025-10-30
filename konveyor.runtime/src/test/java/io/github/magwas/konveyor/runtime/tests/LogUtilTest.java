package io.github.magwas.konveyor.runtime.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.magwas.konveyor.runtime.DebugState;
import io.github.magwas.konveyor.runtime.LogUtil;
import io.github.magwas.konveyor.runtime.LoggerDependency;
import io.github.magwas.konveyor.runtime.LoggerService;

class LogUtilTest {

	@Test
	@DisplayName("addDebuggedClass add the class to the list of debugged classes")
	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	void test() throws NoSuchFieldException, IllegalAccessException {
		LogUtil.addDebuggedClass(getClass());
		LoggerService loggerService = LogUtil.getLoggerService();
		Field field = LoggerService.class.getDeclaredField("debugState");
		field.setAccessible(true);
		var debugState = (DebugState) field.get(loggerService);
		assertEquals(Set.of(this.getClass().getName()), debugState.debuggedClasses);
		LogUtil.clearDebuggedClasses();
	}

	@Test
	@DisplayName("addDebuggedClass sets the log level to FINEST")
	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	void test1() throws NoSuchFieldException, IllegalAccessException {
		LoggerDependency mockDependencies = mock(LoggerDependency.class);
		mockDependencies.logger = mock(java.util.logging.Logger.class);
		LoggerService loggerService = LogUtil.getLoggerService();
		Field dependenciesField = LoggerService.class.getDeclaredField("loggerDependency");
		dependenciesField.setAccessible(true);
		dependenciesField.set(loggerService, mockDependencies);
		LogUtil.addDebuggedClass(getClass());
		verify(mockDependencies.logger).setLevel(java.util.logging.Level.FINEST);
		LogUtil.clearDebuggedClasses();
	}
}
