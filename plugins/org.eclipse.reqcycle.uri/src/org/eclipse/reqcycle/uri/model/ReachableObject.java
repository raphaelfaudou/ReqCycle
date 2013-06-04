package org.eclipse.reqcycle.uri.model;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.uri.exceptions.VisitableException;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;

public interface ReachableObject extends IAdaptable {

	/**
	 * Returns a {@link IVisitable} able to browse the Traceable object
	 * 
	 * @return the {@link IVisitable} accepting {@link IVisitor}
	 * @throws VisitableException
	 */
	IVisitable getVisitable() throws VisitableException;

	/**
	 * Returns a String corresponding to unique revision of the resource to
	 * determine if file is changed example : time stamp, md5 hash....
	 * 
	 * @return NULL if it is impossible to determine a revision
	 */
	String getRevisionIdentification();

	/**
	 * Get the reachable according to the given parameter can be null in this
	 * case the reachable corresponds to the top level element
	 * 
	 * @param o
	 * @return
	 */
	Reachable getReachable(Object o);
}
