package org.eclipse.reqcycle.sesame.handlers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.reqcycle.sesame.graph.SailGraphProvider;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.visitors.IVisitable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;
import org.eclipse.ziggurat.inject.ZigguratInject;
import org.openrdf.rio.RDFWriterRegistry;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;

public class SesameVisitable implements IVisitable, IAdaptable {

	SailGraphProvider provider = null;
	private Graph graph;

	public SesameVisitable(Reachable reachable) {
		String path = reachable.getPath();
		if ("platform".equals(reachable.getScheme())) {
			IFile f = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(path));
			if (f.exists()) {
				path = f.getLocationURI().getPath();
			}
		}
		provider = new SailGraphProvider();
		ZigguratInject.inject(provider);
		graph = provider.getGraph(path, RDFWriterRegistry.getInstance()
				.getFileFormatForFileName(path));

	}

	@Override
	public void accept(IVisitor visitor) {
		for (Edge e : graph.getEdges()) {
			visitor.visit(e, this);
		}
	}

	@Override
	public void dispose() {
		graph.shutdown();
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}
