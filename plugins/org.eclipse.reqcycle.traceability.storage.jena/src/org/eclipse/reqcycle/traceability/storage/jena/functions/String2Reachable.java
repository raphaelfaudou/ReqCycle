package org.eclipse.reqcycle.traceability.storage.jena.functions;

import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public class String2Reachable implements Function<String, Reachable> {
	private Model model;

	public String2Reachable(Model model) {
		this.model = model;
	}

	public Reachable apply(String uri) {
		Resource res = model.getResource(uri);
		return JenaFunctions.newResource2Reachable().apply(res);
	}
}
