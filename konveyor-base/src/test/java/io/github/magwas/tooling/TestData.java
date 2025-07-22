package io.github.magwas.tooling;

import java.nio.file.Path;
import java.util.List;

import io.github.magwas.testing.TestUtil;

public interface TestData {
	Path RESOURCE_PATH = Path.of(System.getProperty("user.dir"),
			"src/test/resources");
	List<String> RESOURCE_FILES = List.of("test.java", "test.ast",
			"some_directory/some_file.txt");
	Path JAVA_PATH = Path.of(RESOURCE_PATH.toString(), "test.java");
	Path AST_PATH = Path.of(RESOURCE_PATH.toString(), "test.ast");
	String AST = TestUtil.getFileContents(AST_PATH);

}
