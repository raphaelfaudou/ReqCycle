package org.eclipse.reqcycle.traceability.storage.jena;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.storage.jena.functions.JenaFunctions;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFReader;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

@Singleton
public class JenaStorage implements ITraceabilityStorage {

	private Model model;
	private String path;
	@Inject
	IReachableCreator creator;
	private ModelMaker modelMaker;
	private File file;

	public JenaStorage(String path) {
		this.path = path;
		file = new File(path);
		file.mkdirs();
		modelMaker = ModelFactory.createFileModelMaker(file.getParentFile()
				.getAbsolutePath());
		String fileName = file.getName();
		if (!fileName.contains(".rdf")) {
			fileName += ".rdf";
		}
		model = modelMaker.createModel(fileName);
		tweakModel();
	}

	public void tweakModel() {
		// writer properties http://jena.sourceforge.net/IO/iohowto.html
		RDFWriter fasterWriter = model.getWriter("RDF/XML");
		fasterWriter.setProperty("allowBadURIs", "true");
		fasterWriter.setProperty("relativeURIs", "");
		fasterWriter.setProperty("tab", "0");

		// reader properties http://jena.sourceforge.net/IO/iohowto.html
		RDFReader bigFileReader = model.getReader("RDF/XML");
		bigFileReader.setProperty("WARN_REDEFINITION_OF_ID", "EM_IGNORE");
	}

