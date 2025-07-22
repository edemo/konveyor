package io.github.magwas.testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TestUtil {
	public static <T> void diffCollections(Set<T> expected, Set<T> actual) {
		Set<T> remaining = new HashSet<T>();
		remaining.addAll(expected);
		remaining.removeAll(actual);
		System.out.println("expected - actual:");
		remaining.forEach(x -> System.out.println(x));

		Set<T> actualCopy = new HashSet<T>();
		actualCopy.addAll(actual);
		actualCopy.removeAll(expected);
		System.out.println("actual - expected:");
		actualCopy.forEach(x -> System.out.println(x));
	}

	public static String getFileContents(Path path) {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			return null;
		}
	}

}
