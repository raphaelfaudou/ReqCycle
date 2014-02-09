/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.ui.collectionspropseditor.internal.components;

public class CharSequenceValue implements ItemValue<CharSequence> {

	private CharSequence value;

	public CharSequenceValue() {
		this(null);
	}

	public CharSequenceValue(CharSequence value) {
		this.value = value;
	}

	@Override
	public CharSequence getValue() {
		return this.value;
	}

	@Override
	public void setValue(CharSequence value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

}
