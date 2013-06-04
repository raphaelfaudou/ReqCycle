package org.eclipse.reqcycle.emf.traceability.topcasedreq.builder;

import java.util.Collections;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
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
			createUpwardTraceability(
					TType.custom(TraceabilityLink.REFINE, att.getName()),
					current, current, att.getValue(), adaptable);
		}
		return true;
	}

	public boolean visit(HierarchicalElement h, IAdaptable adaptable) {
		EObject element = getElement(h);
		if (element != null) {
			for (Requirement re : h.getRequirement()) {
				createUpwardTraceability(
						TType.custom(TraceabilityLink.SATISFY, "element"), re,
						element, re, adaptable);
			}
		}
		return true;
	}

	public EObject getElement(HierarchicalElement h) {
		EObject result = h.getElement();
		if (result.eIsProxy()) {
			URI uri = EcoreUtil.getURI(result);
			if (res == null && !alreadyTried) {
				try {
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
		getCallBack(adaptable).newUpwardRelation(containerOfTrace.eResource(),
				source, Collections.singletonList(target), traceaLabel);
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
