/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.traceability.table.utils;

import java.io.Serializable;

import org.eclipse.reqcycle.uri.model.Reachable;


public class SerializableLink implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Reachable r0;
	protected Reachable r1;
	
	public SerializableLink(){
	}
	
	public SerializableLink(Reachable r0, Reachable r1){
		this.r0 = r0;
		this.r1 = r1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SerializableLink){
			SerializableLink other = (SerializableLink) obj;
			return this.r0.equals(other.r0) && this.r1.equals(other.r1);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return r0.hashCode();
	}
	
}
