package org.polarsys.reqcycle.predicates.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.polarsys.reqcycle.predicates.ui.util.PredicatesUIHelper;

public class EditPredicateHandler extends AbstractHandler {


	public EditPredicateHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		PredicatesUIHelper.editPredicate();
		return null;
	}


}
