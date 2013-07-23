package org.eclipse.reqcycle.repository.data.types;

import javax.inject.Inject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.emf.types.EMFTypeChecker;
import org.eclipse.reqcycle.types.IInjectedTypeChecker;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

import DataModel.Requirement;
import DataModel.Scope;


public class ReqcycleTypeChecker implements IInjectedTypeChecker {

	@Inject
	IReachableManager manager;

	@InjectValue
	String requirementScope;

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

		public void start(IAdaptable adaptable) {
		}

		public boolean visit(Object o, IAdaptable adaptable) {
			found = o instanceof Requirement;
			if(found && requirementScope != null) {
				Requirement type = (Requirement)o;
				for(Scope s : type.getScopes()) {
					if(requirementScope.equalsIgnoreCase(s.eClass().getName())) {
						return true;
					}
				}
			}
			return found;
		}

		public void end(IAdaptable adaptable) {
		}

		boolean getResult() {
			return found;
		}

	}

}
