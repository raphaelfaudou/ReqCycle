/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Papa Issa DIAKHATE (AtoS) papa-issa.diakhate@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.polarsys.reqcycle.predicates.ui.components;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.polarsys.reqcycle.predicates.core.IPredicatesAnnotationSources;
import org.polarsys.reqcycle.predicates.core.api.IEAttrPredicate;
import org.polarsys.reqcycle.predicates.core.api.ITypedPredicate;
import org.polarsys.reqcycle.predicates.core.util.PredicatesUtil;
import org.polarsys.reqcycle.ui.eattrpropseditor.EAttrPropsEditorPlugin;
import org.polarsys.reqcycle.ui.eattrpropseditor.GenericEAttrPropsEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class PredicatePropsEditor extends Composite {

	private final Map<EAttribute, GenericEAttrPropsEditor> editors;

	public PredicatePropsEditor(final ITypedPredicate<?> predicate, final Composite parent, final int style) {

		super(parent, style);
		setLayout(new GridLayout(1, false));

		this.editors = new LinkedHashMap<EAttribute, GenericEAttrPropsEditor>();

		// We must show this component only if the type is supported for the predicate.
		if(!(predicate instanceof IEAttrPredicate))
			return;

		// Now let's show the appropriate properties editor for each EAttribute of the IPredicate to edit.
		// NOTE: Only the attributes annotated with the expected source annotation will be proposed for edition.
		final Collection<EAttribute> inputAttrs = PredicatesUtil.getEAllAttributesAnnotatedBy(predicate.eClass(), IPredicatesAnnotationSources.EANNOTATION_SOURCE_INPUT);

		for(EAttribute attr : inputAttrs) {
			String javaClassType = EAttrPropsEditorPlugin.getEditorType(attr.getEType());
			this.addEditor(attr, javaClassType);
		}
	}

	/**
	 * @return The EAttribute editors used for the edition of this predicate.
	 */
	public Map<EAttribute, GenericEAttrPropsEditor> getEditors() {
		return this.editors;
	}

	public void addEditor(final EAttribute attribute) {
		this.addEditor(attribute, null);
	}

	public void addEditor(final EAttribute attribute, final String javaClassTypeName) {
		final GenericEAttrPropsEditor attributeEditor = new GenericEAttrPropsEditor(this, SWT.NONE);
		attributeEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		attributeEditor.init(attribute, javaClassTypeName);
		this.editors.put(attribute, attributeEditor);
		this.layout();
	}

	public void removeEditor(final EAttribute attribute) {
		if(attribute != null) {
			GenericEAttrPropsEditor editor = this.editors.get(attribute);
			if(editor != null)
				editor.dispose();
			this.editors.remove(attribute);
			this.layout();
		}
	}

}
