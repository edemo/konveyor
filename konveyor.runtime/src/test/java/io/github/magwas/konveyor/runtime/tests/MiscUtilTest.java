package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.github.magwas.konveyor.runtime.Dependencies;
import io.github.magwas.konveyor.testing.TestBase;

public class MiscUtilTest extends TestBase implements RuntimeTestData {
	@InjectMocks
	TestExternalComponent testExternalComponent;

	@Mock
	Dependencies dependencies;

	@DisplayName("use Dependencies.sysout.println() instead MiscUtil.sysout()")
	@Test
	void test() throws Throwable {
		testExternalComponent.doPrint();
		verify(dependencies.sysout).println(PREAMBLE);
	}

	@DisplayName("use Dependencies.syserr.println() instead MiscUtil.syserr()")
	@Test
	void test1() throws Throwable {
		testExternalComponent.doPrintStderr();
		verify(dependencies.syserr).println(PREAMBLE);
	}
}
