package io.github.magwas.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtil {
	public static <T> void diffCollections(Set<T> expected, Set<T> actual) {
		Set<T> remaining = new HashSet<T>();
		remaining.addAll(expected);
		remaining.removeAll(actual);
		System.out.println("expected - actual:");
		remaining.forEach(System.out::println);

		Set<T> actualCopy = new HashSet<T>();
		actualCopy.addAll(actual);
		actualCopy.removeAll(expected);
		System.out.println("actual - expected:");
		actualCopy.forEach(System.out::println);
	}

	public static String getFileContents(Path path) {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			return null;
		}
	}

	public static void assertStreamEquals(Set<?> expected, Stream<?> actual) {
		assertEquals(expected, actual.collect(Collectors.toSet()));
	}
}
