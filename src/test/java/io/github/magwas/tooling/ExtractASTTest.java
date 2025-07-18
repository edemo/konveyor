package io.github.magwas.tooling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class ExtractASTTest implements TestData {

	@Autowired
	WalkTree walkTree;
	@Autowired
	GetASTofSourceTree getASTofSourceTree;

	@Test
	@DisplayName("extract AST from a source tree")
	void test2() throws IOException {
		Path rootPath = RESOURCE_PATH;
		StringBuilder builder = getASTofSourceTree.apply(rootPath);
		String actual = builder.toString();
		assertEquals(AST, actual);
	}

}