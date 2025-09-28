package io.github.magwas.runtime;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class GeneratorUtil implements RuntimeConstants {

	public static void testDataBoilerPlate(
			final StringBuilder builder, final String preamble, final String... extendeds) {
		String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		String klassName = fullClassName.replaceFirst(".*\\.", "").replace("Generator", "");
		String packageName = fullClassName.replaceFirst("\\.[^.]*$", "");
		builder.append("package ")
				.append(packageName)
				.append(";\n")
				.append(preamble)
				.append("\npublic interface ")
				.append(klassName);
		if (extendeds.length != 0) {
			builder.append(" extends ").append(String.join(", ", extendeds));
		}
		builder.append(" {\n");
	}

	public static void mapToCode(
			final String input, final Function<? super String, String> mapper, final StringBuilder builder) {
		linesOf(input).map(mapper).forEach(builder::append);
	}

	public static void mapToCode(
			final Stream<String> input, final Function<? super String, String> mapper, final StringBuilder builder) {
		input.map(mapper).forEach(builder::append);
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

	public static StringBuilder stubBoilerPlate(final String preamble, final String... implementeds) {
		StringBuilder builder = new StringBuilder(STUB_STRINGBUILDER_INITIAL_CAPACITY);
		String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		String klassName = fullClassName.replaceFirst(".*\\.", "").replace("Generator", "");
		String origClassname = klassName.replaceFirst("Stub", "");
		String packageName = fullClassName.replaceFirst("\\.[^.]*$", "");
		builder.append("package ")
				.append(packageName)
				.append(
						"""
				;
				import static org.mockito.Mockito.mock;
				import static org.mockito.Mockito.when;

				""")
				.append(preamble)
				.append("\nclass ")
				.append(klassName);
		if (implementeds.length != 0) {
			builder.append(" implements ").append(String.join(", ", implementeds));
		}
		builder.append(" {\n")
				.append(MessageFormat.format(
						"""
				public static {0} stub() '{'
					{0} mock = mock({0}.class);

				""", origClassname));
		return builder;
	}

	public static StringBuilder stubTail(StringBuilder builder) {
		builder.append("""
						return mock;
					}
				}
				""");
		return builder;
	}
}
