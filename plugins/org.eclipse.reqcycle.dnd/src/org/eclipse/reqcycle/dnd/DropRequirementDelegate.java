/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Philippe ROLAND (AtoS) philippe.roland@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.dnd;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.agesys.inject.AgesysInject;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.reqcycle.commands.CreateRelationCommand;
import org.eclipse.reqcycle.commands.utils.RelationCommandUtils;
import org.eclipse.reqcycle.commands.utils.RelationCreationDescriptor;
import org.eclipse.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.eclipse.reqcycle.types.ITypesManager;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.part.IDropActionDelegate;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class DropRequirementDelegate implements IDropActionDelegate {

	@Inject
	IReachableCreator creator = AgesysInject.make(IReachableCreator.class);

	@Inject
	IReachableManager manager = AgesysInject.make(IReachableManager.class);

	@Inject
	ITypesManager typesManager = AgesysInject.make(ITypesManager.class);

	@Inject
	IObjectHandler objectHandler = AgesysInject.make(IObjectHandler.class);

	ITypesConfigurationProvider configManager = AgesysInject
			.make(ITypesConfigurationProvider.class);

	@Override
	public boolean run(Object source, Object target) {
		Reachable targetReachable = null;
		List<Reachable> sourceReachables = new ArrayList<Reachable>();

		if (source instanceof byte[] && target instanceof ModelElementItem) {
			EObject targetEObj = ((ModelElementItem) target).getEObject();
			IFile file = WorkspaceSynchronizer.getFile(targetEObj.eResource());
			if (file != null) {
				if (objectHandler.handlesObject(targetEObj)) {
					{
						targetReachable = objectHandler.getFromObject(
								targetEObj).getReachable(targetEObj);
					}
					byte[] data = (byte[]) source;
					List<Reachable> reachables = DNDReqCycle.getReachables(data);
					handleDrop(reachables, targetReachable, file);
				}
			}
		}
		return true;

	}

	protected void handleDrop(List<Reachable> sourceReachables,
			Reachable targetReachable, IResource res) {
		final Map<RelationCreationDescriptor, CreateRelationCommand> allCommands = RelationCommandUtils
				.getAllRelationCommands(sourceReachables,
						Collections.singletonList(targetReachable), res);
		Iterable<RelationCreationDescriptor> upstreamToDownstreams = Iterables
				.filter(allCommands.keySet(),
						new Predicate<RelationCreationDescriptor>() {
							public boolean apply(RelationCreationDescriptor desc) {
								return desc.isUpstreamToDownstream();
							}
						});
		Iterable<RelationCreationDescriptor> downstreamToUpstream = Iterables
				.filter(allCommands.keySet(),
						new Predicate<RelationCreationDescriptor>() {
							public boolean apply(RelationCreationDescriptor desc) {
								return desc.isDownstreamToUpstream();
							}
						});
		Menu menu = new Menu(Display.getDefault().getActiveShell());
		Iterator<RelationCreationDescriptor> iteratorUD = upstreamToDownstreams
				.iterator();
		if (iteratorUD.hasNext()) {
			createMenu(menu, "Upstream To Downstream", iteratorUD, allCommands);
		}
		Iterator<RelationCreationDescriptor> iteratorDU = downstreamToUpstream
				.iterator();
		if (iteratorDU.hasNext()) {
			createMenu(menu, "Downstream To Upstream", iteratorDU, allCommands);
		}
		menu.setVisible(true);

	}

	private void createMenu(Menu menu, String string,
			Iterator<RelationCreationDescriptor> iteratorUD,
			Map<RelationCreationDescriptor, CreateRelationCommand> allCommands) {
		MenuItem newItem = new MenuItem(menu, SWT.CASCADE);
		Menu newMenu = new Menu(menu);
		newItem.setMenu(newMenu);
		newItem.setText(string);
		for (; iteratorUD.hasNext();) {
			RelationCreationDescriptor desc = iteratorUD.next();
			MenuItem item = new MenuItem(newMenu, SWT.NONE);
			final CreateRelationCommand command = allCommands.get(desc);
			item.setText(desc.getLabel());
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					command.execute();
				}

			});
		}
	}
}
