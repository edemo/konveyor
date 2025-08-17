package io.github.magwas.runtime;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ObjectCache {
	Map<String, Object> cache = new HashMap<>();
}