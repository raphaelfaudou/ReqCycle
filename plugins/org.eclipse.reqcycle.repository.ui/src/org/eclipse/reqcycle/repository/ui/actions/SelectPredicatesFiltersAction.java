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
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.ui.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.ui.util.PredicatesUIHelper;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class SelectPredicatesFiltersAction extends Action {

	protected Collection<IPredicate> predicates;

	protected Set<Listener> listeners = new HashSet<Listener>();

	public SelectPredicatesFiltersAction() {
	}

	public void setInitialSelection(Collection<IPredicate> predicates) {
		this.predicates = predicates;
	}

	@Override
	public void run() {

		Collection<IPredicate> selection = PredicatesUIHelper.openPredicatesChooser(predicates);
		if(selection != null) {
			PredicatesChangeEvent event = new PredicatesChangeEvent(selection);
			notifyListeners(event);
		}
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	protected void notifyListeners(Event event) {
		for(Listener listener : listeners) {
			listener.handleEvent(event);
		}
	}

	public class PredicatesChangeEvent extends Event {

		public PredicatesChangeEvent(Collection<IPredicate> predicates) {
			this.predicates = predicates;
		}

		protected Collection<IPredicate> predicates;

		public Collection<IPredicate> getPredicates() {
			return predicates;
		}
	}

}
