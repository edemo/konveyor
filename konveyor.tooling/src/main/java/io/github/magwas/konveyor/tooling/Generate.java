package io.github.magwas.konveyor.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

import io.github.magwas.konveyor.annotations.Glue;
import io.github.magwas.konveyor.runtime.MiscUtil;

@Glue
public class Generate {

	public static void main(final String... args)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException,
					IOException, NoSuchMethodException {
		String className = args[0];
		String category = args[1];
		String targetName = className.replaceFirst("Generator$", "");
		MiscUtil.syserr("generating " + targetName);

		Class<Supplier<StringBuilder>> klass = getGeneratorByName(className);
		Supplier<StringBuilder> instance = klass.getConstructor().newInstance();
		File file = new File("target/generated-sources/" + category + "/" + targetName.replace(".", "/") + ".java");
		new File(file.getParent()).mkdirs();
		try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Paths.get(file.toURI())))) {
			writer.append(instance.get());
		}
	}

	@SuppressWarnings("unchecked")
	private static Class<Supplier<StringBuilder>> getGeneratorByName(final String className)
			throws ClassNotFoundException {
		return (Class<Supplier<StringBuilder>>) Class.forName(className);
	}
}
