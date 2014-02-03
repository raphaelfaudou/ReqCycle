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
package org.polarsys.reqcycle.types.ui.providers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.polarsys.reqcycle.types.IType;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class IterableOfTypesContentProvider implements ITreeContentProvider {

	Set<IType> roots = new HashSet<IType>();
	Multimap<IType, IType> tree = HashMultimap.create();

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		tree.clear();
		roots.clear();
		if (newInput instanceof Iterable) {
			Iterable it = (Iterable) newInput;
			Iterable<IType> types = Iterables.filter(it, IType.class);
			Set<IType> aSet = Sets.newHashSet(types);
			for (IType t : types) {
				IType aSuperType = getASuperType(aSet, t);
				if (aSuperType != null) {
					tree.put(aSuperType, t);
				} else {
					roots.add(t);
				}
			}
		}

	}

	private IType getASuperType(Set<IType> aSet, IType t) {
		IType subType = t.getSuperType();
		while (subType != null) {
			if (aSet.contains(subType)) {
				return subType;
			}
			subType = subType.getSuperType();
		}
		return null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (roots.isEmpty()) {
			inputChanged(null, null, inputElement);
		}
		return roots.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IType) {
			IType type = (IType) parentElement;
			Collection<IType> collection = tree.get(type);
			return collection != null ? collection.toArray() : new Object[] {};
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return children != null && children.length > 0;
	}

}
