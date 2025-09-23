package io.github.magwas.tooling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sourceforge.pmd.lang.ast.RootNode;
import net.sourceforge.pmd.util.treeexport.XmlTreeRenderer;

@Service
public class GetASTofSourceTreeService {
	@Autowired
	WalkTreeService walkTree;

	@Autowired
	GenerateAstService generateAst;

	public StringBuilder apply(final Path rootPath) throws IOException {
		List<SourceFileNode> children = Stream.of(rootPath.toFile())
				.mapMulti(walkTree::apply)
				.filter(x -> x.getName().endsWith(".java"))
				.map(x -> x.toPath())
				.map(generateAst::apply)
				.map(x -> wrapToSourceFileNode(rootPath, x))
				.collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		new XmlTreeRenderer().renderSubtree(new SourceTreeNode(children), builder);
		return builder;
	}

	private SourceFileNode wrapToSourceFileNode(final Path rootPath, final RootNode x) {
		Path absPath = new File(x.getReportLocation().getFileId().getAbsolutePath()).toPath();
		Path rel = rootPath.relativize(absPath);
		return new SourceFileNode(rel.toString(), x);
	}
}
