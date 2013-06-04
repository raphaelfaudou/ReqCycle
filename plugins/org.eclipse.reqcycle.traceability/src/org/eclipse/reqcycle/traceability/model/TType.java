package org.eclipse.reqcycle.traceability.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * TraceabilityType determines the type of the relation according to a possible
 * supertype
 * 
 * @author tfaure
 * 
 */
public class TType implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Map<String, String> metadata = new HashMap<String, String>();
	static Map<TraceabilityLink, TType> map = new HashMap<TraceabilityLink, TType>();
	static {
		for (TraceabilityLink t : TraceabilityLink.values()) {
			map.put(t, new TType(t));
		}
	}

	private TraceabilityLink type;
	private String specialization;

	public static TType get(TraceabilityLink link) {
		return map.get(link);
	}

	/**
	 * Create a custom type this type has just the "Trace" meaning
	 * 
	 * @param label
	 * @return
	 */
	public static TType custom(String label) {
		return custom(TraceabilityLink.TRACE, label);
	}

	public static TType custom(TraceabilityLink superType, String label) {
		return new TType(superType, label);
	}

	private TType(TraceabilityLink type) {
		this(type, type.getLabel());
	}

	private TType(TraceabilityLink superType, String specialization) {
		this.type = superType;
		this.specialization = specialization;
	}

	public TraceabilityLink getSuperType() {
		return type;
	}

	public String getSemantic() {
		if (specialization == null) {
			return type.getLabel();
		} else {
			return specialization;
		}

	}

	public boolean is(TType type) {
		return this.type.equals(type);
	}

	public boolean isBasic() {
		return specialization == null;
	}

	@Override
	public String toString() {
		return getSemantic();
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}
}
