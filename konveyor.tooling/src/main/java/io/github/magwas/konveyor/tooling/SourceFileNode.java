package io.github.magwas.konveyor.tooling;

import io.github.magwas.konveyor.annotations.Glue;
import net.sourceforge.pmd.lang.ast.Node;

@Glue
public class SourceFileNode extends AbstractHackNode implements Node {

	Node child;
	String path;

	SourceFileNode(final String path, final Node child) {
		this.path = path;
		this.child = child;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String getXPathNodeName() {
		return "SourceFile";
	}

	@Override
	public Node getChild(final int index) {
		return child;
	}

	@Override
	public int getNumChildren() {
		return 1;
	}
}
