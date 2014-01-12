package org.eclipse.reqcycle.xcos.model;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.xcos.Activator;
import org.eclipse.swt.graphics.Image;

public class XcosLabelProvider extends LabelProvider {

	private static final String ICONS_JMETH_OBJ_GIF = "/icons/jmeth_obj.gif";
	public static ImageDescriptor desc = Activator.imageDescriptorFromPlugin(
			Activator.PLUGIN_ID, ICONS_JMETH_OBJ_GIF);

	@Override
	public Image getImage(Object element) {
		Image image = JFaceResources.getImage(Activator.PLUGIN_ID
				+ ICONS_JMETH_OBJ_GIF);
		if (image == null) {
			JFaceResources.getImageRegistry().put(
					Activator.PLUGIN_ID + ICONS_JMETH_OBJ_GIF, desc);
			image = JFaceResources.getImage(Activator.PLUGIN_ID
					+ ICONS_JMETH_OBJ_GIF);
		}
		return image;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Reachable) {
			Reachable reach = (Reachable) element;
			return "Xcos" + reach.getFragment();
		}
		return super.getText(element);
	}

}
