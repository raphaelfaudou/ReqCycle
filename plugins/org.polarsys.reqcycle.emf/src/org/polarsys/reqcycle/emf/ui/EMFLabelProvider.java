package org.polarsys.reqcycle.emf.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.eclipse.swt.graphics.Image;

public class EMFLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof Reachable) {
			Reachable r = (Reachable) element;
			return EMFEditExtender.getImage(r);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Reachable) {
			Reachable r = (Reachable) element;
			return EMFEditExtender.getLabel(r);
		}
		return "";
	}

}
