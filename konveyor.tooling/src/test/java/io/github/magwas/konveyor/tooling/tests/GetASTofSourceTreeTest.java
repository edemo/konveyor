package io.github.magwas.konveyor.tooling.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.konveyor.testing.TestBase;
import io.github.magwas.konveyor.tooling.GetASTofSourceTreeService;

class GetASTofSourceTreeTest extends TestBase implements ToolingTestData {

	@InjectMocks
	GetASTofSourceTreeService getASTofSourceTree;

	@Test
	@DisplayName("gets the AST of the source tree")
	void test() throws IOException {
		System.out.println(STUB_FILE);
		assertEquals(STUB_AST, getASTofSourceTree.apply(STUB_FILE.toPath()).toString());
	}
}
