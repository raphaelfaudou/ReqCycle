/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.types.configuration.predicates;

import static com.google.common.collect.Iterables.filter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.polarsys.reqcycle.predicates.core.PredicatesFactory;
import org.polarsys.reqcycle.predicates.core.api.IPredicate;
import org.polarsys.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.polarsys.reqcycle.predicates.ui.util.PredicatesUIHelper;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

import com.google.common.collect.Lists;

public class PredicateEditorForTraceabilityHandler extends AbstractHandler {

	IPredicatesConfManager confManager = ZigguratInject
			.make(IPredicatesConfManager.class);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IPredicate predicate = PredicatesFactory.eINSTANCE.createOrPredicate();
		EPackage epackage = EPackage.Registry.INSTANCE
				.getEPackage(ReqCycleDynamicPackage.URI);
		PredicatesUIHelper.openEditor(Lists.newArrayList(filter(
				epackage.getEClassifiers(), EClass.class)), predicate);
		return null;
	}
}
