package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.verify;

import java.util.logging.Level;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.konveyor.runtime.Config;
import io.github.magwas.konveyor.runtime.DebugState;
import io.github.magwas.konveyor.runtime.Dependencies;
import io.github.magwas.konveyor.runtime.LoggerService;
import io.github.magwas.konveyor.testing.TestBase;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class LoggerServiceTest extends TestBase {

	@InjectMocks
	LoggerService logger;

	@Mock
	private Dependencies dependencies;

	@Mock
	private DebugState debugState;

	@Test
	@DisplayName("debug logs to stderr, in the form of 'DEBUG <classname> <methodname> <linenumber>:<message>")
	void test() {
		debugState.debuggedClasses.add(getClass().getName());
		logger.debug("testlog");
		verify(dependencies.syserr)
				.println("DEBUG io.github.magwas.konveyor.runtime.tests.LoggerServiceTest test 40:testlog");
		debugState.debuggedClasses.clear();
	}

	@Test
	@DisplayName(
			"warning logs using the logger, using the caller's class and method name, and prepending the message with the line number")
	void test1() {
		logger.warning("testlog");
		verify(dependencies.logger)
				.logp(
						Level.WARNING,
						"io.github.magwas.konveyor.runtime.tests.LoggerServiceTest",
						"test1",
						"50:testlog");
	}

	@Test
	@DisplayName(
			"info logs using the logger, using the caller's class and method name, and prepending the message with the line number")
	void test2() {
		logger.info("testlog");
		verify(dependencies.logger)
				.logp(Level.INFO, "io.github.magwas.konveyor.runtime.tests.LoggerServiceTest", "test2", "63:testlog");
	}
}
