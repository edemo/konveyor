package io.github.magwas.tooling;

import io.github.magwas.kodekonveyorannotations.Glue;
import net.sourceforge.pmd.lang.ast.Node;

@Glue
public class SourceFileNode extends AbstractHackNode implements Node {

	Node child;
	String path;

	SourceFileNode(String path, Node child) {
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
	public Node getChild(int index) {
		return child;
	}

	@Override
	public int getNumChildren() {
		return 1;
	}
}
