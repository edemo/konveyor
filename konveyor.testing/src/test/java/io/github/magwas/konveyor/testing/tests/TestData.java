package io.github.magwas.konveyor.testing.tests;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

import io.github.magwas.konveyor.testing.TestUtil;

public interface TestData {
	String TEST_RESOURCE_CONTENTS = "test resource contents";
	String TEST_RESOURCE_NAME = "testresource";
	String NONEXISTING_RESOURCE_NAME = "nonexisting";
	Path RESOURCE_PATH = Path.of(System.getProperty("user.dir"), "src/test/resources");
	Path ANNOTATION_AST_PATH = Path.of(RESOURCE_PATH.toString(), "IndirectlyTested.ast");
	String ANNOTATION_AST = TestUtil.loadResourceAsString("IndirectlyTested.ast");
	Stream<Integer> SHORT_SET = Stream.of(1, 3);
	Set<Integer> SHORT_SET_ANOTHER_WAY = Set.of(2, 3);
	Set<Integer> TEST_SET = Set.of(1, 2, 3);
	Stream<Integer> TEST_STREAM = Stream.of(1, 3, 2);
	String DIFF_COLLECTIONS_OUT = """
			expected - actual:
			2
			actual - expected:
			1
			""";
}
