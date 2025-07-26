package io.github.magwas.runtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {
	@Autowired
	ApplicationContext context;
	@Id
	String id;

	TestComponent() {

	}

	TestComponent(String id) {
		this.id = id;
	}
}