package org.eclipse.reqcycle.traceability.types.configuration.preferences.validators;

import java.util.regex.Pattern;

import com.google.common.base.Predicate;

public class RegexPredicate implements Predicate<String> {
	private Pattern regex;

	public RegexPredicate(String regex) {
		this.regex = Pattern.compile(regex, Pattern.DOTALL
				| Pattern.CASE_INSENSITIVE);
	}

	@Override
	public boolean apply(String arg0) {
		return regex.matcher(arg0).matches();
	}
}
