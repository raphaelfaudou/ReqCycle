package org.eclipse.reqcycle.traceability.storage.jena.functions;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.storage.jena.JenaConstants;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class Resource2Reachable implements Function<Resource, Reachable> {
	@Inject
	IReachableCreator creator;

	public Reachable apply(Resource res) {
		Reachable result = null;
		Model model = res.getModel();
		if (model.contains(res, null, (RDFNode) null)) {
			try {
				result = getReachable(new URI(res.getURI()));
				StmtIterator properties = res.listProperties(model
						.createProperty(JenaConstants.SEM_PROPERTIES));
				Property pKey = model
						.createProperty(JenaConstants.SEM_PROPERTIES_KEY);
				Property pValue = model
						.createProperty(JenaConstants.SEM_PROPERTIES_VALUE);
				while (properties.hasNext()) {
					Statement s = properties.next();
					Resource resourceForCurrentProperty = s.getObject()
							.asResource();
					Statement keyStmt = resourceForCurrentProperty
							.getProperty(pKey);
					Statement valueStmt = resourceForCurrentProperty
							.getProperty(pValue);
					if (keyStmt != null && valueStmt != null) {
						result.put(keyStmt.getObject().asLiteral().getString(),
								valueStmt.getObject().asLiteral().getString());
					}

				}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Reachable getReachable(URI uri) {
		Reachable t = new Reachable();
		t.setFragment(uri.getFragment());
		t.setScheme(uri.getScheme());
		t.setSchemeSpecificPart(uri.getSchemeSpecificPart());
		t.setAuthority(uri.getAuthority());
		t.setUserInfo(uri.getUserInfo());
		t.setHost(uri.getHost());
		t.setPath(uri.getPath());
		t.setQuery(uri.getQuery());
		t.setFragment(uri.getFragment());
		t.setPort(uri.getPort());
		return t;
	}
}
