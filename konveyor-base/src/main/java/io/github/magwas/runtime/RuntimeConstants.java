package io.github.magwas.runtime;

public interface RuntimeConstants {
	int GENERATOR_STRINGBUILDER_INITIAL_CAPACITY = 500;
	String ANYTHING_UP_TO_AND_INCLUDING_LAST_DOT_REGEXP = ".*\\.";
	String ANYTHING_FROM_THE_LAST_DOT_REGEXP = "\\.[^.]*$";
	String GENERATOR_SUFFIX = "Generator";
	String STUB_POSTAMBLE = """
			return mock;
		}
	}
	""";
	String TEST_DATA_BOILERPLATE_PATTERN = """
			package {0};
			{1}
			public interface {2}{3} '{'
			""";
	String STUB_BOILERPLATE_PATTERN =
			"""
			package {0};
			import static org.mockito.Mockito.mock;
			import static org.mockito.Mockito.when;
			
			{1}
			class {2}{3} '{'
			public static {4} stub() '{'
				{4} mock = mock({4}.class);
							
			""";
}
