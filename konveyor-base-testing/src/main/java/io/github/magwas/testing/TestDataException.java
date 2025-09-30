package io.github.magwas.testing;

public class TestDataException extends RuntimeException {

	private static final long serialVersionUID = 2607561643472150308L;

	public TestDataException(final Exception e) {
		super(e);
	}
}
