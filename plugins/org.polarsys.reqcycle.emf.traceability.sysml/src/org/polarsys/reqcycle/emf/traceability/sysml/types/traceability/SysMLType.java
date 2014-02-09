/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.traceability.sysml.types.traceability;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.reqcycle.emf.traceability.sysml.Activator;
import org.polarsys.reqcycle.traceability.model.TType;

import com.google.common.base.Predicate;

import static com.google.common.collect.Iterables.find;

public class SysMLType extends TType {
	private static final long serialVersionUID = 1L;
	private String packageUri;
	private int eclass;

	public SysMLType(EClass eclass) {
		super(Activator.PLUGIN_ID + "." + eclass.getName(), eclass.getName()
				+ " (SysML)");
		this.eclass = eclass.getClassifierID();
		this.packageUri = eclass.getEPackage().getNsURI();
	}

	public EClass getEClass() {
		EPackage ep = EPackage.Registry.INSTANCE.getEPackage(packageUri);
		return (EClass) find(ep.getEClassifiers(),
				new Predicate<EClassifier>() {
					@Override
					public boolean apply(EClassifier arg0) {
						return SysMLType.this.eclass == arg0.getClassifierID();
					}

				}, null);
	}
}
