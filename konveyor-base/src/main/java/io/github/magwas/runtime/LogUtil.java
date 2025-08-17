package io.github.magwas.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LogUtil {
	static Set<String> debuggedClasses = new HashSet<>();

	public static void addDebuggedClass(Class<?> debuggedClass) {
		debuggedClasses.add(debuggedClass.getName());
	}

	public static void clearDebuggedClasses() {
		debuggedClasses.removeIf(x -> true);
	}

	public static void debug(Object... args) {
		if (debuggedClasses == null)
			return;
		StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[2];
		String name = stackTraceElement.getClassName();
		if (!debuggedClasses.contains(name))
			return;
		String qualifier = "DEBUG ";
		doLog(stackTraceElement, qualifier, args);
	}

	public static void warning(Object... args) {
		StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[2];
		String qualifier = "WARNING ";
		doLog(stackTraceElement, qualifier, args);
	}

	public static void info(Object... args) {
		StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[2];
		String qualifier = "INFO ";
		doLog(stackTraceElement, qualifier, args);
	}

	private static void doLog(StackTraceElement stackTraceElement,
			String qualifier, Object... args) {
		String name = stackTraceElement.getClassName();
		List<String> params = List.of(args).stream().map(x -> x.toString())
				.collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		builder.append(qualifier);
		builder.append(name);
		builder.append(":");
		builder.append(stackTraceElement.getLineNumber());
		builder.append(" ");
		builder.append(String.join(",", params));
		System.out.println(builder);
	}
}
