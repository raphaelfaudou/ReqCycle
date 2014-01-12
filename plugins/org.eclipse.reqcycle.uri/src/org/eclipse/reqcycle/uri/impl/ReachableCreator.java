package org.eclipse.reqcycle.uri.impl;

import java.net.URI;

import javax.inject.Singleton;

import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.services.IReachableExtender;

@Singleton
public class ReachableCreator implements IReachableCreator {

	ExtenderManager manager = new ExtenderManager();

	public Reachable getReachable(URI uri) {
		return getReachable(uri, null);
	}

	public Reachable getReachable(URI uri, Object originalObject) {
		Reachable t = new Reachable();
		t.setFragment(uri.getFragment());
		t.setScheme(uri.getScheme());
		t.setSchemeSpecificPart(uri.getSchemeSpecificPart());
		t.setAuthority(uri.getAuthority());
		t.setUserInfo(uri.getUserInfo());
		t.setHost(uri.getHost());
		t.setPath(uri.getPath());
		t.setQuery(uri.getQuery());
		t.setFragment(uri.getFragment());
		t.setPort(uri.getPort());
		System.out.println("getReachable " + originalObject + " for uri " + uri);
		for (IReachableExtender ext : manager.getExtenders(uri, originalObject)) {
			System.out.println(" extender for " + originalObject + " : " + ext);
			t.putAll(ext.getExtendedProperties(uri, originalObject));
		}
		return t;
	}

}
