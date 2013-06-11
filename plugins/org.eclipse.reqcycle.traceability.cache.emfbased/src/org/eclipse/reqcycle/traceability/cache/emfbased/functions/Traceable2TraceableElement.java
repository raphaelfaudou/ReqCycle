package org.eclipse.reqcycle.traceability.cache.emfbased.functions;

import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.CacheTracabilityFactory;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Model;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.Property;
import org.eclipse.reqcycle.traceability.cache.emfbased.model.CacheTracability.TraceableElement;
import org.eclipse.reqcycle.traceability.cache.emfbased.predicates.TraceableEqualsToTraceableElement;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class Traceable2TraceableElement implements
		Function<Reachable, TraceableElement> {

	private Model model;

	public Traceable2TraceableElement(Model model) {
		this.model = model;
	}

	@Override
	public TraceableElement apply(Reachable arg0) {
		TraceableElement eleme = Iterables.find(model.getTraceables(),
				new TraceableEqualsToTraceableElement(arg0), null);
		if (eleme == null) {
			eleme = CacheTracabilityFactory.eINSTANCE.createTraceableElement();
			eleme.setUri(arg0.getURI().toString());
			for (String s : arg0.extraKeys()) {
				Property p = CacheTracabilityFactory.eINSTANCE.createProperty();
				p.setName(s);
				p.setValue(arg0.get(s));
				eleme.getProperties().add(p);
			}
			model.getTraceables().add(eleme);
		}
		return eleme;
	}

}
