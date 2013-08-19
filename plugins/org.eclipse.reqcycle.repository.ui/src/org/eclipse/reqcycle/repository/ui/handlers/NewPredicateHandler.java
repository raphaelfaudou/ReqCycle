package org.eclipse.reqcycle.repository.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.reqcycle.predicates.ui.util.PredicatesUIHelper;

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
