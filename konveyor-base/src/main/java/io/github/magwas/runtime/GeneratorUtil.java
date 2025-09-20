package io.github.magwas.runtime;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class GeneratorUtil {

	public static void testDataBoilerPlate(StringBuilder builder, String preamble,
			String... extendeds) {
		String fullClassName = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		String klassName = fullClassName.replaceFirst(".*\\.", "")
				.replace("Generator", "");
		String packageName = fullClassName.replaceFirst("\\.[^.]*$", "");
		builder.append("package ");
		builder.append(packageName);
		builder.append(";\n");
		builder.append(preamble);
		builder.append("\n");
		builder.append("public interface ");
		builder.append(klassName);
		if (extendeds.length != 0) {
			builder.append(" extends ");
			builder.append(String.join(", ", extendeds));
		}
		builder.append(" {\n");
	}

	public static void mapToCode(String input,
			Function<? super String, ? extends String> mapper,
			StringBuilder builder) {
		linesOf(input).map(mapper).forEach(builder::append);
	}

	public static void mapToCode(Stream<String> input,
			Function<? super String, ? extends String> mapper,
			StringBuilder builder) {
		input.map(mapper).forEach(builder::append);
	}

	public static Stream<String> linesOf(String input) {
		return Arrays.asList(input.split("\n")).stream();
	}

	public static Function<String, String> stringConstant(String postfix) {
		return (String line) -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							String {0}_{1} = "{2}";
					""", parts[0].trim(), postfix, parts[1]);
		};
	}

}
