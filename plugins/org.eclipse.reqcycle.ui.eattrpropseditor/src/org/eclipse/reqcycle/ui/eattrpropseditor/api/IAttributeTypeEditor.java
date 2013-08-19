package org.eclipse.reqcycle.ui.eattrpropseditor.api;

import org.eclipse.emf.ecore.EAttribute;

public interface IAttributeTypeEditor<T> extends ITypeEditor<T> {
	void setEAttribute(final EAttribute attribute);
}
