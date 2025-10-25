package io.github.magwas.konveyor.tooling.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.function.Consumer;

import io.github.magwas.konveyor.tooling.WalkTreeService;

public class WalkTreeServiceStub implements ToolingTestData {
	static WalkTreeService stub() {
		WalkTreeService mock = mock(WalkTreeService.class);
		doAnswer(invocation -> {
					Consumer<File> consumer = invocation.getArgument(1);
					consumer.accept(STUB_FILE);
					return null;
				})
				.when(mock)
				.apply(any(), any());

		return mock;
	}
}
