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

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
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


	public TableController(TableViewer viewer) {
		this.viewer = viewer;
	}

	public void displayAllLinks() {
		Iterable<Link> linksFromEngine = getLinksFromEngine();
		setViewerInput(linksFromEngine);
	}
	
	public void displayExplicitLinks(IProject project){
		Iterable<Link> linksFromEngine = getLinksFromProject(project);
		setViewerInput(linksFromEngine);
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

	protected Iterable<Link> getLinksFromProject(IProject project) {
		IFile file = project.getFile(new Path(RDF_FILE));
		if(file != null && file.exists()) {
			// get the storage for the file path
			String uri = file.getLocationURI().getPath();
			ITraceabilityStorage storage = provider.getStorage(uri);
			Iterable<Pair<Link, Reachable>> allTraceabilityLinks = storage.getAllTraceability(DIRECTION.DOWNWARD);

			return Iterables.transform(allTraceabilityLinks, new Function<Pair<Link, Reachable>, Link>() {

				@Override
				public Link apply(Pair<Link, Reachable> arg0) {
					return arg0.getFirst();
				}
			});
		}
		return Lists.newArrayList();
	}

	public void deleteTraceabilityLink(IProject project, Link link) {
		ITraceabilityStorage storage = getStorage(project, provider);
		storage.startTransaction();
		try {
			Reachable source = Iterables.get(link.getSources(), 0);
			Reachable target = Iterables.get(link.getTargets(), 0);
			storage.removeUpwardRelationShip(link.getKind(), null, source, target);
			storage.commit();
		} catch (Exception e) {
			e.printStackTrace();
			storage.rollback();
		} finally {
			storage.save();
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
	 * Filters and sets the viewer's input.
	 * @param input
	 */
	protected void setViewerInput(Iterable<?> input){
		Predicate<Object> filter = new Predicate<Object>(){
			@Override
			public boolean apply(Object arg0) {
				ViewerFilter[] filters = TableController.this.viewer.getFilters();
				for(int i = 0 ; i < filters.length ; i ++ ){
					ViewerFilter filter = filters[i];
					if (!filter.select(viewer, null, arg0)){
						return false; 
					}
				}
				return true;
			}
		};
		Iterable<?> filtered = Iterables.filter(input, filter);
		viewer.setInput(filtered);
		viewer.setItemCount(Iterables.size(filtered));
		viewer.refresh();
	}

	public void refreshViewer(){
		TraceabilityLazyContentProvider<?> contentProvider = (TraceabilityLazyContentProvider<?>) viewer.getContentProvider();
		viewer.setItemCount(Iterables.size((Iterable<?>) viewer.getInput()));
		contentProvider.clearCache();
		viewer.refresh();
	}
	
}
