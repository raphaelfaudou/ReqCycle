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
package org.eclipse.reqcycle.traceability.table.utils;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
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
import org.eclipse.reqcycle.traceability.table.MutableTraceabilityRow;
import org.eclipse.reqcycle.traceability.table.TraceabilityRow;
import org.eclipse.reqcycle.traceability.types.scopes.ConfigurationScope;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;


public class TableUtils {

	private static final String RDF_FILE = "./.traceability.rdf"; //$NON-NLS-1$

	public static Iterable<TraceabilityRow> createRequirementRows(final IProject project, IStorageProvider provider, final TraceabilityTableBuilder builder) {
		IFile file = project.getFile(new Path(RDF_FILE));
		if(file != null && file.exists()) {
			// get the storage for the file path
			String uri = file.getLocationURI().getPath();
			ITraceabilityStorage storage = provider.getStorage(uri);
			Iterable<Pair<Link, Reachable>> allTraceabilityLinks = storage.getAllTraceability(DIRECTION.UPWARD);

			return Iterables.transform(allTraceabilityLinks, new Function<Pair<Link, Reachable>, TraceabilityRow>() {

				@Override
				public TraceabilityRow apply(Pair<Link, Reachable> arg0) {
					TraceabilityRow requirementRow = new MutableTraceabilityRow(arg0.getFirst(), project, builder.getEventList());
					ZigguratInject.inject(requirementRow);
					return requirementRow;
				}
			});
		}
		return Lists.newArrayList();
	}
	
	public static Iterable<TraceabilityRow> createRequirementRows(ITraceabilityEngine engine){
		Request request = new Request();
		CompositeScope scope = new CompositeScope();
		scope.add(Scopes.getWorkspaceScope());
		scope.add(new ConfigurationScope());
		request.setScope(scope);
		request.setDepth(DEPTH.INFINITE);
		try {
			Iterator<Pair<Link, Reachable>> traceability = engine.getTraceability(request);
			final Iterator<TraceabilityRow> iterator = Iterators.transform(traceability, new Function<Pair<Link, Reachable>, TraceabilityRow>() {

				@Override
				public TraceabilityRow apply(Pair<Link, Reachable> arg0) {
					TraceabilityRow requirementRow = new TraceabilityRow(arg0.getFirst());
					ZigguratInject.inject(requirementRow);
					return requirementRow;
				}
			});
			return new Iterable<TraceabilityRow>(){

				@Override
				public Iterator<TraceabilityRow> iterator() {
					return iterator;
				}
				
			};
		} catch (EngineException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ITraceabilityStorage getStorage(IProject project, IStorageProvider provider){
		IFile file = project.getFile(new Path(RDF_FILE));
		if(file != null && file.exists()) {
			String uri = file.getLocationURI().getPath();
			return  provider.getStorage(uri);
		}
		return null;
	}

}
