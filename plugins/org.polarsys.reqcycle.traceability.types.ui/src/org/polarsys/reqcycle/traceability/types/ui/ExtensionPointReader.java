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
package org.polarsys.reqcycle.traceability.types.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.polarsys.reqcycle.types.IType.FieldDescriptor;
import org.polarsys.reqcycle.types.IType.FieldURIDescriptor;

public class ExtensionPointReader {

	private static final String PLUGIN_ID = "org.polarsys.reqcycle.traceability.types.ui";
	public static String EXT_ID_TYPEENTRY = "typeEntry";
	public static String ATT_CLASS = "class";
	public static String ATT_ENTRY = "entry";

	Map<Class<?>, IEntryCompositeProvider> map = new HashMap<Class<?>, IEntryCompositeProvider>();

	
	public ExtensionPointReader() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg.getConfigurationElementsFor(PLUGIN_ID, EXT_ID_TYPEENTRY);
		for(IConfigurationElement iConfigurationElement : extensions) {
			try {
				String clazz = iConfigurationElement.getAttribute(ATT_CLASS);
				Class<?> c = Platform.getBundle(iConfigurationElement.getContributor().getName()).loadClass(clazz);
				IEntryCompositeProvider t = (IEntryCompositeProvider)iConfigurationElement.createExecutableExtension(ATT_ENTRY);
				
				map.put(c, t);
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InvalidRegistryObjectException e1) {
				e1.printStackTrace();
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	public IEntryCompositeProvider getEntryCompositeProvider(FieldDescriptor d) {
		if(d instanceof FieldURIDescriptor) {
			return getEntryCompositeProvider(((FieldURIDescriptor)d).realType);
		} else {
			return getEntryCompositeProvider(d.type);
		}
	}
	
	public IEntryCompositeProvider getEntryCompositeProvider(Class<?> c) {
		if (map.containsKey(c)) {
			return map.get(c);
		}
		
		Set<Class<?>> keys = map.keySet();
		for(Class<?> key : keys) {
			if(key.equals(c)) {
				return map.get(key);
			}
		}
		
		return null;
		
	}

}
