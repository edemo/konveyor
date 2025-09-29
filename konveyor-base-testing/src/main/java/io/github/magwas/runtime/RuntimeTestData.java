package io.github.magwas.runtime;

public interface RuntimeTestData {
	String EXTENDED_TESTDATA_BOILERPLATE =
			"""
			package io.github.magwas.runtime;
			import Foo;
			
			public interface UtilTests extends Bar, Baz {
			""";
	String PREAMBLE = """
			import Foo;
			""";
	String TESTDATA_BOILERPLATE =
			"""
			package io.github.magwas.runtime;
			import Foo;
			
			public interface UtilTests {
			""";
	String STUB_BOILERPLATE =
			"""
			package io.github.magwas.runtime;
			import static org.mockito.Mockito.mock;
			import static org.mockito.Mockito.when;
			
			import Foo;
			
			class UtilTests {
			public static UtilTests stub() {
				UtilTests mock = mock(UtilTests.class);
							
			""";
	String EXTENDED_STUB_BOILERPLATE =
			"""
			package io.github.magwas.runtime;
			import static org.mockito.Mockito.mock;
			import static org.mockito.Mockito.when;
			
			import Foo;
			
			class UtilTests implements Bar, Baz {
			public static UtilTests stub() {
				UtilTests mock = mock(UtilTests.class);
							
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
