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

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.polarsys.reqcycle.uri.model.Reachable;

public interface IType {

	public String getId();

	public String getLabel();

	public ImageDescriptor getIcon();

	public IType getSuperType();

	public boolean is(Reachable r);

	public boolean isExtensible();

	public Class<? extends ITypeChecker> getCheckerClass();
	
	/**
	 * Available only if isExtensible = true
	 * 
	 * @return
	 */
	public List<IType.FieldDescriptor> getDescriptors();

	public class FieldDescriptor {
		public String name;
		public Class<?> type;

		public FieldDescriptor(String name, Class<?> type) {
			this.name = name;
			this.type = type;
		}
	}
	
	public class FieldURIDescriptor extends FieldDescriptor {
		public String name;
		public Class<?> type;
		public Class<?> realType;

		public FieldURIDescriptor(String name, Class<?> type, Class<?> realType) {
			super(name, type);
			this.realType = realType;
		}
	}

}
