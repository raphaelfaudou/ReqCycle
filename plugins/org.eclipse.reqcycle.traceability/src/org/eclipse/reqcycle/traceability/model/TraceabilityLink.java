package org.eclipse.reqcycle.traceability.model;

public enum TraceabilityLink {
	TRACE("Trace"), SATISFY("Satisfy"), COPY("Copy"), DERIVE("Derive"), REFINE(
			"Refine"), VERIFY("Verify");
	private String label;

	TraceabilityLink(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
