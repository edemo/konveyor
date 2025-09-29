package io.github.magwas.tooling;

import java.util.List;

import io.github.magwas.kodekonveyorannotations.Glue;
import net.sourceforge.pmd.lang.ast.AstInfo;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.RootNode;

@Glue
public class SourceTreeNode extends AbstractHackNode implements RootNode {

	List<SourceFileNode> children;

	public SourceTreeNode(final List<SourceFileNode> children2) {
		this.children = children2;
	}

	@Override
	public Node getChild(final int index) {
		return children.get(index);
	}

	@Override
	public int getNumChildren() {
		return children.size();
	}

	@Override
	public String getXPathNodeName() {
		return "SourceTree";
	}

	public void addChild(final SourceFileNode child) {
		children.add(child);
	}

	@Override
	public AstInfo<? extends RootNode> getAstInfo() {
		return null;
	}
}
