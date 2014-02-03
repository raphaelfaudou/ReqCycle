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
package org.eclipse.reqcycle.xcos.traceability.types;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.TTypeProvider;


import com.google.common.base.Function;
import com.google.common.collect.Maps;

public class XcosTTypeProvider implements TTypeProvider{

	
	private static TType[] TYPES = new XcosTType[] {
		new XcosTType("COVERS"),
		new XcosTType("IMPLEMENTS"),
		new XcosTType("TRACE")
		 };

	private static Map<String, TType> MAP = Maps.uniqueIndex(
			Arrays.asList(TYPES), new Function<TType, String>() {
				@Override
				public String apply(TType arg0) {
					return (((XcosTType) arg0).getMainLabel());
				}
			});

	public static TType get(String mainLabel) {
		TType result = MAP.get(mainLabel);
		if (result == null) {
			result = MAP.get("TRACE");
		}
		return result;
	}

	@Override
	public Iterable<TType> getTTypes() {
		return Arrays.asList(TYPES);
	}

}
