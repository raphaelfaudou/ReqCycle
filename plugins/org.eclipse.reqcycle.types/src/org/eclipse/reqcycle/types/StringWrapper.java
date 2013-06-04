package org.eclipse.reqcycle.types;

public class StringWrapper {
	public static <T> T wrap(String s, Class<T> type) {
		if (Integer.class.isAssignableFrom(type)
				|| int.class.isAssignableFrom(type)) {
			return (T) Integer.valueOf(s);
		} else if (Boolean.class.isAssignableFrom(type)
				|| boolean.class.isAssignableFrom(type)) {
			return (T) Boolean.valueOf(s);
		} else if (String.class.isAssignableFrom(type)) {
			return (T) s;
		}
		return null;

	}
}
