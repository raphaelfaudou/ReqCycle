package org.eclipse.reqcycle.jdt.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

public class JDTVisitable implements IVisitable, IAdaptable {

	private IJavaElement element;

	public JDTVisitable(IJavaElement element) {
		this.element = element;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.start(this);
		doAccept(element, visitor);
		visitor.end(this);
	}

	private void doAccept(IJavaElement anElement, IVisitor visitor) {
		visitor.visit(anElement, this);
		if (anElement instanceof IParent) {
			IParent parent = (IParent) anElement;
			try {
				for (IJavaElement j : parent.getChildren()) {
					doAccept(j, visitor);
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public Object getAdapter(Class adapter) {
		if (IJavaElement.class.equals(adapter)) {
			return element;
		}
		return null;
	}

}
