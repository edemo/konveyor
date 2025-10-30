package io.github.magwas.konveyor.runtime;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class DebugState {
	public Set<String> debuggedClasses = new HashSet<>();
}
