package org.eclipse.reqcycle.repository.data.types;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
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

	@InjectValue
	String dataType;

	@Inject
	IDataModelManager dataModelManager;

	@Inject
	@Named("confResourceSet")
	ResourceSet rs;

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
			found = false;
			if(o instanceof AbstractElement) {
				AbstractElement type = (AbstractElement)o;
				found = true;

				if(dataModel != null) {

					if(!type.eClass().getEPackage().getName().equals(dataModel)) {
						found = false;
					}

					if(found && requirementScope != null) {
						found = false;
						for(Scope s : type.getScopes()) {
							if(s.eIsProxy()) {
								EObject newObj = EcoreUtil.resolve(s, rs);
								if(newObj instanceof Scope) {
									s = (Scope)newObj;
								}
							}
							if(requirementScope.equals(s.getName())) {
								found = true;
							}
						}
					}

					if(found && dataType != null) {
						found = false;
						String className = type.eClass().getName();
						found = className.equals(dataType);
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
