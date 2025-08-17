package io.github.magwas.runtime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class ContextUtilTest {

	@Autowired
	ApplicationContext context;
	@Autowired
	ContextUtils contextUtils;

	@Test
	@DisplayName("setContext can be used to set the context\n"
			+ "probably you want to just use its default context in a standalone application,\n"
			+ "or @Autowire it to some of your Services which initializes before you use ContextUtil\n"
			+ "this method is for the case none of the above is viable")
	void test() {
		assertEquals(context, contextUtils.getContext());
		ContextUtils.setContext(context);
		assertEquals(ContextUtils.applicationContext, context);
	}

	@Test
	@DisplayName("wire can be used to wire in an object created with `new`\n"
			+ "probably you want to use it in a factory")
	void test1() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		TestComponent testComponent = new TestComponent();
		assertEquals(null, testComponent.context);
		contextUtils.wire(testComponent);
		assertEquals(contextUtils.getContext(), testComponent.context);

	}

}
