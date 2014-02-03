package org.polarsys.reqcycle.predicates.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.polarsys.reqcycle.predicates.ui.util.PredicatesUIHelper;


public class OpenPredicatesEditorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		//		NewPredicateDialog dialog = new NewPredicateDialog(HandlerUtil.getActiveShell(event));
		//		if(Window.OK == dialog.open()) {
		//			String name = dialog.getName();
		//			IPredicate rootPredicate = dialog.getRootPredicate();
		//			
		//		}

		PredicatesUIHelper.openEditor(null, null);

		return null;
	}


}
