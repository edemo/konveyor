package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.github.magwas.konveyor.runtime.ConsoleDependency;
import io.github.magwas.konveyor.testing.TestBase;

public class MiscUtilTest extends TestBase implements RuntimeTestData {
	@InjectMocks
	TestExternalComponent testExternalComponent;

	@Mock
	ConsoleDependency consoleDependency;

	@DisplayName("use Dependencies.sysout.println() instead MiscUtil.sysout()")
	@Test
	void test() {
		testExternalComponent.doPrint();
		verify(consoleDependency.sysout).println(PREAMBLE);
	}

	@DisplayName("use Dependencies.syserr.println() instead MiscUtil.syserr()")
	@Test
	void test1() {
		testExternalComponent.doPrintStderr();
		verify(consoleDependency.syserr).println(PREAMBLE);
	}
}
