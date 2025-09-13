package io.github.magwas.tooling;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.util.function.Consumer;

public class WalkTreeServiceStub implements TestData {
	static WalkTreeService stub() {
		WalkTreeService mock = mock(WalkTreeService.class);
		doAnswer(invocation -> {
			Consumer<File> consumer = invocation.getArgument(1);
			consumer.accept(ANNOTATION_FILE);
			return null;
		}).when(mock).apply(any(), any());

		return mock;
	}
}
