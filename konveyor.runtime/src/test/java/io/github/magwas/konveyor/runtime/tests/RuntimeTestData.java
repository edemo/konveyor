package io.github.magwas.konveyor.runtime.tests;

public interface RuntimeTestData {
	String EXTENDED_TESTDATA_BOILERPLATE =
			"""
			package io.github.magwas.konveyor.runtime.tests;
			import Foo;
			
			public interface UtilTest extends Bar, Baz {
			""";
	String PREAMBLE = """
			import Foo;
			""";
	String TESTDATA_BOILERPLATE =
			"""
			package io.github.magwas.konveyor.runtime.tests;
			import Foo;
			
			public interface UtilTest {
			""";
	String STUB_BOILERPLATE =
			"""
			package io.github.magwas.konveyor.runtime.tests;
			import static org.mockito.Mockito.mock;
			import static org.mockito.Mockito.when;
			
			import Foo;
			
			class UtilTest {
			public static UtilTest stub() {
				UtilTest mock = mock(UtilTest.class);

			""";
	String EXTENDED_STUB_BOILERPLATE =
			"""
			package io.github.magwas.konveyor.runtime.tests;
			import static org.mockito.Mockito.mock;
			import static org.mockito.Mockito.when;
			
			import Foo;
			
			class UtilTest implements Bar, Baz {
			public static UtilTest stub() {
				UtilTest mock = mock(UtilTest.class);

			""";
	String EXTENDED_1 = "Bar";
	String EXTENDED_2 = "Baz";
	String LINES = EXTENDED_1 + "\n" + EXTENDED_2;
	String LINES_WITH_TRAILING_NEWLINE = LINES + "\n";
	String GENERATED_STRINGCONSTANT = """
			String NAME_POSTFIX = "value";
	""";
	String POSTFIX = "POSTFIX";
	String NAME_VALUE_PAIR = "NAME,value";
	String GENERATED_CODE = """
			String FIRST_POSTFIX = "first";
			String SECOND_POSTFIX = "second";
	""";
	String GENERATOR_INPUT = """
	FIRST,first
	SECOND,second
	""";
}
