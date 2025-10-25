package io.github.magwas.konveyor.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtil {
	public static <T> void diffCollections(final Set<T> expected, final Set<T> actual) {
		Set<T> remaining = new HashSet<>(expected);
		remaining.removeAll(actual);
		System.out.println("expected - actual:");
		remaining.forEach(System.out::println);

		Set<T> actualCopy = new HashSet<>(actual);
		actualCopy.removeAll(expected);
		System.out.println("actual - expected:");
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

	@SuppressWarnings("unchecked")
	public static <T> T dependency(Object instance, Class<T> klass) throws IllegalAccessException {
		System.out.println("dependency " + instance + ", " + klass);
		for (Field field : instance.getClass().getDeclaredFields()) {
			System.out.println("field:" + field + " type=" + field.getType());
			if (field.getType().equals(klass)) {
				field.setAccessible(true);
				return (T) field.get(instance);
			}
		}
		throw new IllegalAccessException("could not find " + klass + " in " + instance);
	}
}
