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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.PluginTransferData;

@Singleton
public class DragRequirementSourceAdapter extends DragSourceAdapter
{

    private ISelectionProvider provider;

    private final static String PLUGIN_TRANSFER_ACTION_ID = "org.eclipse.reqcycle.dnd.DropRequirementDelegate";

    /** IObjectHandler */
    @Inject
    IObjectHandler objectHandler;

    public DragRequirementSourceAdapter(ISelectionProvider provider)
    {
        super();
        this.provider = provider;
    }

    public void dragSetData(DragSourceEvent event)
    {
        IStructuredSelection selection = (IStructuredSelection) provider.getSelection();
        Iterator< ? > iterator = selection.iterator();

        if (PluginTransfer.getInstance().isSupportedType(event.dataType))
        {
            try
            {
                List<Reachable> objectURIs = new ArrayList<Reachable>();
                while (iterator.hasNext())
                {
                    Object next = iterator.next();
                    if (objectHandler.handlesObject(next))
                    {
                        ReachableObject fromObject = objectHandler.getFromObject(next);
                        objectURIs.add(fromObject.getReachable(next));
                    }
                    else
                    {
                        // if one object is not handled, do not transfer any
                        return;
                    }
                }
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream buffered;
                buffered = new ObjectOutputStream(bos);
                buffered.writeObject(objectURIs.toArray());
                byte[] data = bos.toByteArray();
                bos.close();
                buffered.close();

                event.data = new PluginTransferData(PLUGIN_TRANSFER_ACTION_ID, data);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
