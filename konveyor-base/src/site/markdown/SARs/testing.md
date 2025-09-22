Both test coverage and depth are ensured by requiring 100% mutation test coverage on Service units, and that business logic should be only in Service units
This also makes sure that the implementation is minimal: no undocumented things happen in code (as the documentation is generated from test descriptions and end-to-ed test runs in the UI, which both in turn must be consistent with the behaviours and processes modeled.

The challenge is to organize test data nicely, and have a structured approach for test data and stubs supporting everything between London and Detroit, with as few redundancy as possible.

The @IndirectlyTested annotaton on stubs results in a spy which wraps the real service with mocks injected. This allows londoning the service, while the unit tests of the consumer can use verify. This is already implemented. The rest is TODO.

TestData ans Stub units can either be written in the usual way or generated. Generator units are java classes with an apply(StringBuilder builder) method, and probably some public final static String constants containing test data tables.

The format convention of test data tables is comma separated values, where the values mostly related to names of testdata constants or simply constant values to be used.

The framework will run an apply method, and uses the builder to obtain the source code of the testdata/stub. The result will be put under target/generated-sources/java in the same package as the generator, and the class name will be the same without the 'Generator'.
So e.g. io.github.magwas.inez.storage.repository.SumtiRepositoryStubGenerator.java will generate io.github.magwas.inez.storage.repository.SumtiRepository.java.
The full generated code is the responsibility of the apply method, but the konveyor-base-testing (in the future konveyor.base.testing) package will provide helper methods to generate boilerplate and do often occurring tasks, including the case when a stub just answers what the real method would answer to a set of test data inputs.

The content of the exported constants is also the responsibility of the generator.

This way test data needs to be defined once, and changes are automatically followed up in the testdata and stub units using it or some modification/aggregation of it, while the more complex or unique cases can still be written by hand. The choice whether a unit is indirectly or directly tested (or even both) is given to the developer.

This approach adds no test-time overhead for providing test data consistency and non-redundancy, which is an important consideration in unit testing.

