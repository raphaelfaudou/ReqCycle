/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Tristan Faure (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.traceability.builder.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.polarsys.reqcycle.traceability.builder.ITraceabilityAnalyserDisabler;
import org.polarsys.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.polarsys.reqcycle.traceability.builder.LabelledVisitor;
import org.polarsys.reqcycle.uri.visitors.CompositeVisitor;
import org.polarsys.reqcycle.uri.visitors.IVisitor;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import static com.google.common.collect.Iterables.filter;

public class TraceabilityVisitor extends CompositeVisitor implements IAdaptable {

	// the {@link ITraceabilityAnalyserDisabler} is used at cosntruction so
	// @Inject can not be used
	// the default implementation is not provided so this attribute can be NULL
	private static ITraceabilityAnalyserDisabler disabler = ZigguratInject
			.make(ITraceabilityAnalyserDisabler.class);
	private IBuilderCallBack callBack;

	public TraceabilityVisitor(IBuilderCallBack callBack) {
		super(getTraceabilityVisitors());
		this.callBack = callBack;
	}

	private static Collection<IVisitor> getTraceabilityVisitors() {
		Iterable<IVisitor> tmp = filter(
				LabelledVisitor.getRegisteredVisitors(), IVisitor.class);
		return Lists.newArrayList(filter(tmp, new Predicate<IVisitor>() {

			@Override
			public boolean apply(IVisitor arg0) {
				Class<? extends IVisitor> classToCheck = arg0.getClass();
				if (arg0 instanceof LabelledVisitor) {
					classToCheck = ((LabelledVisitor) arg0).getVisitorClass();
				}
				return disabler == null || !disabler.isDisabled(classToCheck);
			}
		}));
	}

	@Override
	public boolean visit(Object o, IAdaptable adapable) {
		// redirect the adapt to this to manage IBuilderCallback
		return super.visit(o, this);
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (IBuilderCallBack.class == adapter) {
			return callBack;
		}
		return null;
	}

}
