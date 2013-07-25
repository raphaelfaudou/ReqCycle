package org.eclipse.reqcycle.traceability.types;

import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;

public class RelationBasedType extends TType {

	private static final long serialVersionUID = 1L;

	public static String RELATION_BASED_NS = Activator.PLUGIN_ID
			+ ".relations.";

	public RelationBasedType(Relation rel) {
		this(rel, null);
	}

	public RelationBasedType(Relation rel, TType superType) {
		super(RELATION_BASED_NS + rel.getKind(), rel.getKind(), superType);
	}

}
