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

import java.util.Collections;
import java.util.UUID;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.reqcycle.traceability.builder.ITraceabilityBuilder.IBuilderCallBack;
import org.eclipse.reqcycle.traceability.model.TType;
import org.eclipse.reqcycle.uri.visitors.IVisitor;
import org.eclipse.reqcycle.xcos.model.XcosElement;
import org.eclipse.reqcycle.xcos.traceability.types.XcosTTypeProvider;



public class XcosTraceabilityVisitor implements IVisitor {


	@Override
	public void start(IAdaptable adaptable) {
	}

	@Override
	public boolean visit(Object o, IAdaptable adaptable) {
		IBuilderCallBack callBack = (IBuilderCallBack)adaptable.getAdapter(IBuilderCallBack.class);
		System.out.println(o);
		if(o instanceof XcosElement) {
			XcosElement xce = (XcosElement)o;
			// do the job: analyse resource to find traceability links
			retrieveTraceabilityLinks(xce,callBack);
			
		}
		// -RFU- return false;
		return true;
	}

	@Override
	public void end(IAdaptable adaptable) {
	}
	
	
	
	
	/**
	 * @param resource
	 * @param callback
	 */
	private void retrieveTraceabilityLinks(XcosElement xce, IBuilderCallBack callBack) {
		
		

			
		// retrieve requirement references
		String reqRef = getRequirementReference(xce);
		if (reqRef != null) {
			computeTraceability(reqRef,xce,callBack);
		}
			

		
	}
	
	protected String getRequirementReference(XcosElement xce) {
		//TODO change to real parsing
		return "IMPLEMENT-REF";
	}

	/**
	 * Retrieves the right reference to trace links from the object, executes it,
	 * and serialize the links.
	 * 
	 * @param callBack
	 */
	private  void computeTraceability(String linkType, XcosElement from, IBuilderCallBack callBack) {
		TType tType = XcosTTypeProvider.get(linkType);

		//Downward relation from "from" to "result" == upward relation from "result" to "from";
		UUID uniqueID = UUID.randomUUID();
		
		// TODO change to true implementation
		//String result = "REQ-SYS-0020";
	
		callBack.newUpwardRelation(from,  from.getResource(), from, Collections.singletonList(from), tType);
	}

	


}
