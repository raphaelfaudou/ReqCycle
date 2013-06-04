package org.eclipse.reqcycle.traceability.storage.jena;

import com.hp.hpl.jena.rdf.model.Model;

public class JenaModel {
	Model model = null;

	public JenaModel(Model model) {
		this.model = model;
	}

}
