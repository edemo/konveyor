package io.github.magwas.tooling;

import io.github.magwas.kodekonveyorannotations.Glue;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.document.TextRegion;
import net.sourceforge.pmd.util.DataMap;
import net.sourceforge.pmd.util.DataMap.DataKey;

@Glue
@SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
public abstract class AbstractHackNode implements Node {
	@Override
	public TextRegion getTextRegion() {
		return null;
	}

	@Override
	public DataMap<DataKey<?, ?>> getUserMap() {
		return null;
	}

	@Override
	public Node getParent() {
		return null;
	}

	@Override
	public int getIndexInParent() {
		return 0;
	}
}
