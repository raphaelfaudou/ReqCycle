package org.eclipse.reqcycle.traceability.types;

import java.util.Collection;

import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.AttributeType;
import org.eclipse.reqcycle.uri.model.Reachable;

public interface ITraceabilityAttributesManager {

	/**
	 * Returns a {@link Collection} of {@link EditableAttribute} of a
	 * {@link Reachable}. The attributes returned are those defined in the
	 * enabled configuration
	 * 
	 * @param reachable
	 * @return
	 */
	Collection<EditableAttribute> getAttributes(Reachable reachable);

	public interface EditableAttribute {
		String getName();

		Object getValue();

		void setValue(Object value);

		AttributeType getType();

		public Object[] getPossibleValues();
	}
}
