open module konveyor.tooling.tests {
	exports io.github.magwas.konveyor.tooling.tests;

	requires transitive konveyor.testing;
	requires transitive konveyor.testing.tests;
	requires transitive konveyor.tooling;
	requires transitive spring.test;
	requires transitive konveyor.runtime;
}
