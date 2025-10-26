package io.github.magwas.konveyor.tooling.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.konveyor.runtime.Config;
import io.github.magwas.konveyor.testing.tests.TestData;
import io.github.magwas.konveyor.tooling.GetASTofSourceTreeService;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class GetASTofSourceTreeEndToEndTest implements ToolingTestData, TestData {

	@Autowired
	GetASTofSourceTreeService getASTofSourceTree;

	@Test
	@DisplayName("extract AST from a source tree")
	void test2() throws IOException {
		String actual = getASTofSourceTree.apply(RESOURCE_PATH).toString();
		assertEquals(TEST_AST, actual);
	}
}
