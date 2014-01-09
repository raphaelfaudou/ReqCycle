package org.eclipse.reqcycle.xcos.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.EmptyVisitable;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.reqcycle.xcos.utils.XcosUtils;

public class XcosReachableObject implements ReachableObject {

	private Reachable reachable;
	IFile file = null;
	private XcosElement element;

	public XcosReachableObject(Reachable t) {
		this.reachable = t;
		String path = t.trimFragment().toString()
				.replaceFirst(XcosUtils.PLATFORM, "");
		file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		element = createElement(file);
//		if (element instanceof IXcosModelElement) {
//			ITypeRoot aClass = (ITypeRoot) element;
//			String fragment = t.getFragment();
//			if (fragment != null && fragment.length() > 0) {
//				String[] splitted = fragment.split(XcosUtils.SEPARATOR);
//				if (splitted.length > 0) {
//					element = findMethodRecursively(aClass, splitted);
//				} else {
//					element = aClass;
//				}
//			} else {
//				element = aClass;
//			}
//		}
	}
	
	private XcosElement createElement (IResource res) {
		//TODO real impl
		return new XcosElement(res.getName(), res);
	}

//	private IJavaElement findMethodRecursively(IParent aClass,
//			final String[] javaElement) {
//		try {
//			IJavaElement result = null;
//			boolean found = false;
//			IParent currentContainer = aClass;
//			int i = 0;
//			for (String s : javaElement) {
//				found = false;
//				if (currentContainer != null) {
//					for (IJavaElement j : currentContainer.getChildren()) {
//						if (s.equals(j.getElementName())) {
//							if (i == javaElement.length - 1) {
//								result = j;
//								found = true;
//							} else if (j instanceof IParent) {
//								currentContainer = (IParent) j;
//							}
//							break;
//						}
//					}
//				}
//				i++;
//			}
//			if (found && result != null) {
//				return result;
//			}
//		} catch (JavaModelException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public Object getAdapter(Class adapter) {
		if (ILabelProvider.class.equals(adapter)) {
			return new XcosLabelProvider();
		}
		if (XcosElement.class.equals(adapter)) {
			return element;
		}
		return null;
	}

	@Override
	public IVisitable getVisitable() throws VisitableException {
		if (element != null) {
			return new XcosVisitable(element);
		} else {
			return new EmptyVisitable();
		}
	}

	@Override
	public String getRevisionIdentification() {
		return String.valueOf(file.getLocalTimeStamp());
	}

	@Override
	public Reachable getReachable(Object o) {
		return reachable;
	}

}
