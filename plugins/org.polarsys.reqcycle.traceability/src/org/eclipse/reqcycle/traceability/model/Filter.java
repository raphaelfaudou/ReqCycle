package org.eclipse.reqcycle.traceability.model;

import org.eclipse.reqcycle.uri.model.Reachable;

/**
 * A filter determines if the given pair is selected for the traceability
 * 
 * @author tfaure
 * 
 */
public interface Filter {
	boolean apply(Pair<Link, Reachable> pair);
}
