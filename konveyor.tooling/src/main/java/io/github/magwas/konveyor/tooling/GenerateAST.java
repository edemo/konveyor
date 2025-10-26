package io.github.magwas.konveyor.tooling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.magwas.konveyor.annotations.Glue;
import io.github.magwas.konveyor.runtime.Config;
import io.github.magwas.konveyor.runtime.MiscUtil;

@Glue
public class GenerateAST {

	public static void main(final String[] args) throws IOException {
		Class<GetASTofSourceTreeService> requiredType = GetASTofSourceTreeService.class;
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class); ) {
			GetASTofSourceTreeService getASTofSourceTree = context.getBean(requiredType);
			String path = null;
			if (args.length == 1) path = args[0];
			else {
				MiscUtil.syserr("give me a path name");
				System.exit(1);
			}
			Path root = new File(new File(path).getAbsolutePath()).toPath();
			StringBuilder ret = getASTofSourceTree.apply(root);
			MiscUtil.sysout(ret.toString());
		}
	}
}
