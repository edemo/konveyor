package io.github.magwas.konveyor.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LogUtil {
	private static Set<String> debuggedClasses = new HashSet<>();

	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void addDebuggedClass(final Class<?> debuggedClass) {
		debuggedClasses.add(debuggedClass.getName());
		logger.setLevel(Level.FINEST);
	}

	public static void clearDebuggedClasses() {
		debuggedClasses.removeIf(x -> true);
	}

	public static void debug(final Object... args) {
		debug(3, args);
	}

	public static void debug(final int depth, final Object... args) {
		if (debuggedClasses == null) return;
		StackTraceElement stackTraceElement = MiscUtil.getStackElementAtHeight(depth);
		String name = stackTraceElement.getClassName();
		if (!debuggedClasses.contains(name)) return;
		List<String> params = Stream.of(args).map(Object::toString).toList();
		String method = stackTraceElement.getMethodName();
		String string = "DEBUG "
				+ name
				+ ' '
				+ method
				+ ' '
				+ stackTraceElement.getLineNumber()
				+ ':'
				+ String.join(",", params);
		MiscUtil.syserr(string);
	}

	public static void warning(final Object... args) {
		warning(3, args);
	}

	public static void warning(final int depth, final Object... args) {
		StackTraceElement stackTraceElement = MiscUtil.getStackElementAtHeight(depth);
		doLog(stackTraceElement, Level.WARNING, args);
	}

	public static void info(final Object... args) {
		info(3, args);
	}

	public static void info(final int depth, final Object... args) {
		StackTraceElement stackTraceElement = MiscUtil.getStackElementAtHeight(depth);
		doLog(stackTraceElement, Level.INFO, args);
	}

	private static void doLog(final StackTraceElement stackTraceElement, final Level level, final Object... args) {
		String name = stackTraceElement.getClassName();
		List<String> params = Stream.of(args).map(Object::toString).toList();
		String method = stackTraceElement.getMethodName();
		String string = stackTraceElement.getLineNumber() + ":" + String.join(",", params);
		logger.logp(level, name, method, string);
	}
}
