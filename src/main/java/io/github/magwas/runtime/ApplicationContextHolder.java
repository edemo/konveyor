package io.github.magwas.runtime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import io.github.magwas.kodekonveyorannotations.Glue;

@Component
@Glue
public class ApplicationContextHolder {
	private static ApplicationContext _appCtx;

	public static <T> T getBean(Class<T> klass) {
		if (_appCtx == null)
			_appCtx = new AnnotationConfigApplicationContext(Config.class);

		return _appCtx.getBean(klass);
	}
}