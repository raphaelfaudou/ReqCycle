package org.eclipse.reqcycle.uri.impl.handlers;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class StandardUtils {

	public static String SCHEME = "reqcycleStd";

	static IReachableCreator creator = ZigguratInject
			.make(IReachableCreator.class);

	public static Reachable getReachable(Object o) {
		return getReachable(getURI(o));
	}

	private static URI getURI(Object o) {
		if (o instanceof URI) {
			return (URI) o;
		}
		try {
			URI uri = new URI(SCHEME, o.toString(), "");
			return uri;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Reachable getReachable(URI o) {
		return creator.getReachable(o, o);
	}
}
