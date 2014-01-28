/*******************************************************************************
 * Copyright (c) 2014 Samares Engineering.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Samares Engineering - initial API and implementation
 ******************************************************************************/
package org.eclipse.reqcycle.xcos.traceability.visitor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import javax.inject.Inject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.IReachableCreator;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.visitors.IVisitor;
import org.eclipse.reqcycle.xcos.model.XcosElement;
import org.eclipse.reqcycle.xcos.model.XcosTrace;
import org.eclipse.reqcycle.xcos.traceability.types.XcosTTypeProvider;





/**
 * @author Raphael Faudou
 * this class visits interesting elements that are Xcos reachable objects
 *
 */
public class XcosTraceabilityVisitor implements IVisitor {
	
	@Inject
	IReachableCreator creator;


	@Override
	public void start(IAdaptable adaptable) {
	}

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		IBuilderCallBack callBack = (IBuilderCallBack)adaptable.getAdapter(IBuilderCallBack.class);
		if(o instanceof XcosElement) {
			XcosElement xce = (XcosElement)o;
			if( xce instanceof XcosTrace){
				XcosTrace trace = (XcosTrace) xce;
				// do the job: 
				//  analyse trace and add or update the link in ReqCycle traceability repository
				
				computeTraceability(trace,callBack);
			}
			
			
			
		}
		// continue to visit
		return true;
	}

	@Override
	public void end(IAdaptable adaptable) {
	}
	


	/**
	 * Retrieves the right reference to trace links from the object, executes it,
	 * and serialize the links.
	 * 
	 * @param callBack
	 */
	private  void computeTraceability(XcosTrace link, IBuilderCallBack callBack) {
		
		//Downward relation from "from" to "result" == upward relation from "result" to "from";
		//UUID uniqueID = UUID.randomUUID();
		TType tType =  XcosTTypeProvider.get(link.getSemantics());
		URI uri;
		Reachable target = null;
		try {
			uri = new URI(link.getRef());
			 target = creator.getReachable(uri);
			 
		
			
			
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (target != null) {
		
			callBack.newUpwardRelation(link,  link.getResource(), link.getSource(), Collections.singletonList(target), tType);
		}
	}

	


}
