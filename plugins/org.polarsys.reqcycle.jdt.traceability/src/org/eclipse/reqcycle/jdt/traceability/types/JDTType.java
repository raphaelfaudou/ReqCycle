package org.eclipse.reqcycle.jdt.traceability.types;

import org.eclipse.reqcycle.jdt.traceability.Activator;
import org.eclipse.reqcycle.traceability.model.TType;

public class JDTType extends TType {
	String label;

	public JDTType(String label) {
		super(Activator.PLUGIN_ID + "." + label, label + " (Java)");
		this.label = label;
	}
}
