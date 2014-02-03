package org.eclipse.reqcycle.jdt.traceability;

import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IAnnotatable;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMemberValuePair;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.ILogicalIDManager;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

public class JDTTraceabilityVisitor implements IVisitor {

	private IAdaptable adaptable;
	@Inject
	ILogicalIDManager manager;

	Map<String, TType> mapOfTypes = JDTPreferences.getPreferences();

	@Override
	public void start(IAdaptable adaptable) {

	}

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		this.adaptable = adaptable;
		if (o instanceof IFile) {
			IFile file = (IFile) o;
			return file.getFileExtension().equalsIgnoreCase("java");
		}
		if (o instanceof IJavaElement) {
			if (o instanceof IAnnotatable) {
				IAnnotatable annotable = (IAnnotatable) o;
				visit(annotable);
			}
		}
		return true;
	}

	private void visit(IAnnotatable annot) {
		for (String s : mapOfTypes.keySet()) {
			IAnnotation ann = annot.getAnnotation(s);
			handleAnnot(annot, ann, mapOfTypes.get(s));
		}

	}

	protected void handleAnnot(IAnnotatable annot, IAnnotation ann,
			TType traceabilityLink) {
		if (ann != null) {
			IMemberValuePair[] pairs;
			try {
				pairs = ann.getMemberValuePairs();
				if (pairs != null) {
					for (IMemberValuePair m : pairs) {
						if (m.getValue() instanceof String) {
							String value = (String) m.getValue();
							if (annot instanceof IJavaElement) {
								Reachable reachable = manager
										.getReachable(value);
								if (reachable != null) {
									getCallBack()
											.newUpwardRelation(
													((IJavaElement) annot)
															.getElementName(),
													((IJavaElement) annot)
															.getResource(),
													annot,
													Collections
															.singletonList(reachable),
													traceabilityLink);
								}
							}
						}
					}
				}
			} catch (JavaModelException e) {
			}
		}
	}

	@Override
	public void end(IAdaptable adaptable) {

	}

	public IBuilderCallBack getCallBack() {
		return (IBuilderCallBack) adaptable.getAdapter(IBuilderCallBack.class);
	}
}
