package io.github.magwas.runtime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.testing.TestBase;

public class LoggerServiceStubTest extends TestBase {

	@InjectMocks
	TestComponent testComponent;

	@Test
	@DisplayName("debug prints to stderr")
	void test() {
		PrintStream fakeErr = mock(PrintStream.class);
		PrintStream oldErr = System.err;
		System.setErr(fakeErr);
		LogUtil.addDebuggedClass(TestComponent.class);
		testComponent.testDebug();
		verify(fakeErr).println("DEBUG io.github.magwas.runtime.TestComponent testDebug 12:debug");
		LogUtil.clearDebuggedClasses();
		System.setErr(oldErr);
	}

	@Test
	@DisplayName("debug can be verified")
	void test0() {
		testComponent.testDebug();
		verify(testComponent.logger).debug("debug");
	}

	@Test
	@DisplayName("warning can be verified")
	void test1() {
		testComponent.testWarning();
		verify(testComponent.logger).warning("warning");
	}

	@Test
	@DisplayName("info can be verified")
	void test2() {
		testComponent.testInfo();
		verify(testComponent.logger).info("info");
	}
}
