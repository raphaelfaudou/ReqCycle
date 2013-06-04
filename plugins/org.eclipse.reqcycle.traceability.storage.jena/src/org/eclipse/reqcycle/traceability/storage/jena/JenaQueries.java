package org.eclipse.reqcycle.traceability.storage.jena;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.core.runtime.Platform;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.hp.hpl.jena.n3.IRIResolver;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaQueries {
	public static boolean isDebug = Activator.getDefault().isDebugging()
			&& "true".equalsIgnoreCase(Platform
					.getDebugOption(Activator.DEBUG_OPTION));
	public static String TRAC = "traceability";
	public static String VAR_TRAC = "?" + TRAC;
	public static String ORPHAN = "orphan";
	public static String VAR_ORPHANS = "?" + ORPHAN;
	public static String PROPERTY = "property";
	public static String VAR_PROPERTY = "?" + PROPERTY;

	public static String queryGetTraceabilityLink = "SELECT " + VAR_TRAC + " "
			+ "WHERE'{' " + "<{0}> " + "<" + JenaConstants.SEM_CHILDREN + ">"
			+ " " + VAR_TRAC + " . " + VAR_TRAC + "<"
			+ JenaConstants.TRAC_SOURCE + ">" + " " + " <{1}> " + " . "
			+ VAR_TRAC + " " + "<" + JenaConstants.TRAC_TARGET + ">"
			+ " <{2}> ." + " '}'";

	public static ResultSet getTraceabilityLinksFromContainer(Model model,
			Resource container, Resource source, Resource target, Property kind) {
		String queryString = MessageFormat.format(queryGetTraceabilityLink,
				container.toString(), source.toString(), target.toString());
		Query query = getQuery(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			return results;
		} finally {
			qexec.close();
		}

	}

	public static String queryGetTraceabilityLinkInContainer = "SELECT "
			+ VAR_TRAC + " " + "WHERE'{' " + "<{0}> " + "<"
			+ JenaConstants.SEM_CHILDREN + ">" + " " + VAR_TRAC + " . "
			+ " '}'";

	/**
	 * Returns all the Traceability Element contained in a Resource
	 * 
	 * @param model
	 * @param resource
	 * @return
	 */
	public static Iterable<Resource> allTraceability(Model model,
			Resource resource) {
		String queryString = MessageFormat.format(
				queryGetTraceabilityLinkInContainer, resource.toString());
		Query query = getQuery(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			ArrayList<Resource> result = Lists.newArrayList(Iterators
					.transform(results,
							new Function<QuerySolution, Resource>() {
								public Resource apply(QuerySolution query) {
									return query.getResource(VAR_TRAC);
								}
							}));
			return result;
		} finally {
			qexec.close();
		}
	}

	public static Query getQuery(String queryString) {
		Query q = new Query();
		// problems with files URIs in rdf
		// TODO check if better solution is possible
		q.setResolver(new IRIResolver() {

			@Override
			public String resolve(String relURI) {
				return relURI;
			}

		});
		Query query = QueryFactory.parse(q, queryString, "", null);
		return query;
	}

	public static Object getTraceabilityLinksFromContainer(Reachable reachable) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String queryProperties = "SELECT " + VAR_PROPERTY
			+ " WHERE '{'" + "<{0}> <" + JenaConstants.SEM_PROPERTIES + "> "
			+ VAR_PROPERTY + " . " + VAR_PROPERTY + " a <"
			+ JenaConstants.TYPE_PROPERTY + "> . " + VAR_PROPERTY + "  <"
			+ JenaConstants.SEM_PROPERTIES_KEY + "> " + "\"{1}\" ." + " '}'";

	public static Resource getPropertyWithKey(Model model, Resource reachable,
			String propertyName) {
		String formattedQuery = MessageFormat.format(queryProperties,
				reachable.toString(), propertyName);
		Query query = getQuery(formattedQuery);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			// ResultSetFormatter.out(results);
			ArrayList<Resource> result = Lists.newArrayList(Iterators
					.transform(results,
							new Function<QuerySolution, Resource>() {
								public Resource apply(QuerySolution query) {
									return query.getResource(VAR_PROPERTY);
								}
							}));
			if (result.iterator().hasNext()) {
				return result.iterator().next();
			} else {
				return null;
			}
		} finally {
			qexec.close();
		}
	}

	public static String queryOrphans = MessageFormat.format("SELECT {0} "
			+ "WHERE '{'" + " {0} a <{3}> . " + "FILTER NOT EXISTS '{' "
			+ "{0} <{1}> ?x '}'  FILTER NOT EXISTS '{' " + "{0} <{2}> ?y . "
			+ "'}' '}'", VAR_ORPHANS, JenaConstants.TRAC_INCOMING,
			JenaConstants.TRAC_OUTGOING, JenaConstants.TYPE_REACHABLE);

	public static Iterable<Resource> allOrphans(Model model) {
		Query query = getQuery(queryOrphans);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
			ResultSet results = qexec.execSelect();
			// ResultSetFormatter.out(results);
			ArrayList<Resource> result = Lists.newArrayList(Iterators
					.transform(results,
							new Function<QuerySolution, Resource>() {
								public Resource apply(QuerySolution query) {
									return query.getResource(VAR_ORPHANS);
								}
							}));
			return result;
		} finally {
			qexec.close();
		}
	}

}
