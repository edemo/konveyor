package io.github.magwas.konveyor.runtime;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class GeneratorUtil implements RuntimeConstants {

	public static StringBuilder testDataBoilerPlate(final String preamble, final String... extendeds) {
		return generateBoilerPlate(preamble, EXTENDS_CLAUSE_NAME, TEST_DATA_BOILERPLATE_PATTERN, extendeds);
	}

	public static StringBuilder testDataTail(final StringBuilder builder) {
		return builder.append("}\n");
	}

	public static StringBuilder stubBoilerPlate(final String preamble, final String... implementeds) {
		return generateBoilerPlate(preamble, IMPLEMENTS_CLAUSE_NAME, STUB_BOILERPLATE_PATTERN, implementeds);
	}

	public static StringBuilder stubTail(final StringBuilder builder) {
		return builder.append("""
							return mock;
				}
			}
			""");
	}

	public static StringBuilder generateBoilerPlate(
			final String preamble, final String extendType, final String pattern, final String... implementeds) {
		var builder = new StringBuilder(GENERATOR_STRINGBUILDER_INITIAL_CAPACITY);
		String fullClassName = MiscUtil.getStackElementAtHeight(3).getClassName();
		String className = fullClassName
				.replaceFirst(ANYTHING_UP_TO_AND_INCLUDING_LAST_DOT_REGEXP, EMPTY_STRING)
				.replace(GENERATOR_SUFFIX, EMPTY_STRING);
		String packageName = fullClassName.replaceFirst(ANYTHING_FROM_THE_LAST_DOT_REGEXP, EMPTY_STRING);
		String stubbedClassname = className.replaceFirst(STUB_UNIT_NAME_POSTFIX, EMPTY_STRING);
		String extendsClause = EMPTY_STRING;
		if (implementeds.length != 0) extendsClause = extendType + String.join(COMMA_SPACE, implementeds);

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
		return Arrays.stream(input.split(NEWLINE));
	}

	public static Function<String, String> stringConstant(final String postfix) {
		return line -> {
			String[] parts = line.split(COMMA, 2);
			return MessageFormat.format(STRING_CONSTANT_PATTERN, parts[0].trim(), postfix, parts[1]);
		};
	}
}
