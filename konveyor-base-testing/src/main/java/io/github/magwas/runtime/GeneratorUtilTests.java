package io.github.magwas.runtime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneratorUtilTests implements RuntimeTestData {

	@Test
	@DisplayName(
			"""
			testDataBoilerPlate generates boilerplate for TestData units
			- the class name is the calling class name without 'Generator'
			- the package is the package of the calling class
			- the preamble (probably containing import statements) is inserted before the interface declaration
			- the extended interfaces added in an 'extends' clause
			""")
	void test() {
		assertEquals(
				EXTENDED_TESTDATA_BOILERPLATE,
				GeneratorUtil.testDataBoilerPlate(PREAMBLE, EXTENDED_1, EXTENDED_2)
						.toString());
	}

	@Test
	@DisplayName("If no 'extends' parameter, no 'extended clause")
	void test1() {
		assertEquals(
				TESTDATA_BOILERPLATE,
				GeneratorUtil.testDataBoilerPlate(PREAMBLE).toString());
	}

	@Test
	@DisplayName(
			"""
			stubBoilerPlate generates boilerplate for Stub units
			- the class name is the calling class name without 'Generator'
			- the package is the package of the calling class
			- mockito static imports are  added after the package declaration
			- the preamble (probably containing import statements) is inserted before the class declaration
			- the extended interfaces added in an 'implements' clause
			- defines the stub function
			- defines the mock variable
			""")
	void test2() {
		assertEquals(
				EXTENDED_STUB_BOILERPLATE,
				GeneratorUtil.stubBoilerPlate(PREAMBLE, EXTENDED_1, EXTENDED_2).toString());
	}

	@Test
	@DisplayName("If no 'extends' parameter, no 'extended clause")
	void test3() {
		assertEquals(STUB_BOILERPLATE, GeneratorUtil.stubBoilerPlate(PREAMBLE).toString());
	}

	@Test
	@DisplayName("linesOf creates a stream of lines from the input")
	void test4() {
		assertEquals(
				List.of(EXTENDED_1, EXTENDED_2), GeneratorUtil.linesOf(LINES).toList());
	}

	@Test
	@DisplayName("linesOf tolerates trailing newline")
	void test5() {
		assertEquals(
				List.of(EXTENDED_1, EXTENDED_2),
				GeneratorUtil.linesOf(LINES_WITH_TRAILING_NEWLINE).toList());
	}

	@Test
	@DisplayName(
			"""
			stringConstant returns a mapper function which
				generates an assignment of string constant
				using the postfix for the variable name
				between the name and string value of the input
				which is separated by ','
			""")
	void test6() {
		assertEquals(
				GENERATED_STRINGCONSTANT, GeneratorUtil.stringConstant(POSTFIX).apply(NAME_VALUE_PAIR));
	}

	@Test
	@DisplayName(
			"""
			mapToCode maps the String input for each line
			obtained by splitting the input to lines
			using the mapper function
			and appends them to the builder
			""")
	void test7() {
		assertEquals(
				GENERATED_CODE,
				GeneratorUtil.mapToCode(GENERATOR_INPUT, GeneratorUtil.stringConstant(POSTFIX), new StringBuilder())
						.toString());
	}

	@Test
	@DisplayName(
			"""
			mapToCode maps the Stream<String> input for each line
			using the mapper function
			and appends them to the builder
			""")
	void test8() {
		assertEquals(
				GENERATED_CODE,
				GeneratorUtil.mapToCode(
								GeneratorUtil.linesOf(GENERATOR_INPUT),
								GeneratorUtil.stringConstant(POSTFIX),
								new StringBuilder())
						.toString());
	}
}
