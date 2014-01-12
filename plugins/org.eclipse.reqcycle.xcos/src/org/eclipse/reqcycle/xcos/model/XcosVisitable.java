package org.eclipse.reqcycle.xcos.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

public class XcosVisitable implements IVisitable, IAdaptable {

	private XcosElement element;

	public XcosVisitable(XcosElement element) {
		this.element = element;
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.start(this);
		doAccept(element, visitor);
		visitor.end(this);
	}

	private void doAccept(XcosElement anElement, IVisitor visitor) {
		visitor.visit(anElement, this);
		if (anElement instanceof XcosModel) {
			XcosModel parent = (XcosModel) anElement;
			
			// we handle all Xcos blocks
			for (XcosElement x : parent.getBlocks()) {
				doAccept(x, visitor);
			}
			
			// we handle all Xcos traces to external elements (requirement, SysML element...)
			for (XcosElement trace : parent.getTraces()) {
				doAccept(trace, visitor);
			}
			
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public Object getAdapter(Class adapter) {
		if (XcosElement.class.equals(adapter)) {
			return element;
		}
		return null;
	}

}
