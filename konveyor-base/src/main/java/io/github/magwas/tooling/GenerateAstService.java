package io.github.magwas.tooling;

import java.io.IOError;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sourceforge.pmd.lang.ast.Parser;
import net.sourceforge.pmd.lang.ast.RootNode;
import net.sourceforge.pmd.lang.ast.SemanticErrorReporter;
import net.sourceforge.pmd.lang.document.TextDocument;
import net.sourceforge.pmd.lang.document.TextFile;
import net.sourceforge.pmd.util.treeexport.XmlTreeRenderer;

@Service
public class GenerateAstService {
	@Autowired
	PmdParsingTools pmdParsingTools;

	public void apply(final Path path, final Appendable appendable) throws IOException {
		RootNode root = apply(path);
		new XmlTreeRenderer().renderSubtree(root, appendable);
	}

	public RootNode apply(final Path path) {
		TextFile textfile = TextFile.forPath(path, Charset.defaultCharset(), pmdParsingTools.java.getDefaultVersion());
		TextDocument textDocument;
		try {
			textDocument = TextDocument.create(textfile);
		} catch (IOException e) {
			throw new IOError(e);
		}
		Parser.ParserTask task = new Parser.ParserTask(textDocument, SemanticErrorReporter.noop(), pmdParsingTools.lpr);
		return pmdParsingTools.parser.parse(task);
	}
}