	@Override
	public void save() {
		model.commit();
		// BufferedOutputStream bos = null;
		// try {
		// bos = new BufferedOutputStream(new FileOutputStream(file));
		// model.write(bos);
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (bos != null) {
		// try {
		// bos.close();
		// } catch (IOException e) {
		// }
		// }
		// }
		// model.sa();
		// model.close();
		// model = modelMaker.openModel("cache");
	}

	@Override
	public void removeUpwardRelationShip(String kind, Reachable container,
			Reachable source, Reachable... targets) {
		Resource rContainer = model.getResource(container.toString());
		if (rContainer == null) {
			return;
		}
		Resource rSource = model.getResource(source.toString());
		if (rSource == null) {
			return;
		}
		Property rKind = getKindProperty(kind);
		if (rKind == null) {
			return;
		}

		for (Reachable t : targets) {
			Resource rTarget = model.getResource(t.toString());
			removeUpwardRelationShip(rKind, rContainer, rSource, rTarget);
		}
	}

	public void removeUpwardRelationShip(Property rKind, Resource rContainer,
			Resource rSource, Resource rTarget) {
		if (rTarget != null) {
			ResultSet allLinks = JenaQueries.getTraceabilityLinksFromContainer(
					model, rContainer, rSource, rTarget, rKind);
			for (; allLinks.hasNext();) {
				QuerySolution soln = allLinks.next();
				RDFNode node = soln.get(JenaQueries.TRAC);
			}
		}
	}

	@Override
	public void newUpwardRelationShip(TType kind, Reachable container,
			Reachable source, Reachable... targets) {
		try {
			Resource rKind = getKindProperty(kind.getSemantic());
			// container and revision
			Resource rContainer = model.createResource(container.toString());
			fillProperties(container, rContainer);
			// for each target a link is created between the source and the
			// targets
			for (Reachable t : targets) {
				// defining rSource and rTarget
				Resource propertyReachableType = model
						.createResource(JenaConstants.TYPE_REACHABLE);
				Resource rSource = model.createResource(source.toString(),
						propertyReachableType);
				fillProperties(source, rSource);
				Resource rTarget = model.createResource(t.toString(),
						propertyReachableType);
				fillProperties(t, rTarget);
				// create a traceability link (trac -> source / trac -> target)
				Resource traceabilityLink = model.createResource(model
						.createResource(JenaConstants.TYPE_TRACE));
				traceabilityLink
						.addProperty(
								model.createProperty(JenaConstants.TRAC_SOURCE),
								rSource)
						.addProperty(
								model.createProperty(JenaConstants.TRAC_TARGET),
								rTarget)
						.addProperty(
								model.createProperty(JenaConstants.TRAC_KIND),
								rKind);
				// create inverse relation ship (source -> outgoing, target ->
				// incoming)
				rSource.addProperty(
						model.createProperty(JenaConstants.TRAC_OUTGOING),
						traceabilityLink);
				rTarget.addProperty(
						model.createProperty(JenaConstants.TRAC_INCOMING),
						traceabilityLink);
				// define the link between the traceability and the container
				// and
				// vice versa
				rContainer.addProperty(
						model.createProperty(JenaConstants.SEM_CHILDREN),
						traceabilityLink);
				traceabilityLink.addProperty(
						model.createProperty(JenaConstants.SEM_CONTAINER),
						rContainer);
			}
		} finally {
			// model.commit();
			// model.notifyEvent(GraphEvents.finishRead);
		}

	}

	public Property getKindProperty(String kind) {
		return model.createProperty(JenaConstants.TRAC_KIND + "/" + kind);
	}

	public void fillProperties(Reachable r, Resource resource) {
		// a reachable is linked to Property [JenaConstants.SEM_PROPERTIES]
		// a property contains a key [JenaConstants.SEM_PROPERTIES_KEY] and a
		// value [JenaConstants.SEM_PROPERTIES_VALUE]
		Map<String, String> properties = r.getProperties();
		Property valueProperty = model
				.createProperty(JenaConstants.SEM_PROPERTIES_VALUE);
		Property keyProperty = model
				.createProperty(JenaConstants.SEM_PROPERTIES_KEY);
		Property semProperty = model
				.createProperty(JenaConstants.SEM_PROPERTIES);
		for (String s : properties.keySet()) {
			String value = properties.get(s);
			Resource rProperty = JenaQueries.getPropertyWithKey(model,
					resource, s);
			if (rProperty == null) {
				rProperty = model.createResource(model
						.createProperty(JenaConstants.TYPE_PROPERTY));
				rProperty.addLiteral(keyProperty, s);
				resource.addProperty(semProperty, rProperty);
			} else {
				model.remove(rProperty.listProperties(valueProperty));
			}
			rProperty.addLiteral(valueProperty, value);

		}

	}

	@Override
	public Reachable getReachable(String uri) {
		return JenaFunctions.newString2Reachable(model).apply(uri);
	}

	public Iterable<Pair<Link, Reachable>> doGetTraceability(Reachable r,
			Property direction,
			Function<Statement, Pair<Link, Reachable>> function) {
		Resource res = model.getResource(r.toString());
		if (res == null) {
			return Lists.newArrayList();
		}
		StmtIterator properties = res.listProperties(direction);
		return Lists
				.newArrayList(Iterators.filter(
						Iterators.transform(properties, function),
						Predicates.notNull()));

	}

	@Override
	public void dispose() {
		// save();
		model.close();
	}

	@Override
	public Iterable<Pair<Link, Reachable>> getTraceability(Reachable r,
			DIRECTION direction) {
		if (direction == DIRECTION.UPWARD) {
			return doGetTraceability(r,
					model.createProperty(JenaConstants.TRAC_OUTGOING),
					JenaFunctions.newUpwardStatement2Pair());
		} else {
			return doGetTraceability(r,
					model.createProperty(JenaConstants.TRAC_INCOMING),
					JenaFunctions.newDownwardStatement2Pair());
		}
	}

	@Override
	public void startTransaction() {
		// TAKES TOO MUCH TIME
		model.begin();
	}

	@Override
	public void commit() {
		// TAKES TOO MUCH TIME
		model.commit();
	}

	@Override
	public void rollback() {
		// TAKES TOO MUCH TIME
		model.abort();
	}

	@Override
	public void removeTraceabilityLinksContainedIn(Reachable reachable) {
		Resource resource = model.getResource(reachable.toString());
		if (resource == null) {
			return;
		}
		Iterable<Resource> traca = JenaQueries.allTraceability(model, resource);
		for (Resource r : traca) {
			// remove target from incoming
			removeTargets(r);
			// remove source from outgoing
			removeSources(r);
			// remove children from container
			removeChildren(r);
			// remove all triplets from r
			model.remove(r.listProperties());
		}
		// remove orphans
		Iterable<Resource> allOrphans = JenaQueries.allOrphans(model);
		for (Resource r : allOrphans) {
			Property pProperty = model
					.getProperty(JenaConstants.SEM_PROPERTIES);
			if (pProperty != null) {
				StmtIterator it = r.listProperties(pProperty);
				for (; it.hasNext();) {
					Statement next = it.next();
					model.remove(next.getObject().asResource().listProperties());
				}
			}
			model.remove(r.listProperties());
		}
		// finally delete container properties
		model.remove(resource.listProperties());
	}

	private void removeChildren(Resource r) {
		Property pContainer = model.getProperty(JenaConstants.SEM_CONTAINER);
		Property pChildren = model.getProperty(JenaConstants.SEM_CHILDREN);
		if (pContainer == null || pChildren == null) {
			return;
		}
		removeTraceability(r, pContainer, pChildren);

	}

	public void removeSources(Resource r) {
		Property pOutgoing = model.getProperty(JenaConstants.TRAC_OUTGOING);
		Property pSource = model.getProperty(JenaConstants.TRAC_SOURCE);
		if (pOutgoing == null || pSource == null) {
			return;
		}
		removeTraceability(r, pSource, pOutgoing);
	}

	public void removeTargets(Resource r) {
		Property pIncoming = model.getProperty(JenaConstants.TRAC_INCOMING);
		Property pTarget = model.getProperty(JenaConstants.TRAC_TARGET);
		if (pIncoming == null || pTarget == null) {
			return;
		}
		removeTraceability(r, pTarget, pIncoming);
	}

	public void removeTraceability(Resource r, Property linkFromResource,
			Property linkFromReachedElement) {
		StmtIterator linksFromResource = r.listProperties(linkFromResource);
		for (; linksFromResource.hasNext();) {
			Statement next = linksFromResource.next();
			model.remove(next.getObject().asResource(), linkFromReachedElement,
					r);
		}
	}
}
