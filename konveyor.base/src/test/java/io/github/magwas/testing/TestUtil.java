package io.github.magwas.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.magwas.runtime.MiscUtil;

public class TestUtil {
	public static <T> void diffCollections(final Set<T> expected, final Set<T> actual) {
		Set<T> remaining = new HashSet<>();
		remaining.addAll(expected);
		remaining.removeAll(actual);
		MiscUtil.sysout("expected - actual:");
		remaining.forEach(System.out::println);

		Set<T> actualCopy = new HashSet<>();
		actualCopy.addAll(actual);
		actualCopy.removeAll(expected);
		MiscUtil.sysout("actual - expected:");
		actualCopy.forEach(System.out::println);
	}

	public static String getFileContents(final Path path) {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			throw new TestDataException(e);
		}
	}

	public static void assertStreamEquals(final Set<?> expected, final Stream<?> actual) {
		assertEquals(expected, actual.collect(Collectors.toSet()));
	}

	@SuppressWarnings("PMD.ExceptionAsFlowControl")
	public static String loadResourceAsString(final String definitionName) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = classloader.getResourceAsStream(definitionName)) {
			if (null == inputStream) throw new TestDataException("could not load " + definitionName);
			return new String(inputStream.readAllBytes());
		} catch (IOException e) {
			throw new TestDataException(e);
		}
	}
}
