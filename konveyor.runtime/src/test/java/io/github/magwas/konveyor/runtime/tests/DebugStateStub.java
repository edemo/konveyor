package io.github.magwas.konveyor.runtime.tests;

import static org.mockito.Mockito.mock;

import java.util.HashSet;

import io.github.magwas.konveyor.runtime.DebugState;

public class DebugStateStub {
	public static DebugState stub() {
		DebugState mock = mock(DebugState.class);
		mock.debuggedClasses = new HashSet<>();
		return mock;
	}
}
