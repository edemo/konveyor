package io.github.magwas.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public class Generate {

	public static void main(final String... args)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException,
					IOException, NoSuchMethodException {
		String className = args[0];
		String targetName = className.replaceFirst("Generator$", "");
		System.out.println("generating " + targetName);

		Class<Supplier<StringBuilder>> klass = (Class<Supplier<StringBuilder>>) Class.forName(className);
		Supplier<StringBuilder> instance = klass.getConstructor().newInstance();
		File file = new File("target/generated-sources/" + targetName.replace(".", "/") + ".java");
		new File(file.getParent()).mkdirs();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.append(instance.get());
		}
	}
}
