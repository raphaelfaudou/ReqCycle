/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.traceability.types.ExtensionPointReader;
import org.polarsys.reqcycle.traceability.types.ITraceTypesManager;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;
import org.polarsys.reqcycle.types.ITypesManager;

import com.google.common.collect.Ordering;

@Singleton
public class TraceTypesManager implements ITraceTypesManager {

	@Inject
	ITypesManager manager;

	Map<String, TType> map = new ExtensionPointReader().readTTypes();
	Map<String, RegisteredAttribute> mapAtt = new ExtensionPointReader()
			.readAttributes();

	@Override
	public Iterable<TType> getAllTTypes() {
		return Ordering.usingToString().immutableSortedCopy(map.values());
	}

	@Override
	public TType getTType(String id) {
		return map.get(id);
	}

	@Override
	public RegisteredAttribute getAttribute(String id) {
		return mapAtt.get(id);
	}

	@Override
	public Iterable<RegisteredAttribute> getAllAttributes() {
		return Ordering.usingToString().immutableSortedCopy(mapAtt.values());
	}

}
