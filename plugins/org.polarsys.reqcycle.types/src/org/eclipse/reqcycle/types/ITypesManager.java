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
package org.eclipse.reqcycle.types;

import org.eclipse.reqcycle.types.IInjectedTypeChecker.IValueInjecter;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITypesManager {
	Iterable<IType> getAllTypes();

	IType getType(String id);

	Iterable<IType> getAllApplicableTypes(Reachable reachable);

	IType newInjectedType(String id, IType parent, IValueInjecter injecter);

	void addTypeProvider(IInjectedTypeProvider provider);

	void removeTypeProvider(IInjectedTypeProvider provider);
}