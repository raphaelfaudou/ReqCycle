package org.polarsys.reqcycle.repository.ui.actions;

import org.eclipse.jface.action.Action;
import org.polarsys.reqcycle.predicates.ui.util.PredicatesUIHelper;

public class OpenPredicatesEditorAction extends Action {

	public OpenPredicatesEditorAction() {
	}

	@Override
	public void run() {
		PredicatesUIHelper.openNewPredicateEditor();
	}


}
