package io.github.magwas.runtime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogUtilTest {

	@Test
	@DisplayName("addDebuggedClass add the class to the list of debugged classes")
	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	void test() throws NoSuchFieldException, IllegalAccessException {
		LogUtil.addDebuggedClass(getClass());
		Field field = LogUtil.class.getDeclaredField("debuggedClasses");
		field.setAccessible(true);
		@SuppressWarnings("unchecked")
		Set<String> klasses = (Set<String>) field.get(LogUtil.class);
		assertEquals(Set.of(this.getClass().getName()), klasses);
		LogUtil.clearDebuggedClasses();
	}

	@Test
	@DisplayName("addDebuggedClass sets the log level to FINEST")
	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	void test1() throws NoSuchFieldException, IllegalAccessException {
		Logger mockLogger = mock(Logger.class);
		Field field = LogUtil.class.getDeclaredField("logger");
		field.setAccessible(true);
		field.set(LogUtil.class, mockLogger);
		LogUtil.addDebuggedClass(getClass());
		verify(mockLogger).setLevel(Level.FINEST);
		LogUtil.clearDebuggedClasses();
	}
}
