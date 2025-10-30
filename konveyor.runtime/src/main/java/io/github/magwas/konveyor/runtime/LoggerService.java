package io.github.magwas.konveyor.runtime;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

	@Autowired
	public DebugState debugState;

	@Autowired
	LoggerDependency loggerDependency;

	@Autowired
	ConsoleDependency consoleDependency;

	public void addDebuggedClass(final Class<?> debuggedClass) {
		debugState.debuggedClasses.add(debuggedClass.getName());
		loggerDependency.logger.setLevel(Level.FINEST);
	}

	public void clearDebuggedClasses() {
		debugState.debuggedClasses.clear();
	}

	public void debug(final Object... args) {
		debug(3, args);
	}

	public void debug(final int depth, final Object... args) {
		if (debugState.debuggedClasses.isEmpty()) return;
		StackTraceElement stackTraceElement = MiscUtil.getStackElementAtHeight(depth);
		String name = stackTraceElement.getClassName();
		if (!debugState.debuggedClasses.contains(name)) return;
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
		consoleDependency.syserr.println(string);
	}

	public void warning(final Object... args) {
		warning(3, args);
	}

	public void warning(final int depth, final Object... args) {
		StackTraceElement stackTraceElement = MiscUtil.getStackElementAtHeight(depth);
		doLog(stackTraceElement, Level.WARNING, args);
	}

	public void info(final Object... args) {
		info(3, args);
	}

	public void info(final int depth, final Object... args) {
		StackTraceElement stackTraceElement = MiscUtil.getStackElementAtHeight(depth);
		doLog(stackTraceElement, Level.INFO, args);
	}

	private void doLog(final StackTraceElement stackTraceElement, final Level level, final Object... args) {
		String name = stackTraceElement.getClassName();
		List<String> params = Stream.of(args).map(Object::toString).toList();
		String method = stackTraceElement.getMethodName();
		String string = stackTraceElement.getLineNumber() + ":" + String.join(",", params);
		loggerDependency.logger.logp(level, name, method, string);
	}
}
