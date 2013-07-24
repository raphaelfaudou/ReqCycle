package org.eclipse.reqcycle.traceability.types;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.RegisteredAttribute;

/**
 * Manages the different TTypes registered
 * 
 * @author Tristan FAURE
 * 
 */
public interface ITraceTypesManager {
	/**
	 * Returns all the registered types
	 * 
	 * @return an {@link Iterable} of {@link TType}
	 */
	Iterable<TType> getAllTTypes();

	/**
	 * Returns the {@link TType} with the given id
	 * 
	 * @param id
	 * @return
	 */
	TType getTType(String id);

	/**
	 * @param id
	 * @return
	 */
	RegisteredAttribute getAttribute(String id);

	Iterable<RegisteredAttribute> getAllAttributes();

}
