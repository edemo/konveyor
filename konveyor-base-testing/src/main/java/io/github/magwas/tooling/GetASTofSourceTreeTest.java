package io.github.magwas.tooling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.testing.TestBase;

public class GetASTofSourceTreeTest extends TestBase implements TestData {

	@InjectMocks
	GetASTofSourceTreeService getASTofSourceTree;

	@Test
	@DisplayName("gets the AST of the source tree")
	void test() throws IOException {
		assertEquals(ANNOTATION_AST,
				getASTofSourceTree.apply(ANNOTATION_AST_PATH).toString());
	}
}
