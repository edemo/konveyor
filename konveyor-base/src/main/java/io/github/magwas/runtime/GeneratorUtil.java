package io.github.magwas.runtime;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class GeneratorUtil implements RuntimeConstants {

	public static StringBuilder testDataBoilerPlate(final String preamble, final String... extendeds) {
		String extendType = " extends ";
		String pattern = TEST_DATA_BOILERPLATE_PATTERN;
		return generateBoilerPlate(preamble, extendType, pattern, extendeds);
	}

	public static StringBuilder stubBoilerPlate(final String preamble, final String... implementeds) {
		String extendType = " implements ";
		String pattern = STUB_BOILERPLATE_PATTERN;
		return generateBoilerPlate(preamble, extendType, pattern, implementeds);
	}

	public static StringBuilder generateBoilerPlate(
			final String preamble, String extendType, String pattern, final String... implementeds) {
		StringBuilder builder = new StringBuilder(GENERATOR_STRINGBUILDER_INITIAL_CAPACITY);
		String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
		String className = fullClassName
				.replaceFirst(ANYTHING_UP_TO_AND_INCLUDING_LAST_DOT_REGEXP, "")
				.replace(GENERATOR_SUFFIX, "");
		String packageName = fullClassName.replaceFirst(ANYTHING_FROM_THE_LAST_DOT_REGEXP, "");
		String stubbedClassname = className.replaceFirst("Stub", "");
		String extendsClause = "";
		if (implementeds.length != 0) extendsClause = extendType + String.join(", ", implementeds);
		builder.append(
				MessageFormat.format(pattern, packageName, preamble, className, extendsClause, stubbedClassname));
		return builder;
	}

	public static StringBuilder mapToCode(
			final String input, final Function<? super String, String> mapper, final StringBuilder builder) {
		linesOf(input).map(mapper).forEach(builder::append);
		return builder;
	}

	public static StringBuilder mapToCode(
			final Stream<String> input, final Function<? super String, String> mapper, final StringBuilder builder) {
		input.map(mapper).forEach(builder::append);
		return builder;
	}

	public static Stream<String> linesOf(final String input) {
		return Arrays.stream(input.split("\n"));
	}

	public static Function<String, String> stringConstant(final String postfix) {
		return line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format(
					"""
							String {0}_{1} = "{2}";
					""", parts[0].trim(), postfix, parts[1]);
		};
	}
}
