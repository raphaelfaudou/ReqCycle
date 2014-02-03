/*******************************************************************************
 *  * Copyright (c) 2013-2014 AtoS.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * which accompanies this distribution, and is available at
 *  * http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  * Contributors:
 *  *    {INITIAL AUTHOR} - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.xcos.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.polarsys.reqcycle.uri.IReachableCreator;
import org.polarsys.reqcycle.uri.model.ReachableObject;
import org.polarsys.reqcycle.xcos.model.XcosElement;
import org.polarsys.reqcycle.xcos.model.XcosModel;
import org.polarsys.reqcycle.xcos.model.XcosReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Joiner;

public class XcosUtils {
	public static final String XcosExtension = "xcos";
	public static final String PLATFORM = "platform:";
	public static final String SEPARATOR = "::";
	private static IReachableCreator creator = ZigguratInject
			.make(IReachableCreator.class);

	public static ReachableObject getReachableObject(IFile cu) {
		if (XcosExtension.equalsIgnoreCase(cu.getFileExtension())) {
			try {
				XcosReachableObject xcosReachableObject = new XcosReachableObject(
						creator.getReachable(new URI(PLATFORM
								+ cu.getFullPath().toString()), cu));
				ZigguratInject.inject(xcosReachableObject);
				return xcosReachableObject;
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ReachableObject getReachable(XcosElement xe) {
		URI uri;
		
		try {
			uri = new URI(getQualifiedURI(xe));
			XcosReachableObject object = new XcosReachableObject(
					creator.getReachable(uri, xe));
			ZigguratInject.inject(object);
			return object;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	protected static String getQualifiedURI(XcosElement element) {
		StringBuilder result = new StringBuilder();
		result.append(PLATFORM)
				.append(element.getResource().getFullPath().toString())
				.append("#");
		List<String> names = new LinkedList<String>();
		names.add(element.getElementName());
		XcosElement parent = element.getParent();
		while (parent != null ) {
			names.add(0, parent.getElementName());
			parent = parent.getParent();
		}
		result.append(Joiner.on(SEPARATOR).join(names));
		return result.toString();
	}
}
