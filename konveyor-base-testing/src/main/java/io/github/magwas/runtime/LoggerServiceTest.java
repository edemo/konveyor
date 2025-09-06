package io.github.magwas.runtime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintStream;
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
public class LoggerServiceTest {

	@Autowired
	LoggerService logger;

	Logger loggerMock;

	@BeforeEach
	private void setUp() {
		loggerMock = mock(Logger.class);
		when(loggerMock.getLevel()).thenReturn(Level.FINEST);
		LogUtil.logger = loggerMock;
	}

	@Test
	@DisplayName("debug correctly logs")
	void test() {
		PrintStream errMock = mock(PrintStream.class);
		PrintStream olderr = System.err;
		System.setErr(errMock);
		LogUtil.addDebuggedClass(getClass());
		logger.debug("testlog");
		verify(errMock).println(
				"DEBUG io.github.magwas.runtime.LoggerServiceTest test 44:testlog");
		System.setErr(olderr);
	}

	@Test
	@DisplayName("warning correctly logs")
	void test1() {
		logger.warning("testlog");
		verify(loggerMock).logp(Level.WARNING,
				"io.github.magwas.runtime.LoggerServiceTest", "test1", "53:testlog");
	}

	@Test
	@DisplayName("info correctly logs")
	void test2() {
		logger.info("testlog");
		verify(loggerMock).logp(Level.INFO,
				"io.github.magwas.runtime.LoggerServiceTest", "test2", "61:testlog");
	}

}
