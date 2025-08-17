package io.github.magwas.runtime;

import java.lang.reflect.Field;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import io.github.magwas.kodekonveyorannotations.Glue;

@Component
@Glue
public class ContextUtils {

	static ApplicationContext applicationContext;
	@Autowired
	ApplicationContext runtimeContext;
	@Autowired
	AutowireCapableBeanFactory autowireCapableBeanFactory;
	@Autowired
	ObjectCache objectCache;

	static ContextUtils instance = null;

	@PostConstruct
	void findOutContext() {
		if (applicationContext == null)
			if (runtimeContext == null)
				createDefaultApplicationContext();
			else
				applicationContext = runtimeContext;
	}

	public Object wireAndCache(Object object) {
		autowireCapableBeanFactory.autowireBean(object);
		return cacheObject(object);
	}

	public Object wire(Object object) {
		autowireCapableBeanFactory.autowireBean(object);
		return object;
	}

	private Object cacheObject(Object object) {
		String objectId = getObjectId(object);
		String fullId = object.getClass().getCanonicalName() + ":" + objectId;
		Map<String, Object> cache = objectCache.cache;
		if (!cache.containsKey(fullId)) {
			cache.put(fullId, object);
		}
		return cache.get(fullId);
	}

	private String getObjectId(Object object) {
		for (Field field : object.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Id.class) != null) {
				field.setAccessible(true);
				try {
					return (String) field.get(object);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new Error(e);
				}
			}
		}
		return null;
	}

	public static ContextUtils getInstance() {
		if (instance == null) {
			if (applicationContext == null)
				createDefaultApplicationContext();
			instance = applicationContext.getBean(ContextUtils.class);
		}
		return instance;
	}

	public <T> T getBean(Class<T> klass) {
		return applicationContext.getBean(klass);
	}

	private static void createDefaultApplicationContext() {
		applicationContext = new AnnotationConfigApplicationContext(Config.class);
	}

	public static void setContext(ApplicationContext context) {
		applicationContext = context;
	}

	public ApplicationContext getContext() {
		return applicationContext;
	}
}