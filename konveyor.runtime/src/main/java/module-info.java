open module konveyor.runtime {
	exports io.github.magwas.konveyor.annotations;
	exports io.github.magwas.konveyor.runtime;

	requires transitive spring.context;
	requires transitive spring.beans;
	requires transitive java.logging;
}
