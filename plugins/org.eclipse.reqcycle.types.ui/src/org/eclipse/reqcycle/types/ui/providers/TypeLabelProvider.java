package org.eclipse.reqcycle.types.ui.providers;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.types.Activator;
import org.eclipse.reqcycle.types.IType;
import org.eclipse.swt.graphics.Image;

public class TypeLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof IType) {
			IType type = (IType) element;
			return getImage(type);
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IType) {
			IType type = (IType) element;
			return type.getLabel();
		}
		return super.getText(element);
	}

	public Image getImage(IType type) {
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		Image result = imageRegistry.get(type.getId());
		if (result == null) {
			imageRegistry.put(type.getId(), type.getIcon());
		}
		result = imageRegistry.get(type.getId());
		return result;
	}

}
