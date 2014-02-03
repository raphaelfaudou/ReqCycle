package org.polarsys.reqcycle.traceability.engine;

import java.util.Iterator;

import org.polarsys.reqcycle.traceability.exceptions.EngineException;
import org.polarsys.reqcycle.traceability.model.Link;
import org.polarsys.reqcycle.traceability.model.Pair;
import org.polarsys.reqcycle.uri.model.Reachable;

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
