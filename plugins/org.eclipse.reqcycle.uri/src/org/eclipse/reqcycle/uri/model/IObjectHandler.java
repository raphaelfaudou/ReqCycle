package org.eclipse.reqcycle.uri.model;

public interface IObjectHandler extends IHandler {
	/**
	 * @param t
	 * @return
	 */
	ReachableObject getFromObject(Object object);

	/**
	 * @param t
	 * @return
	 */
	boolean handlesObject(Object object);

}
