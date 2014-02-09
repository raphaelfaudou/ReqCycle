/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.uri.impl.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.polarsys.reqcycle.uri.exceptions.VisitableException;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.uri.visitors.EmptyVisitable;
import org.polarsys.reqcycle.uri.visitors.IVisitable;

public class StandardReachableObject implements ReachableObject {

	private Object object;

	public StandardReachableObject(Object object) {
		this.object = object;
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (ILabelProvider.class.equals(adapter)) {
			return new LabelProvider() {

				@Override
				public String getText(Object element) {
					if (element instanceof Reachable) {
						Reachable reach = (Reachable) element;
						return reach.getSchemeSpecificPart();
					}
					return super.getText(element);
				}

			};
		}
		if (IResource.class.equals(adapter) || IFile.class.equals(adapter)) {
			Reachable reachable = this.getReachable(this.object);
			String path = reachable.getPath();
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		}
		return null;
	}

	@Override
	public IVisitable getVisitable() throws VisitableException {
		return new EmptyVisitable();
	}

	@Override
	public String getRevisionIdentification() {
		return null;
	}

	@Override
	public Reachable getReachable(Object o) {
		if(object instanceof Reachable) {
			return (Reachable)object;
		} else {
			return StandardUtils.getReachable(o);
		}
	}

}
