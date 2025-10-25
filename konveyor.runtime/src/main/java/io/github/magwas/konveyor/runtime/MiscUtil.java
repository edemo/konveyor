package io.github.magwas.konveyor.runtime;

public class MiscUtil {
	@SuppressWarnings("PMD.DoNotUseThreads")
	public static StackTraceElement getStackElementAtHeight(final int depth) {
		return Thread.currentThread().getStackTrace()[depth + 1];
	}

	@SuppressWarnings("PMD.SystemPrintln")
	public static void syserr(final String message) {
		System.err.println(message);
	}

	@SuppressWarnings("PMD.SystemPrintln")
	public static void sysout(final String message) {
		System.out.println(message);
	}
}
