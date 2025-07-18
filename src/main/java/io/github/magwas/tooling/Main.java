package io.github.magwas.tooling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.magwas.kodekonveyorannotations.Glue;
import io.github.magwas.runtime.Config;

@Glue
public class Main {

	public static void main(String[] args) throws IOException {
		Class<GetASTofSourceTree> requiredType = GetASTofSourceTree.class;
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);
		GetASTofSourceTree getASTofSourceTree = context.getBean(requiredType);
		String path = null;
		if (args.length == 1)
			path = args[0];
		else {
			System.out.println("give me a path name");
			System.exit(1);
		}
		Path root = new File(new File(path).getAbsolutePath()).toPath();
		StringBuilder ret = getASTofSourceTree.apply(root);
		System.out.print(ret);
		context.close();

	}
}
