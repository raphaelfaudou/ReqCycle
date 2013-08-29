/*******************************************************************************
 * Copyright (c) 2012 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.traceability.table.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.engine.Request;
import org.eclipse.reqcycle.traceability.engine.Request.DEPTH;
import org.eclipse.reqcycle.traceability.exceptions.EngineException;
import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.reqcycle.traceability.model.Pair;
import org.eclipse.reqcycle.traceability.model.scopes.CompositeScope;
import org.eclipse.reqcycle.traceability.model.scopes.Scopes;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.table.providers.TraceabilityLazyContentProvider;
import org.eclipse.reqcycle.traceability.types.scopes.ConfigurationScope;
import org.eclipse.reqcycle.uri.model.Reachable;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class TableController {

	private static final String RDF_FILE = "./.traceability.rdf"; //$NON-NLS-1$

	@Inject
	@Named("RDF")
	protected IStorageProvider provider;

	@Inject
	protected ITraceabilityEngine engine;

	protected TableViewer viewer;

	protected Callable<Iterable<?>> callable;

	public TableController(TableViewer viewer) {
		this.viewer = viewer;
	}

	public void displayAllLinks() {
		callable = new Callable<Iterable<?>>() {

			@Override
			public Iterable<?> call() throws Exception {
				return getLinksFromEngine();
			}
		};
		refreshViewerData();
	}

	public void displayExplicitLinks(final IProject project) {
		callable = new Callable<Iterable<?>>() {

			@Override
			public Iterable<?> call() throws Exception {
				return getLinksFromProject(project);
			}
		};
		refreshViewerData();
	}

	protected Iterable<Link> getLinksFromEngine() {
		return new Iterable<Link>() {

			@Override
			public Iterator<Link> iterator() {
				Request request = new Request();
				CompositeScope scope = new CompositeScope();
				scope.add(Scopes.getWorkspaceScope());
				scope.add(new ConfigurationScope());
				request.setScope(scope);
				request.setDepth(DEPTH.INFINITE);
				try {
					Iterator<Pair<Link, Reachable>> traceability = engine.getTraceability(request);
					return Iterators.transform(traceability, new Function<Pair<Link, Reachable>, Link>() {

						@Override
						public Link apply(Pair<Link, Reachable> arg0) {
							return arg0.getFirst();
						}
					});
				} catch (EngineException e) {
				}
				return new ArrayList<Link>().iterator();
			}
		};
	}

	protected Iterable<Link> getLinksFromProject(final IProject project) {
		IFile file = project.getFile(new Path(RDF_FILE));
		if(file != null && file.exists()) {
			// get the storage for the file path
			String uri = file.getLocationURI().getPath();
			ITraceabilityStorage storage = provider.getStorage(uri);
			Iterable<Pair<Link, Reachable>> allTraceabilityLinks = storage.getAllTraceability(DIRECTION.DOWNWARD);

			return Iterables.transform(allTraceabilityLinks, new Function<Pair<Link, Reachable>, Link>() {

				@Override
				public Link apply(Pair<Link, Reachable> arg0) {
					return new TransverseLink(arg0.getFirst(), project);
				}
			});
		}
		return Lists.newArrayList();
	}

	public void deleteTraceabilityLinks(Iterator<TransverseLink> links) {
		ITraceabilityStorage storage = null;
		IProject project = null;
		try {
			while(links.hasNext()) {
				TransverseLink link = links.next();
				if(storage == null) {
					project = link.getProject();
					storage = getStorage(project, provider);
					storage.startTransaction();
				}
				Reachable source = Iterables.get(link.getSources(), 0);
				Reachable target = Iterables.get(link.getTargets(), 0);
				if(storage != null)
					storage.removeUpwardRelationShip(link.getKind(), null, target, source);
			}
			if(storage != null) {
				storage.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			storage.rollback();
		} finally {
			storage.save();
			refreshViewerData();
			IFile file = project.getFile(new Path(RDF_FILE));
			try {
				file.refreshLocal(0, new NullProgressMonitor());
			} catch (CoreException e) {
				//DO NOTHING
			}
		}
	}

	protected static ITraceabilityStorage getStorage(IProject project, IStorageProvider provider) {
		IFile file = project.getFile(new Path(RDF_FILE));
		if(file != null && file.exists()) {
			String uri = file.getLocationURI().getPath();
			return provider.getStorage(uri);
		}
		return null;
	}


	/**
	 * Refreshes the viewer's data.
	 */
	public void refreshViewerData() {
		Predicate<Object> filter = new Predicate<Object>() {

			@Override
			public boolean apply(Object arg0) {
				ViewerFilter[] filters = TableController.this.viewer.getFilters();
				for(int i = 0; i < filters.length; i++) {
					ViewerFilter filter = filters[i];
					if(!filter.select(viewer, null, arg0)) {
						return false;
					}
				}
				return true;
			}
		};
		Iterable<?> input;
		try {
			input = callable.call();
			Iterable<?> filtered = Iterables.filter(input, filter);
			viewer.setItemCount(Iterables.size(filtered));
			viewer.setInput(filtered);
			viewer.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshViewerVisuals() {
		TraceabilityLazyContentProvider<?> contentProvider = (TraceabilityLazyContentProvider<?>)viewer.getContentProvider();
		viewer.setItemCount(Iterables.size((Iterable<?>)viewer.getInput()));
		contentProvider.clearCache();
		viewer.refresh();
	}


}
