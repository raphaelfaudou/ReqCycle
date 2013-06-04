package org.eclipse.reqcycle.emf.traceability.sysml.handler;

import java.util.Collections;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.sysml.requirements.RequirementsPackage;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
import org.eclipse.reqcycle.uri.visitors.IVisitor;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.profile.l2.Trace;

public class SysMLTraceabilityVisitor implements IVisitor {

	@Override
	public boolean visit(Object o, IAdaptable adapable) {
		if (o instanceof Resource) {
			return ((Resource) o).getURI() != null
					&& ((Resource) o).getURI().fileExtension() != null
					&& ((Resource) o).getURI().fileExtension().equals("uml");
		}
		if (o instanceof Trace) {
			return visit((Trace) o, adapable);
		}
		return true;
	}

	public boolean visit(Trace satis, IAdaptable adaptable) {
		Abstraction abstraction = satis.getBase_Abstraction();
		if (abstraction != null) {
			Object source = abstraction.getClients().get(0);
			Object target = abstraction.getSuppliers().get(0);
			TType tType = getTType(satis);
			getCallBack(adaptable).newUpwardRelation(satis.eResource(), source,
					Collections.singletonList(target), tType);
		}
		return true;
	}

	private TType getTType(Trace satis) {
		switch (satis.eClass().getClassifierID()) {
		case RequirementsPackage.COPY:
			return TType.get(TraceabilityLink.COPY);
		case RequirementsPackage.DERIVE_REQT:
			return TType.get(TraceabilityLink.DERIVE);
		case RequirementsPackage.SATISFY:
			return TType.get(TraceabilityLink.SATISFY);
		case RequirementsPackage.VERIFY:
			return TType.get(TraceabilityLink.VERIFY);
		default:
			return TType.get(TraceabilityLink.TRACE);
		}
	}

	private IBuilderCallBack getCallBack(IAdaptable adaptable) {
		return (IBuilderCallBack) adaptable.getAdapter(IBuilderCallBack.class);
	}

	@Override
	public void start(IAdaptable adaptable) {
	}

	@Override
	public void end(IAdaptable adaptable) {
	}

}
