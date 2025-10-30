package io.github.magwas.konveyor.runtime.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.github.magwas.konveyor.runtime.ConsoleDependency;
import io.github.magwas.konveyor.runtime.LoggerService;
import io.github.magwas.konveyor.testing.TestBase;

class LoggerServiceStubTest extends TestBase {

	@InjectMocks
	TestExternalComponent testComponent;

	@Mock
	public LoggerService logger;

	@Test
	@DisplayName("debug prints to stderr")
	void test() throws IllegalAccessException, NoSuchFieldException {
		Field dependenciesField = LoggerService.class.getDeclaredField("consoleDependency");
		dependenciesField.setAccessible(true);
		var dependencies = (ConsoleDependency) dependenciesField.get(logger);
		assertEquals(System.err, dependencies.syserr);
		dependencies.syserr = mock(PrintStream.class);
		logger.addDebuggedClass(TestExternalComponent.class);
		testComponent.doDebug();
		verify(dependencies.syserr)
				.println("DEBUG io.github.magwas.konveyor.runtime.tests.TestExternalComponent doDebug 18:debug");
		logger.clearDebuggedClasses();
		dependencies.syserr = System.err;
	}

	@Test
	@DisplayName("debug can be verified")
	void test0() {
		testComponent.doDebug();
		verify(testComponent.logger).debug("debug");
	}

	@Test
	@DisplayName("warning can be verified")
	void test1() {
		testComponent.doWarning();
		verify(testComponent.logger).warning("warning");
	}

	@Test
	@DisplayName("info can be verified")
	void test2() {
		testComponent.doInfo();
		verify(testComponent.logger).info("info");
	}
}
