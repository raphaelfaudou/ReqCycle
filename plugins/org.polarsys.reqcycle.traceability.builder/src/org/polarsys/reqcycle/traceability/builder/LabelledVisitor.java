/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder;

import java.util.ArrayDeque;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.polarsys.reqcycle.uri.visitors.IVisitor;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;
import org.osgi.framework.FrameworkUtil;

public class LabelledVisitor implements IVisitor {

	private String label;
	private String description;
	private IVisitor delegate;
	private static String EXT_POINT = "traceabilityAnalyser";
	private static String LABEL = "label";
	private static String DESCRIPTION = "description";
	private static Collection<LabelledVisitor> visitors = null;

	public static Collection<LabelledVisitor> getRegisteredVisitors() {
		if (visitors == null) {
			visitors = new ArrayDeque<LabelledVisitor>();
			for (IConfigurationElement e : Platform
					.getExtensionRegistry()
					.getConfigurationElementsFor(Activator.PLUGIN_ID, EXT_POINT)) {
				try {
					IVisitor v = (IVisitor) e
							.createExecutableExtension("visitor");
					ZigguratInject.inject(v);
					String label = e.getAttribute(LABEL);
					if (label == null || label.length() == 0) {
						label = v.getClass().getName();
					}
					String description = e.getAttribute(DESCRIPTION);
					if (description == null) {
						description = " analyser registered in bundle : "
								+ FrameworkUtil.getBundle(v.getClass())
										.getSymbolicName();
					}
					LabelledVisitor labelled = new LabelledVisitor(label,
							description, v);
					visitors.add(labelled);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}
		}
		return visitors;
	}

	public LabelledVisitor(String label, String description, IVisitor delegate) {
		this.label = label;
		this.description = description;
		this.delegate = delegate;
	}

	@Override
	public void start(IAdaptable adaptable) {
		delegate.start(adaptable);
	}

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		return delegate.visit(o, adaptable);
	}

	@Override
	public void end(IAdaptable adaptable) {
		delegate.end(adaptable);
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

	public Class<? extends IVisitor> getVisitorClass() {
		return delegate.getClass();
	}
}
