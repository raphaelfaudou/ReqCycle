package org.eclipse.reqcycle.traceability.types.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.reqcycle.traceability.builder.IBuildingDecoration.IBuildingDecorationAdapter;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.engine.ITraceabilityEngine.DIRECTION;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.eclipse.reqcycle.traceability.types.RelationBasedType;
import org.eclipse.reqcycle.traceability.types.RelationUtils;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Attribute;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;

public class AttributesConfigurationBuildingDecoration extends
		IBuildingDecorationAdapter {

	@Inject
	protected ITypesConfigurationProvider provider;
	@Inject
	protected IReachableManager manager;
	@Inject
	@Named("RDF")
	protected IStorageProvider storageProvider;

	protected ITraceabilityStorage currentStorage;

	protected Set<Reachable> allTraceabilityWithProperties = new HashSet<Reachable>();

	@Override
	public void startBuild(IBuilderCallBack callBack, Reachable reachable) {
		ReachableObject object;
		try {
			object = manager.getHandlerFromReachable(reachable)
					.getFromReachable(reachable);
			if (object != null) {
				IFile adapted = (IFile) object.getAdapter(IFile.class);
				if (adapted != null && adapted.exists()) {
					IProject p = adapted.getProject();
					currentStorage = getStorage(p);
					// currentStorage.get
				}
			}
		} catch (IReachableHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ITraceabilityStorage getStorage(IProject p) {
		IFile file = p.getFile(new Path("./.t-attributes.rdf"));
		// get the storage for the file path
		String uri = file.getLocationURI().getPath();
		ITraceabilityStorage storage = storageProvider.getStorage(uri);
		return storage;
	}

	@Override
	public void endBuild(IBuilderCallBack callBack, Reachable reachable) {
		if (currentStorage != null) {
			currentStorage.save();
			currentStorage.dispose();
			currentStorage = null;
		}
	}

	@Override
	public boolean newUpwardRelation(IBuilderCallBack callBack,
			Object traceability, Object resource, Object source,
			List<? extends Object> targets, TType kind) {
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
		Reachable reachableTrac = getReachable(traceability);
		boolean newOne = true;
		for (Object t : targets) {
			Reachable reachableTarget = getReachable(t);
			Iterable<Relation> relations = RelationUtils
					.getAgregatingRelations(kind, configuration,
							reachableSource, reachableTarget, DIRECTION.UPWARD);
			for (Relation r : relations) {
				newOne = false;
				RelationBasedType relBasedType = new RelationBasedType(r, kind);
				callBack.newUpwardRelation(traceability, resource, source,
						targets, relBasedType);
				if (currentStorage != null) {
					for (Attribute a : r.getAttributes()) {
						if (currentStorage.getProperty(reachableTrac,
								a.getName()) == null) {
							currentStorage.addUpdateProperty(reachableTrac,
									a.getName(), null);
						}
					}
				}
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
