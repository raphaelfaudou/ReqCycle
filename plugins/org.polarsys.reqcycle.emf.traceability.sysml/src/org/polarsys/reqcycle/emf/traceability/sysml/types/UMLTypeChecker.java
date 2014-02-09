/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.emf.traceability.sysml.types;

import javax.inject.Inject;

import org.eclipse.core.runtime.IAdaptable;
import org.polarsys.reqcycle.emf.types.EMFTypeChecker;
import org.polarsys.reqcycle.types.IInjectedTypeChecker;
import org.polarsys.reqcycle.uri.IReachableManager;
import org.polarsys.reqcycle.uri.exceptions.IReachableHandlerException;
import org.polarsys.reqcycle.uri.exceptions.VisitableException;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.uri.visitors.IVisitor;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;

public class UMLTypeChecker implements IInjectedTypeChecker {
	@Inject
	IReachableManager manager;
	@InjectValue
	String stereotypeName;
	@InjectValue
	String elementName;

	@Override
	public boolean apply(Reachable reachable) {
		EMFTypeChecker emfTypeChecker = new EMFTypeChecker();
		if (emfTypeChecker.apply(reachable)) {
			try {
				ReachableObject object = manager.getHandlerFromReachable(
						reachable).getFromReachable(reachable);
				IsUMLVisitor visitor = new IsUMLVisitor();
				object.getVisitable().accept(visitor);
				return visitor.found;
			} catch (IReachableHandlerException e) {
				e.printStackTrace();
			} catch (VisitableException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	class IsUMLVisitor implements IVisitor {
		boolean found = false;

		@Override
		public void start(IAdaptable adaptable) {
		}

		@Override
		public boolean visit(Object o, IAdaptable adaptable) {
			if (o instanceof Element) {
				if (stereotypeName == null && elementName == null) {
					found = true;
				} else {
					found = false;
					Element e = (Element) o;
					if (isStereotypeOk(e) && isElementOK(e)) {
						found = true;
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

	/**
	 * @param e
	 * @return
	 */
	public boolean isElementOK(Element e) {
		boolean isOK = true;
		if (elementName != null) {
			isOK = elementName.equalsIgnoreCase(e.eClass().getName());
		}
		return isOK;

	}

	/**
	 * @param e
	 * @return
	 */
	public boolean isStereotypeOk(Element e) {
		boolean isOK = true;
		if (stereotypeName != null) {
			isOK = false;
			//-RFU- check stereotypes applicable rather than those applied
			for (Stereotype s : e.getApplicableStereotypes()) {
				if (stereotypeName.equalsIgnoreCase(s.getName())) {
					isOK = true;
					break;
				}
			}
		}
		return isOK;
	}
}
