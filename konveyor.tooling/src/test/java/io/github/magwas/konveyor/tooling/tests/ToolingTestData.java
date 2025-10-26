package io.github.magwas.konveyor.tooling.tests;

import java.io.File;
import java.nio.file.Path;

import io.github.magwas.konveyor.testing.TestUtil;

public interface ToolingTestData {
	File STUB_FILE = Path.of(
					System.getProperty("user.dir"),
					"src/test/java/io/github/magwas/konveyor/tooling/tests/GenerateAstServiceStub.java")
			.toFile();
	String TEST_AST = TestUtil.loadResourceAsString("test.ast");
	String STUB_AST = TestUtil.loadResourceAsString("GenerateAstServiceStub.ast");
}
