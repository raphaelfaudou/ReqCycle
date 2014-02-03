/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
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
