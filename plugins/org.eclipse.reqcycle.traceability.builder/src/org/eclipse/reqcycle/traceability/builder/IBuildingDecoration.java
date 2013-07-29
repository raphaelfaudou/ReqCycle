package org.eclipse.reqcycle.traceability.builder;

import java.util.List;

import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.model.Reachable;

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
	boolean newUpwardRelation(IBuilderCallBack callback, Object resource,
			Object source, List<? extends Object> targets, TType kind);

	public static class Composite implements Cloneable {
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
		public boolean newUpwardRelation(IBuilderCallBack callBack,
				Object resource, Object source, List<? extends Object> targets,
				TType label) {
			return true;
		}

		@Override
		public void transform(Composite c) {
		}

		public void startBuild(IBuilderCallBack callBack, Reachable reachable) {
		}

		public void endBuild(IBuilderCallBack callBack, Reachable reachable) {
		}

	}
}
