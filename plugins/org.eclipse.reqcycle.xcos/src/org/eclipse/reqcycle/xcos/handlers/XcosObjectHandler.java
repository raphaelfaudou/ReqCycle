/**
 * 
 */
package org.eclipse.reqcycle.xcos.handlers;

import static org.eclipse.reqcycle.xcos.utils.XcosUtils.XcosExtension;

import org.eclipse.core.resources.IFile;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.xcos.model.XcosElement;
import org.eclipse.reqcycle.xcos.utils.XcosUtils;

/**
 * @author faudouraphael
 *
 */
public class XcosObjectHandler implements IObjectHandler {

	/**
	 * 
	 */
	public XcosObjectHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ReachableObject getFromObject(Object object) {
		if (object instanceof IFile) {
			IFile file = (IFile) object;
			if (XcosExtension.equalsIgnoreCase(file.getFileExtension())) {
				return XcosUtils.getReachable(file);
			}
		}
		if (object instanceof XcosElement) {
			XcosElement xce = (XcosElement) object;
			return XcosUtils.getReachable(xce);
		}
		return null;
	}

	@Override
	public boolean handlesObject(Object object) {
		if (object instanceof IFile) {
			IFile file = (IFile) object;
			if (XcosExtension.equalsIgnoreCase(file.getFileExtension())) {
				//TODO real handleObject for Xcos
				return true;
			}
		}
		return (object instanceof XcosElement);
	}



}
