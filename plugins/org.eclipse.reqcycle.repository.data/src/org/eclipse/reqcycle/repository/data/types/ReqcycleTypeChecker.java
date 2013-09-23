package org.eclipse.reqcycle.repository.data.types;

import javax.inject.Inject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.emf.types.EMFTypeChecker;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.types.IInjectedTypeChecker;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

import RequirementSourceData.AbstractElement;
import ScopeConf.Scope;


public class ReqcycleTypeChecker implements IInjectedTypeChecker {

	@Inject
	IReachableManager manager;

	@InjectValue
	String dataModel;

	@InjectValue
	String requirementScope;

	@Inject
	IDataModelManager dataModelManager;

	@Override
	public boolean apply(Reachable reachable) {
		EMFTypeChecker emfTypeChecker = new EMFTypeChecker();
		if(emfTypeChecker.apply(reachable)) {
			try {
				ReachableObject object = manager.getHandlerFromReachable(reachable).getFromReachable(reachable);
				IsRequirementTypeVisitor visitor = new IsRequirementTypeVisitor();
				object.getVisitable().accept(visitor);
				return visitor.getResult();
			} catch (IReachableHandlerException e) {
				e.printStackTrace();
			} catch (VisitableException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	class IsRequirementTypeVisitor implements IVisitor {

		boolean found = false;

		@Override
		public void start(IAdaptable adaptable) {
		}

		@Override
		public boolean visit(Object o, IAdaptable adaptable) {
			//FIXME : Scope can't be resolved, fixe this after fixing scope bug
			found = o instanceof AbstractElement;
			if(o instanceof AbstractElement && requirementScope != null && dataModel != null) {
				AbstractElement type = (AbstractElement)o;
				for(Scope s : type.getScopes()) {
					if(requirementScope.equalsIgnoreCase(s.eClass().getName()) && dataModelManager.getDataModel(s).equals(dataModelManager.getDataModel(dataModel))) {
						found = true;
						return true;
					}
				}
			}
			return found;
		}

		@Override
		public void end(IAdaptable adaptable) {
		}

		boolean getResult() {
			return found;
		}

	}

}
