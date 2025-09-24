package io.github.magwas.runtime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogUtilTest {

	@Test
	@DisplayName("addDebuggedClass add the class to the list of debugged classes")
	void test() {
		LogUtil.addDebuggedClass(getClass());
		assertEquals(Set.of(this.getClass().getName()), LogUtil.debuggedClasses);
	}

	@Test
	@DisplayName("addDebuggedClass sets the log level to FINEST")
	void test1() {
		Logger mockLogger = mock(Logger.class);
		LogUtil.logger = mockLogger;
		LogUtil.addDebuggedClass(getClass());
		verify(mockLogger).setLevel(Level.FINEST);
	}
}
