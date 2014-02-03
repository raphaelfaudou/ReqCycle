package org.eclipse.reqcycle.emf.traceability.sysml.types;

import javax.inject.Inject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.reqcycle.emf.types.EMFTypeChecker;
import org.eclipse.reqcycle.types.IInjectedTypeChecker;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.IVisitor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public class SysMLTypeChecker implements IInjectedTypeChecker {
	@Inject
	IReachableManager manager;

	@InjectValue
	String sysmlElementName;

	@InjectValue
	String umlElementName;

	@InjectValue
	String applicatedStereotype;

	@Override
	public boolean apply(Reachable reachable) {
		EMFTypeChecker emfTypeChecker = new EMFTypeChecker();
		if (emfTypeChecker.apply(reachable)) {
			try {
				ReachableObject object = manager.getHandlerFromReachable(
						reachable).getFromReachable(reachable);
				IsSysMLVisitor visitor = new IsSysMLVisitor();
				object.getVisitable().accept(visitor);
				return visitor.found;
			} catch (IReachableHandlerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VisitableException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	class IsSysMLVisitor implements IVisitor {
		boolean found = false;

		@Override
		public void start(IAdaptable adaptable) {
		}

		@Override
		public boolean visit(Object o, IAdaptable adaptable) {
			if (o instanceof Element) {
				Element e = (Element) o;
				EList<EObject> eobjects = e.getStereotypeApplications();
				for (EObject tmp : eobjects) {
					if (!tmp.eIsProxy()
							&& tmp.eClass()
									.getEPackage()
									.getNsURI()
									.contains(
											"http://www.eclipse.org/papyrus/0.7.0/SysML")) {
						if (sysmlElementName == null
								&& applicatedStereotype == null
								&& umlElementName == null) {
							found = true;
							break;
						} else {
							if (isElementOK(e) && isStereotypeOk(e)
									&& isSysMLOK(tmp)) {
								found = true;
								break;
							}
						}
					}
				}
			}
			return false;
		}

		@Override
		public void end(IAdaptable adaptable) {

		}

		boolean getResult() {
			return found;
		}

	}

	public boolean isElementOK(Element e) {
		boolean isOK = true;
		if (umlElementName != null) {
			isOK = umlElementName.equalsIgnoreCase(e.eClass().getName());
		}
		return isOK;

	}

	/**
	 * @param e
	 * @return
	 */
	public boolean isStereotypeOk(Element e) {
		boolean isOK = true;
		if (applicatedStereotype != null) {
			isOK = false;
			for (Stereotype s : e.getAppliedStereotypes()) {
				if (applicatedStereotype.equalsIgnoreCase(s.getName())) {
					isOK = true;
					break;
				}
			}
		}
		return isOK;
	}

	public boolean isSysMLOK(EObject e) {
		boolean found = true;
		if (sysmlElementName != null) {
			found = (e.eClass().getName().equalsIgnoreCase(sysmlElementName));
		}
		return found;
	}
}
