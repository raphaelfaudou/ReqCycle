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

import java.util.List;

import org.polarsys.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.uri.model.Reachable;

/**
 * The {@link IBuildingDecoration} decorates the building call back for each
 * method of {@link IBuilderCallBack} the decorator are also called The
 * transform method is called before core newUpwardRelation and allow decorators
 * to transform values given to the traceability engine
 * 
 * Take care that the mthod needsBuild does not override global mechanism.
 * Returning True or False has no impact
 * 
 * @author tfaure
 * 
 */
public interface IBuildingDecoration {

	/**
	 * The given {@link Composite} can be transformed and will be used for build
	 * 
	 * @param c
	 */
	void transform(Composite c);

	void startBuild(IBuilderCallBack callBack, Reachable reachable);

	void endBuild(IBuilderCallBack callBack, Reachable reachable);

	void errorOccurs(IBuilderCallBack callBack, Reachable reachable, Throwable t);

	/**
	 * Notify of traceability creation
	 * 
	 * @param callback
	 * @param resource
	 * @param source
	 * @param targets
	 * @param kind
	 * @return true if the original traceability link shall be created
	 */
	boolean newUpwardRelation(IBuilderCallBack callback,
			Object traceabilityObject, Object resource, Object source,
			List<? extends Object> targets, TType kind);

	public static class Composite implements Cloneable {
		public Object traceabilityObject;
		public Object resource;
		public Object source;
		public TType kind;
		public List<? extends Object> targets;

		@Override
		public Object clone() throws CloneNotSupportedException {
			Composite c = new Composite();
			c.source = this.source;
			c.targets = this.targets;
			c.resource = this.resource;
			c.kind = this.kind;
			return c;
		}

	}

	public static class IBuildingDecorationAdapter implements
			IBuildingDecoration {

		@Override
		public boolean newUpwardRelation(IBuilderCallBack callback,
				Object traceabilityObject, Object resource, Object source,
				List<? extends Object> targets, TType kind) {
			return true;
		}

		@Override
		public void transform(Composite c) {
		}

		public void startBuild(IBuilderCallBack callBack, Reachable reachable) {
		}

		public void endBuild(IBuilderCallBack callBack, Reachable reachable) {
		}

		@Override
		public void errorOccurs(IBuilderCallBack callBack, Reachable reachable,
				Throwable t) {
		}

	}
}
