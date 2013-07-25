package org.eclipse.reqcycle.types;

import org.eclipse.reqcycle.uri.model.Reachable;

/**
 * 
 * @author tfaure
 *
 */
public interface ICachedTypedChecker {
	boolean is(Reachable reachable, ITypeChecker typeCheckers);
}
