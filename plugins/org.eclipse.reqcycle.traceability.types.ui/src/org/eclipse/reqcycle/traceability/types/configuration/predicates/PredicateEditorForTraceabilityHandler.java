package org.eclipse.reqcycle.traceability.types.configuration.predicates;

import static com.google.common.collect.Iterables.filter;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.reqcycle.predicates.core.PredicatesFactory;
import org.eclipse.reqcycle.predicates.core.api.IPredicate;
import org.eclipse.reqcycle.predicates.persistance.util.IPredicatesConfManager;
import org.eclipse.reqcycle.predicates.ui.util.PredicatesUIHelper;
import org.eclipse.ziggurat.inject.ZigguratInject;

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
