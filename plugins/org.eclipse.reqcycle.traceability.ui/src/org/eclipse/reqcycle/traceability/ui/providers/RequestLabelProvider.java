package org.eclipse.reqcycle.traceability.ui.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.ui.TraceabilityUtils;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.swt.graphics.Image;

public class RequestLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof BusinessDeffered) {
			element = ((BusinessDeffered) element).getBusinessElement();
		}
		if (element instanceof Reachable) {
			Reachable reach = (Reachable) element;
			return TraceabilityUtils.getText(reach);
		} else if (element instanceof Link) {
			return ((Link) element).getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof BusinessDeffered) {
			element = ((BusinessDeffered) element).getBusinessElement();
		}
		if (element instanceof Reachable) {
			Reachable reach = (Reachable) element;
			Image image = TraceabilityUtils.getImage(reach);
			if (image != null) {
				return image;
			}
		}
		return super.getImage(element);
	}

}
