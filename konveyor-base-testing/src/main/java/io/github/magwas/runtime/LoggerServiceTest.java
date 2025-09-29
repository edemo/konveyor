package io.github.magwas.runtime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class LoggerServiceTest {

	@Autowired
	LoggerService logger;

	Logger loggerMock;

	@BeforeEach
	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	void setUp() throws NoSuchFieldException, IllegalAccessException {
		loggerMock = mock(Logger.class);
		when(loggerMock.getLevel()).thenReturn(Level.FINEST);
		Field field = LogUtil.class.getDeclaredField("logger");
		field.setAccessible(true);
		field.set(LogUtil.class, loggerMock);
	}

	@Test
	@DisplayName("debug logs to stderr, in the form of 'DEBUG <classname> <methodname> <linenumber>:<message>")
	void test() {
		try (PrintStream errMock = mock(PrintStream.class);
				PrintStream olderr = System.err; ) {
			System.setErr(errMock);
			LogUtil.addDebuggedClass(getClass());
			logger.debug("testlog");
			verify(errMock).println("DEBUG io.github.magwas.runtime.LoggerServiceTest test 48:testlog");
			System.setErr(olderr);
			LogUtil.clearDebuggedClasses();
		}
	}

	@Test
	@DisplayName(
			"warning logs using the logger, using the caller's class and method name, and prepending the message with the line number")
	void test1() {
		logger.warning("testlog");
		verify(loggerMock).logp(Level.WARNING, "io.github.magwas.runtime.LoggerServiceTest", "test1", "59:testlog");
	}

	@Test
	@DisplayName(
			"info logs using the logger, using the caller's class and method name, and prepending the message with the line number")
	void test2() {
		logger.info("testlog");
		verify(loggerMock).logp(Level.INFO, "io.github.magwas.runtime.LoggerServiceTest", "test2", "67:testlog");
	}
}
