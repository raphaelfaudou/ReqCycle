package org.eclipse.reqcycle.traceability.ui;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IReachableHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class TraceabilityUtils {
	public static String getText(Reachable r) {
		ILabelProvider provider = getProvider(r);
		if (provider != null) {
			return provider.getText(r);
		}
		return r.toString();

	}

	public static ILabelProvider getProvider(Reachable r) {
		IReachableManager manager = ZigguratInject.make(IReachableManager.class);
		try {
			IReachableHandler handler = manager.getHandlerFromReachable(r);
			ReachableObject reachableObject = handler.getFromReachable(r);
			if (reachableObject != null) {
				ILabelProvider provider = (ILabelProvider) reachableObject
						.getAdapter(ILabelProvider.class);
				return provider;
			}
		} catch (IReachableHandlerException e) {
		}
		return null;
	}

	public static Image getImage(Reachable r) {
		ILabelProvider provider = getProvider(r);
		if (provider != null) {
			return provider.getImage(r);
		}
		return null;
	}
}
