package org.eclipse.reqcycle.traceability.model;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * TraceabilityType determines the type of the relation according to a possible
 * supertype
 * 
 * 2 {@link TType} are equals if their ids are equals
 * 
 * Take care that this class is {@link Serializable} this means all the
 * attributes contained shall be {@link Serializable} you can also override
 * write/read method if you want to use at your own risk
 * 
 * @see ObjectOutputStream
 * @author tfaure
 * 
 */
public class TType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, String> metadata = new HashMap<String, String>();
	private TType superType;
	private String label;
	private String id;

	public TType(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public TType(String id, TType superType) {
		this(id, superType.getLabel());
		this.superType = superType;
	}

	public TType(String id, String label, TType superType) {
		this(id, label);
		this.superType = superType;
	}

	public String getLabel() {
		return label;
	}

	public TType getSuperType() {
		return superType;
	}

	public String getId() {
		return id;
	}

	public String getSemantic() {
		if (superType != null) {
			return superType.getLabel();
		} else {
			return label;
		}

	}

	public boolean is(TType type) {
		return this.superType.equals(type);
	}

	public boolean isBasic() {
		return superType == null;
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TType) {
			TType ttype = (TType) obj;
			return ttype.getId().equals(this.getId());
		}
		return super.equals(obj);
	}

}
