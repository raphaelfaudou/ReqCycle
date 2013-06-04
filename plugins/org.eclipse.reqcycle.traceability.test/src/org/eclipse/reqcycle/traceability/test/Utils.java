package org.eclipse.reqcycle.traceability.test;

import static org.junit.Assert.fail;

import org.agesys.inject.AgesysInject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.reqcycle.emf.utils.EMFUtils;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;

public class Utils {
	// public static Reachable getTraceable(URI emfUri) {
	// Reachable t = new Reachable();
	// t.setAuthority(emfUri.authority());
	// t.setFragment(emfUri.fragment());
	// t.setHost(emfUri.host());
	// t.setPath(emfUri.path());
	// if (emfUri.port() != null) {
	// t.setPort(Integer.parseInt(emfUri.port()));
	// }
	// t.setQuery(emfUri.query());
	// t.setScheme(emfUri.scheme());
	// t.setUserInfo(emfUri.userInfo());
	// return t;
	// }

	public static Reachable getReachable(URI uri) {
		return EMFUtils.getReachable(uri);
	}

	public static ILabelProvider getLabelProvider(Reachable r) {
		IReachableManager manager = AgesysInject.make(IReachableManager.class);
		IReachableHandler object;
		try {
			object = manager.getHandlerFromReachable(r);
			if (object == null) {
				return null;
			}
			ILabelProvider p = (ILabelProvider) object.getFromReachable(r)
					.getAdapter(ILabelProvider.class);
			return p;
		} catch (IReachableHandlerException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		return null;
	}

}
