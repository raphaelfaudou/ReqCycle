package org.polarsys.reqcycle.traceability.builder;

import java.util.List;

import org.polarsys.reqcycle.traceability.builder.exceptions.BuilderException;
import org.polarsys.reqcycle.traceability.model.TType;
import org.polarsys.reqcycle.uri.model.Reachable;

public interface ITraceabilityBuilder {
	/**
	 * Builds the {@link Reachable} to save traceability information
	 * 
	 * @param r
	 *            the reachable to build
	 * @param callBack
	 *            the callback which handles the detection of traceability link
	 * @param forceBuild
	 *            if true the build will not try to identify {@link Reachable}
	 *            modification
	 * @throws BuilderException
	 *             if an error occurs during build
	 */
	void build(Reachable r, IBuilderCallBack callBack, boolean forceBuild)
			throws BuilderException;

	public interface IBuilderCallBack {

		/**
		 * @param reachable
		 * @return
		 */
		boolean needsBuild(Reachable reachable);

		/**
		 * Notify that a build is starting for the given {@link Reachable}
		 * 
		 * @param reachable
		 * @return true if the build shall continue false otherwise
		 */
		void startBuild(Reachable reachable);

		/**
		 * Notify that a build has finished
		 * 
		 * @param reachable
		 */
		void endBuild(Reachable reachable);

		/**
		 * Notify that an error occurs during the build
		 * 
		 * @param reachable
		 */
		void errorOccurs(Reachable reachable, Throwable t);

		/**
		 * Identify a new relationship. The direction between the source and the
		 * target is an downstream to upstream When several targets are filled
		 * it is important to notice that the traceability link will be
		 * consistent only with all the targets
		 * 
		 * @param traceabilityObject
		 *            the {@link Object} identifying the relationship
		 * @param resource
		 *            the {@link Object} of the resource containing the relation
		 * @param source
		 *            the {@link Object} source of the relation
		 * @param targets
		 *            the {@link Object} targets of the relation
		 * @param label
		 *            the label identifying the relation
		 * @return the reachable corresponding to the identification of the
		 *         link, or null if the creation was not possible
		 */
		void newUpwardRelation(Object traceabilityObject, Object resource,
				Object source, List<? extends Object> targets, TType label);

	}

}
