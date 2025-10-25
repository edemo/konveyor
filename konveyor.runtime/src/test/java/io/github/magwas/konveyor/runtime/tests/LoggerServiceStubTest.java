package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.konveyor.runtime.LogUtil;
import io.github.magwas.konveyor.testing.TestBase;

class LoggerServiceStubTest extends TestBase {

	@InjectMocks
	TestExternalComponent testComponent;

	@Test
	@DisplayName("debug prints to stderr")
	void test() {
		try (PrintStream fakeErr = mock(PrintStream.class);
				PrintStream oldErr = System.err; ) {
			System.setErr(fakeErr);
			LogUtil.addDebuggedClass(TestExternalComponent.class);
			testComponent.doDebug();
			verify(fakeErr)
					.println("DEBUG io.github.magwas.konveyor.runtime.tests.TestExternalComponent doDebug 14:debug");
			LogUtil.clearDebuggedClasses();
			System.setErr(oldErr);
		}
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
