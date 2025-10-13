package io.github.magwas.runtime;

import org.springframework.stereotype.Component;

import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
@Component
public class LoggerService {

	public void debug(final Object... args) {
		LogUtil.debug(3, args);
	}

	public void warning(final Object... args) {
		LogUtil.warning(3, args);
	}

	public void info(final Object... args) {
		LogUtil.info(3, args);
	}
}
