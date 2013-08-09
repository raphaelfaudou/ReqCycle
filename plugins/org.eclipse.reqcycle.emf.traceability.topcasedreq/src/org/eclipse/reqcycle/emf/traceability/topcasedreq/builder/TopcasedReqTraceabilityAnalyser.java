package org.eclipse.reqcycle.emf.traceability.topcasedreq.builder;

import java.util.Collections;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.emf.traceability.topcasedreq.types.TReqTypeProvider;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.visitors.IVisitor;
import org.topcased.requirement.HierarchicalElement;
import org.topcased.requirement.ObjectAttribute;
import org.topcased.requirement.Requirement;
import org.topcased.requirement.util.RequirementResource;

public class TopcasedReqTraceabilityAnalyser implements IVisitor {

	Resource res = null;
	boolean alreadyTried = false;

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		if (o instanceof Resource) {
			Resource r = (Resource) o;
			return RequirementResource.FILE_EXTENSION.equalsIgnoreCase(r
					.getURI().fileExtension());
		}
		if (o instanceof HierarchicalElement) {
			return visit((HierarchicalElement) o, adaptable);
		}
		if (o instanceof ObjectAttribute) {
			return visit((ObjectAttribute) o, adaptable);
		}
		return true;
	}

	public boolean visit(ObjectAttribute att, IAdaptable adaptable) {
		Requirement current = (Requirement) att.eContainer();
		if (att.getValue() != null) {
			createUpwardTraceability(TReqTypeProvider.REFINE, att, current,
					att.getValue(), adaptable);
		}
		return true;
	}

	public boolean visit(HierarchicalElement h, IAdaptable adaptable) {
		EObject element = getElement(h);
		if (element != null) {
			for (Requirement re : h.getRequirement()) {
				createUpwardTraceability(TReqTypeProvider.SATISFY, h, element,
						re, adaptable);
			}
		}
		return true;
	}

	public EObject getElement(HierarchicalElement h) {
		EObject result = h.getElement();
		if (result.eIsProxy()) {
			URI uri = EcoreUtil.getURI(result);
			if ((res == null && !alreadyTried)
					|| (res != null && !res.getURI().trimFragment()
							.equals(uri.trimFragment()))) {
				try {
					if (res != null) {
						if (res.getResourceSet() != null) {
							for (int i = 0; i < res.getResourceSet()
									.getResources().size(); i++) {
								try {
									res.getResourceSet().getResources().get(i)
											.unload();
								} catch (Exception e) {
								}
								res.getResourceSet().getResources().clear();
							}
							res.getResourceSet().getResources().clear();
						}
					}
					alreadyTried = true;
					res = EMFUtils.getFastAndUnresolvingResourceSet()
							.getResource(uri.trimFragment(), true);
				} catch (RuntimeException e) {
				}
			}
			if (res != null) {
				result = res.getEObject(uri.fragment());
			}
		}
		return result;
	}

	public void createUpwardTraceability(TType traceaLabel,
			EObject containerOfTrace, EObject source, EObject target,
			IAdaptable adaptable) {
		getCallBack(adaptable).newUpwardRelation(containerOfTrace,
				containerOfTrace.eResource(), source,
				Collections.singletonList(target), traceaLabel);
	}

	private IBuilderCallBack getCallBack(IAdaptable adapable) {
		return (IBuilderCallBack) adapable.getAdapter(IBuilderCallBack.class);
	}

	@Override
	public void start(IAdaptable adapable) {
		// DO NOTHING
	}

	@Override
	public void end(IAdaptable adapable) {
		if (res != null) {
			res.unload();
		}
		alreadyTried = false;
	}

}
