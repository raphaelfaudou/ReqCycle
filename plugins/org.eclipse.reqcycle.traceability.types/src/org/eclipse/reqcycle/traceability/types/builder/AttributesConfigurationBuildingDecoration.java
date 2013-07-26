package org.eclipse.reqcycle.traceability.types.builder;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.reqcycle.traceability.builder.IBuildingDecoration.IBuildingDecorationAdapter;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.eclipse.reqcycle.traceability.types.RelationBasedType;
import org.eclipse.reqcycle.traceability.types.RelationUtils;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;

public class AttributesConfigurationBuildingDecoration extends
		IBuildingDecorationAdapter {

	@Inject
	ITypesConfigurationProvider provider;
	@Inject
	IReachableManager manager;

	@Override
	public boolean newUpwardRelation(IBuilderCallBack callBack,
			Object resource, Object source, List<? extends Object> targets,
			TType kind) {
		if (kind instanceof RelationBasedType) {
			// prevent recursive call
			return true;
		}
		Configuration configuration = provider.getDefaultConfiguration();
		// in case of no configuration registered the elements are not modified
		if (configuration == null) {
			return true;
		}
		Reachable reachableSource = getReachable(source);
		boolean newOne = true;
		for (Object t : targets) {
			Reachable reachableTarget = getReachable(t);
			Iterable<Relation> relations = RelationUtils
					.getAgregatingRelations(kind, configuration,
							reachableSource, reachableTarget, DIRECTION.UPWARD);
			for (Relation r : relations) {
				newOne = false;
				RelationBasedType relBasedType = new RelationBasedType(r, kind);
				callBack.newUpwardRelation(resource, source, targets,
						relBasedType);
			}
		}
		return newOne;

	}

	private Reachable getReachable(Object object) {
		IObjectHandler handler;
		try {
			handler = manager.getHandlerFromObject(object);
			return handler.getFromObject(object).getReachable(object);
		} catch (IReachableHandlerException e) {
		}
		return null;
	}

}
