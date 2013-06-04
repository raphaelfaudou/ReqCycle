package org.eclipse.reqcycle.uri.visitors;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.uri.model.IReachableHandler;

/**
 * Interface for visiting result of {@link IReachableHandler}
 * 
 * @author tfaure
 * 
 */
public interface IVisitor {

	/**
	 * first method called during visiting
	 * 
	 * @param adapable
	 *            , object adaptable in several classes depending on the caller
	 */
	void start(IAdaptable adaptable);

	/**
	 * Visit for each new element
	 * 
	 * @param o
	 *            the current element
	 * @param adapable
	 *            object adaptable in several classes depending on the caller
	 * @return true if the visit shall continue false otherwise
	 */
	boolean visit(Object o, IAdaptable adaptable);

	/**
	 * last method called during visiting
	 * 
	 * @param adapable
	 *            , object adaptable in several classes depending on the caller
	 */
	void end(IAdaptable adaptable);

}
