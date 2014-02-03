package org.polarsys.reqcycle.jdt.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JavaProject;
import org.polarsys.reqcycle.jdt.model.JDTReachableObject;
import org.polarsys.reqcycle.jdt.utils.JDTUtils;
import org.polarsys.reqcycle.types.ITypeChecker;
import org.polarsys.reqcycle.uri.model.IObjectHandler;
import org.polarsys.reqcycle.uri.model.IReachableHandler;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class JDTReachableHandler implements IReachableHandler, IObjectHandler,
		ITypeChecker {

	public JDTReachableHandler() {
	}

	@Override
	public ReachableObject getFromReachable(Reachable t) {
		JDTReachableObject jdtReachableObject = new JDTReachableObject(t);
		ZigguratInject.inject(jdtReachableObject);
		return jdtReachableObject;
	}

	@Override
	public boolean handlesReachable(Reachable t) {
		return t != null && t.getPath() !=null && t.getPath().endsWith("java");
	}

	@Override
	public ReachableObject getFromObject(Object object) {
		if (object instanceof IFile) {
			IFile file = (IFile) object;
			if ("java".equalsIgnoreCase(file.getFileExtension())) {
				return JDTUtils.getReachable(file);
			}
		}
		if (object instanceof IJavaElement) {
			IJavaElement cu = (IJavaElement) object;
			return JDTUtils.getReachable(cu);
		}
		return null;
	}

	@SuppressWarnings("restriction")
	@Override
	public boolean handlesObject(Object object) {
		if (object instanceof IFile) {
			IFile file = (IFile) object;
			if ("java".equalsIgnoreCase(file.getFileExtension())) {
				return JavaProject.hasJavaNature(file.getProject());
			}
		}
		return (object instanceof IJavaElement);
	}

	@Override
	public boolean apply(Reachable reachable) {
		return handlesReachable(reachable);
	}

}
