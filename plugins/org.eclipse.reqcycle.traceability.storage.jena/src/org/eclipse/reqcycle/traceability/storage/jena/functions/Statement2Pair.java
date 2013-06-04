package org.eclipse.reqcycle.traceability.storage.jena.functions;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.storage.jena.JenaConstants;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class Statement2Pair implements
		Function<Statement, Pair<Link, Reachable>> {
	private Property nodeToSearch;

	public Statement2Pair(Property nodeToSearch) {
		this.nodeToSearch = nodeToSearch;
	}

	@Override
	public Pair<Link, Reachable> apply(Statement stmt) {
		Pair<Link, Reachable> result = null;
		Resource subject = stmt.getSubject();
		RDFNode object = stmt.getObject();
		Function<Resource, Reachable> function = JenaFunctions
				.newResource2Reachable();
		Reachable source = function.apply(subject);
		Resource traceabilityLink = object.asResource();
		StmtIterator listProperties = traceabilityLink.listProperties();
		String kind = null;
		RDFNode target = null;
		while (listProperties.hasNext() && (kind == null || target == null)) {
			Statement s = listProperties.next();
			Property predicate = s.getPredicate();
			if (predicate.getURI().contains(JenaConstants.TRAC_KIND)) {
				kind = s.getObject().asResource().getURI()
						.replace(JenaConstants.TRAC_KIND + "/", "");
			} else if (predicate.getURI().equals(
					getNodeToSearch(stmt.getModel()).getURI())) {
				target = s.getObject();
			}
		}
		if (kind != null && target != null) {
			Reachable targetReachable = function.apply(target.asResource());
			Link link = new Link(kind, source, targetReachable);
			result = new Pair<Link, Reachable>(link, targetReachable);
		}
		return result;
		// Reachable target = function.apply(object.asResource());
		// Link link = new Link(predicate.getURI().replace(
		// JenaConstants.TYPE_TRACE + "/", ""), source, target);
		// return new Pair<Link, Reachable>(link, target);
	}

	public Property getNodeToSearch(Model model) {
		return nodeToSearch;
	}

	public static class DownwardStatement2Pair extends Statement2Pair {
		public DownwardStatement2Pair() {
			super(null);
		}

		@Override
		public Property getNodeToSearch(Model model) {
			return model.createProperty(JenaConstants.TRAC_SOURCE);
		}

	}

	public static class UpwardStatement2Pair extends Statement2Pair {
		public UpwardStatement2Pair() {
			super(null);
		}

		@Override
		public Property getNodeToSearch(Model model) {
			return model.createProperty(JenaConstants.TRAC_TARGET);
		}

	}
}
