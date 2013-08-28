package org.eclipse.reqcycle.emf.handlers;

import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.emf.ui.EMFLabelProvider;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.emf.visitors.EMFVisitable;
import org.eclipse.reqcycle.uri.Activator;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class EMFReachableObject implements ReachableObject {
	private final Reachable t;
	@Inject
	ILogger logger;

	public EMFReachableObject(Reachable t) {
		this.t = t;
	}

	@Override
	public IVisitable getVisitable() throws VisitableException {
		try {
			EMFVisitable emfVisitable = doGetVisitable(EMFUtils.getEMFURI(t));
			ZigguratInject.inject(emfVisitable);
			return emfVisitable;
		} catch (RuntimeException e) {
			if (Activator.getDefault().isDebugging()) {
				logger.trace("Error loading " + t.toString());
			}
			throw new VisitableException();
		}
	}

	protected EMFVisitable doGetVisitable(URI uri) {
		return new EMFVisitable(uri);
	}

	@Override
	public String getRevisionIdentification() {
		EMFVisitable emfVisitable;
		try {
			emfVisitable = (EMFVisitable) getVisitable();
			String result = emfVisitable.getResourceTimeStamp();
			emfVisitable.dispose();
			return result;
		} catch (VisitableException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (IMarker.class.equals(adapter)) {
			IFile f = (IFile) getAdapter(IFile.class);
			if (f != null) {
				try {
					IMarker marker = f.createMarker(EValidator.MARKER);
					marker.setAttribute(EValidator.URI_ATTRIBUTE, URI
							.createURI(t.toString()).toString());
					return marker;
				} catch (CoreException e) {
				}
			}
		}
		if (IResource.class.equals(adapter) || IFile.class.equals(adapter)) {
			try {
				return WorkspaceSynchronizer
						.getFile(((EMFVisitable) getVisitable()).getResource());
			} catch (VisitableException e) {
				e.printStackTrace();
			}
		}
		if (Resource.class.equals(adapter)) {
			try {
				return ((EMFVisitable) getVisitable()).getResource();
			} catch (VisitableException e) {
				e.printStackTrace();
			}
		}
		if (IVisitable.class.equals(adapter)) {
			try {
				return getVisitable();
			} catch (VisitableException e) {
				e.printStackTrace();
			}
		}
		if (ILabelProvider.class.equals(adapter)) {
			return new EMFLabelProvider();
		}
		return null;
	}

	@Override
	public Reachable getReachable(Object o) {
		if (o == null || o instanceof Resource) {
			return t;
		}
		if (o instanceof EObject) {
			return EMFUtils.getReachable((EObject) o);
		}
		if (o instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) o;
			EObject e = (EObject) adaptable.getAdapter(EObject.class);
			if (e != null) {
				return EMFUtils.getReachable(e);
			}

		}
		return null;
	}
}