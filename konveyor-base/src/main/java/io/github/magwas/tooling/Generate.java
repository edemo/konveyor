package io.github.magwas.tooling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.function.Supplier;

public class Generate {

	public static void main(String... args) throws Exception {
		String className = args[0];
		String targetName = className.replaceFirst("Generator$", "");
		System.out.println("generating " + targetName);

		Class klass = Class.forName(className);
		Supplier<StringBuilder> instance =
				(Supplier<StringBuilder>) klass.getConstructor().newInstance();
		File file = new File("target/generated-sources/" + targetName.replace(".", "/") + ".java");
		new File(file.getParent()).mkdirs();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.append(instance.get());
		}
	}
}
