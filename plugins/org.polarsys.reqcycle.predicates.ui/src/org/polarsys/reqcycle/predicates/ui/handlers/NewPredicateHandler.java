/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.polarsys.reqcycle.predicates.ui.util.PredicatesUIHelper;

public class NewPredicateHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public NewPredicateHandler() {
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		PredicatesUIHelper.openNewPredicateEditor();
		return null;
	}
}
