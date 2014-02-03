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
package org.polarsys.reqcycle.types;

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
