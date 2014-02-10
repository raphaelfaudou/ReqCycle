/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Philippe Roland (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.commands.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Status;
import org.polarsys.reqcycle.commands.Activator;
import org.polarsys.reqcycle.commands.CreateRelationCommand;
import org.polarsys.reqcycle.traceability.types.ITypesConfigurationProvider;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Configuration;
import org.polarsys.reqcycle.traceability.types.configuration.typeconfiguration.Relation;
import org.polarsys.reqcycle.types.ITypesManager;
import org.polarsys.reqcycle.uri.model.Reachable;
import org.polarsys.reqcycle.utils.inject.ZigguratInject;

public class RelationCommandUtils
{

    @Inject
    static ITypesConfigurationProvider typeConfigProvider = ZigguratInject.make(ITypesConfigurationProvider.class);

    @Inject
    static ITypesManager typeManager = ZigguratInject.make(ITypesManager.class);

    public static Map<RelationCreationDescriptor, CreateRelationCommand> getAllRelationCommands(List<Reachable> sourceReachables, List<Reachable> targetReachables, IResource resource)
    {

        Map<RelationCreationDescriptor, CreateRelationCommand> map = new HashMap<RelationCreationDescriptor, CreateRelationCommand>();
        Configuration defaultConfiguration = typeConfigProvider.getContainer().getDefaultConfiguration();
        if(defaultConfiguration != null) {
            for (Reachable sourceReachable : sourceReachables)
            {
                for (Reachable targetReachable : targetReachables)
                {
                    map.putAll(getMatches(sourceReachable, targetReachable, defaultConfiguration, resource));
                }
            }
        }
        else{
            Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, "No default Reqcycle configuration was found"));
        }

        return map;
    }

    protected static Map<RelationCreationDescriptor, CreateRelationCommand> getMatches(Reachable sourceReachable, Reachable targetReachable, Configuration configuration, IResource resource)
    {
        Map<RelationCreationDescriptor, CreateRelationCommand> map = new HashMap<RelationCreationDescriptor, CreateRelationCommand>();

                for (Relation relation : configuration.getRelations())
                {
                    Iterable<RelationCreationDescriptor> descs = match(relation, sourceReachable, targetReachable);
                    if (descs != null)
                    {
                        for(RelationCreationDescriptor desc : descs){
                            CreateRelationCommand command = new CreateRelationCommand(relation, desc.getSource(), desc.getTarget(), resource);
                            ZigguratInject.inject(command);
                            map.put(desc, command);
                        }
                    }
                    
                }
        return map;

    }

    protected static Iterable<RelationCreationDescriptor> match(Relation r,  Reachable sourceReachable, Reachable targetReachable)
    {
        if(r.getUpstreamType()==null || r.getDownstreamType()==null) {
            return null;
        }
        boolean match = false;
        int type = 0;
        if (r.getUpstreamType().getIType().is(sourceReachable) && r.getDownstreamType().getIType().is(targetReachable))
        {
            match = true;
            type = RelationCreationDescriptor.UPSTREAM_TO_DOWNSTREAM;
        }
        if (r.getUpstreamType().getIType().is(targetReachable) && r.getDownstreamType().getIType().is(sourceReachable))
        {
            if(type == RelationCreationDescriptor.UPSTREAM_TO_DOWNSTREAM) {
                type = RelationCreationDescriptor.BOTH;
            }
            else{
                type = RelationCreationDescriptor.DOWNSTREAM_TO_UPSTREAM; 
            }
            match = true;
        }

        if (match)
        {
            List<RelationCreationDescriptor> descs = new ArrayList<RelationCreationDescriptor>(2);
            if (type == RelationCreationDescriptor.BOTH || type == RelationCreationDescriptor.DOWNSTREAM_TO_UPSTREAM){
                descs.add(new RelationCreationDescriptor(RelationCreationDescriptor.DOWNSTREAM_TO_UPSTREAM, r, sourceReachable, targetReachable));
            }
            if (type == RelationCreationDescriptor.BOTH || type == RelationCreationDescriptor.UPSTREAM_TO_DOWNSTREAM){
                descs.add(new RelationCreationDescriptor(RelationCreationDescriptor.UPSTREAM_TO_DOWNSTREAM, r, targetReachable, sourceReachable));
            }
            return descs;
        }
        else
        {
            return null;
        }
    }

}
