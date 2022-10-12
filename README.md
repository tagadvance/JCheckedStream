# JCheckedStream

Are you tired of writing code like this?

```java
static void foo() throws IOException {
	try {
		Arrays.asList().stream().map(i -> {
			try {
				throw new IOException(); // e.g.
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		})
	} catch (RuntimeException e) {
		final var cause = e.getCause();
		if (cause instanceof IOException) {
			throw (IOException) cause;
		}

		throw e;
	}
}
```
