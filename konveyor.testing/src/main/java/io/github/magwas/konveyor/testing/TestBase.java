package io.github.magwas.konveyor.testing;

import static org.mockito.Mockito.spy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.TestInstantiationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBase {

	public static String environmentState;

	@BeforeEach
	public void setUp() throws Throwable {
		environmentState = null;
		Object tested = stubUp(this);
		mockUp(this, tested);
	}

	@AfterEach
	public void tearDown() throws Throwable {}

	public void given(String newState) {
		environmentState = newState;
	}

	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	public static Object stubUp(final Object test) {
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
					return instance;
				}
			}
			throw new TestInstantiationException("no @InjectMocks in test");
		} catch (NoSuchMethodException
				| SecurityException
				| InstantiationException
				| IllegalAccessException
				| InvocationTargetException e) {
			throw new TestInstantiationException("stubUp " + test, e);
		}
	}

	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	public static void stubFill(final Object instance) {
		Class<?> type = instance.getClass();
		for (Field field : type.getDeclaredFields()) {
			if (field.isAnnotationPresent(Autowired.class)) {
				String stubName = field.getType().getName().replaceFirst("(\\.)([^.]+)$", ".tests.$2Stub");
				Class<?> stub;
				Object value;
				try {
					stub = Class.forName(stubName);
					if (null == stub.getAnnotation(IndirectlyTested.class)) {
						Method method = stub.getDeclaredMethod("stub");
						method.setAccessible(true);
						try {
							value = method.invoke(null);
						} catch (NullPointerException e) {
							throw new TestInstantiationException("stub is not static in " + stubName);
						}
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
						| InstantiationException
						| IllegalArgumentException e) {
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

	@SuppressWarnings("PMD.AvoidAccessibilityAlteration")
	public static void mockUp(final Object test, final Object tested) {
		try {
			Field[] dependencies = tested.getClass().getDeclaredFields();
			for (Field objField : test.getClass().getDeclaredFields()) {
				if (objField.isAnnotationPresent(Mock.class)) {
					Class<?> type = objField.getType();
					Field mockField = getFieldByClass(tested, type, dependencies);
					mockField.setAccessible(true);
					Object mock = mockField.get(tested);
					objField.setAccessible(true);
					objField.set(test, mock);
				}
			}
		} catch (SecurityException | IllegalAccessException e) {
			throw new TestInstantiationException("stubUp " + test, e);
		}
	}

	private static Field getFieldByClass(Object tested, Class<?> type, Field[] dependencies) {
		for (Field field : dependencies) {
			if (field.getType().equals(type)) {
				return field;
			}
		}
		throw new IllegalArgumentException("no field of type " + type + " in " + tested);
	}
}
