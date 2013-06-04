package org.eclipse.reqcycle.traceability.engine;

import java.util.Iterator;

import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.uri.model.Reachable;

/**
 * Interface for traceability management
 */
public interface ITraceabilityEngine {
	public enum DIRECTION {
		UPWARD, DOWNWARD
	}

	/**
	 * @return a traceability {@link Iterator} from the {@link Request}
	 * 
	 */
	Iterator<Pair<Link, Reachable>> getTraceability(Request... requests)
			throws EngineException;

}
