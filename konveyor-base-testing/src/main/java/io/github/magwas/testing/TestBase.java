package io.github.magwas.testing;

import static org.mockito.Mockito.spy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.TestInstantiationException;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.magwas.kodekonveyorannotations.Glue;

@Glue
public class TestBase {
	@BeforeEach
	public void setUp() throws Throwable {
		stubUp(this);
	}

	@AfterEach
	public void tearDown() throws Throwable {}

	public static void stubUp(final Object test) {
		try {
			for (Field objField : test.getClass().getDeclaredFields()) {
				if (objField.isAnnotationPresent(InjectMocks.class)) {
					Class<?> type = objField.getType();
					Constructor<?> constructor = type.getDeclaredConstructor();
					constructor.setAccessible(true);
					Object instance = constructor.newInstance();
					objField.setAccessible(true);
					objField.set(test, instance);
					stubFill(instance);
				}
			}
		} catch (NoSuchMethodException
				| SecurityException
				| InstantiationException
				| IllegalAccessException
				| InvocationTargetException
				| NullPointerException e) {
			e.printStackTrace();
			throw new TestInstantiationException("stubUp " + test, e);
		}
	}

	public static void stubFill(final Object instance) {
		Class<?> type = instance.getClass();
		for (Field field : type.getDeclaredFields()) {
			if (field.isAnnotationPresent(Autowired.class)) {
				String stubName = field.getType().getName() + "Stub";
				Class<?> stub;
				Object value;
				try {
					stub = Class.forName(stubName);
					if (null == stub.getAnnotation(IndirectlyTested.class)) {
						Method method = stub.getDeclaredMethod("stub");
						method.setAccessible(true);
						value = method.invoke(null);
					} else {
						value = field.getType().getConstructor().newInstance();
						stubFill(value);
						value = spy(value);
					}
				} catch (ClassNotFoundException
						| NoSuchMethodException
						| SecurityException
						| IllegalAccessException
						| InvocationTargetException
						| NullPointerException
						| InstantiationException
						| IllegalArgumentException e) {
					e.printStackTrace();
					throw new TestInstantiationException("problem with stub " + stubName, e);
				}
				field.setAccessible(true);
				try {
					field.set(instance, value);
				} catch (IllegalAccessException e) {
					throw new TestInstantiationException("stubFill", e);
				}
			}
		}
	}
}
