open module konveyor.tooling {
	exports io.github.magwas.konveyor.tooling;

	requires transitive spring.context;
	requires transitive spring.beans;
	requires transitive spring.core;
	requires transitive pmd.core;
	requires transitive org.apache.commons.lang3;
	requires transitive java.logging;
	requires transitive konveyor.runtime;
	requires pmd.java;
}
