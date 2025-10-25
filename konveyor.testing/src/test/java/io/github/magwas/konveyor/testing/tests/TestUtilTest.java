package io.github.magwas.konveyor.testing.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import io.github.magwas.konveyor.testing.TestDataException;
import io.github.magwas.konveyor.testing.TestUtil;

class TestUtilTest implements TestData {

	@Test
	@DisplayName("loadResuorceAsString loads the resource as a string")
	void test() {
		assertEquals(TEST_RESOURCE_CONTENTS, TestUtil.loadResourceAsString(TEST_RESOURCE_NAME));
	}

	@Test
	@DisplayName("loadResuorceAsString throws TestDataException if the resource does not exist")
	void test2() {
		assertThrows(TestDataException.class, () -> TestUtil.loadResourceAsString(NONEXISTING_RESOURCE_NAME));
	}

	@Test
	@DisplayName("getFileContents loads a file as a string")
	void test1() {
		assertEquals(ANNOTATION_AST, TestUtil.getFileContents(ANNOTATION_AST_PATH));
	}

	@Test
	@DisplayName("getFileContents throws TestDataException for nonexisting file")
	void test3() {
		assertThrows(TestDataException.class, () -> TestUtil.getFileContents(Path.of(NONEXISTING_RESOURCE_NAME)));
	}

	@Test
	@DisplayName("assertStreamEquals throws nothing when the streams content equals the expected set")
	void test4() {
		TestUtil.assertStreamEquals(TEST_SET, TEST_STREAM);
	}

	@Test
	@DisplayName("assertStreamEquals throws AssertionFailedError when the streams does not equal the expected set")
	void test5() {
		assertThrows(AssertionFailedError.class, () -> TestUtil.assertStreamEquals(Set.of(1, 2, 3), SHORT_SET));
	}

	@Test
	@DisplayName("diffCollections prints the expected and actual collection contents to stdout")
	void test6() {
		PrintStream originalOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();

		try {
			System.setOut(new PrintStream(outContent, true)); // auto-flush
			TestUtil.diffCollections(SHORT_SET_ANOTHER_WAY, Set.of(1, 3));
			assertEquals(DIFF_COLLECTIONS_OUT, outContent.toString());
		} finally {
			System.setOut(originalOut);
		}
	}
}
