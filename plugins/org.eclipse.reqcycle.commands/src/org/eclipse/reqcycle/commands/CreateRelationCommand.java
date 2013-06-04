package org.eclipse.reqcycle.commands;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.traceability.model.TraceabilityLink;
import org.eclipse.reqcycle.traceability.storage.IStorageProvider;
import org.eclipse.reqcycle.traceability.storage.ITraceabilityStorage;
import org.eclipse.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.Reachable;

public class CreateRelationCommand implements Command {
	@Inject
	@Named("RDF")
	IStorageProvider provider;
	
	@Inject
	IReachableManager manager;

	@Inject
	IReachableCreator creator;

	private Relation relation;

	private Reachable source;

	private Reachable target;

	private IResource res;

	public CreateRelationCommand(Relation relation, Reachable source,
			Reachable target, IResource res) {
		this.relation = relation;
		this.source = source;
		this.target = target;
		this.res = res;
	}

	public void execute() {
		redo();

	}

	public void redo() {
		// handle project traceability path
		IProject p = res.getProject();
		IFile file = p.getFile(new Path("./.traceability.rdf"));

		// get the storage for the file path
		String uri = file.getLocationURI().getPath();
		ITraceabilityStorage storage = provider.getStorage(uri);
		try {
		    Reachable container = manager.getHandlerFromObject(file).getFromObject(file).getReachable(file);
			storage.startTransaction();
			storage.newUpwardRelationShip(TType.get(TraceabilityLink
					.valueOf(relation.getKind().toUpperCase())), container, source,
					new Reachable[] { target });
			storage.commit();
			storage.save();
		} catch (RuntimeException e) {
			storage.rollback();
		}
        catch (IReachableHandlerException e)
        {
            e.printStackTrace();
        } finally {
			storage.dispose();
		}
		try {
			file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
			if (file.exists()) {
				file.setHidden(false);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

}
