package io.github.magwas.konveyor.runtime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.magwas.konveyor.annotations.Glue;

@Glue
public class LogUtil {
	private LogUtil() {}

	private static LoggerService loggerService = null;

	public static LoggerService getLoggerService() {
		if (null == loggerService)
			try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class)) {
				loggerService = context.getBean(LoggerService.class);
			}
		return loggerService;
	}

	public static void addDebuggedClass(final Class<?> debuggedClass) {
		getLoggerService().addDebuggedClass(debuggedClass);
	}

	public static void clearDebuggedClasses() {
		getLoggerService().clearDebuggedClasses();
	}

	public static void debug(final Object... args) {
		getLoggerService().debug(args);
	}

	public static void debug(final int depth, final Object... args) {
		getLoggerService().debug(depth, args);
	}

	public static void warning(final Object... args) {
		getLoggerService().warning(args);
	}

	public static void warning(final int depth, final Object... args) {
		getLoggerService().warning(depth, args);
	}

	public static void info(final Object... args) {
		getLoggerService().info(args);
	}

	public static void info(final int depth, final Object... args) {
		getLoggerService().info(depth, args);
	}
}
