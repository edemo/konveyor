package io.github.magwas.tooling;

import java.io.File;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

@Service
public class WalkTreeService {
	public void apply(File root, Consumer<File> consumer) {
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) apply(file, consumer);
			else consumer.accept(file);
		}
	}
}
