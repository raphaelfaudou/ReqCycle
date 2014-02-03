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
package org.eclipse.reqcycle.uri.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.reqcycle.uri.IReachableManager;
import org.eclipse.reqcycle.uri.exceptions.IReachableHandlerException;
import org.eclipse.reqcycle.uri.model.IObjectHandler;
import org.eclipse.reqcycle.uri.model.Reachable;
import org.eclipse.reqcycle.uri.model.ReachableObject;
import org.eclipse.ziggurat.inject.ZigguratInject;

public class ReachableUtils {

	@SuppressWarnings("unchecked")
	public static <T> T getAdapter(Object o, Class<T> aclass) {
		T result = null;
		if (o instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) o;
			result = (T) adaptable.getAdapter(aclass);
		}
		if (result == null) {
			result = (T) Platform.getAdapterManager().getAdapter(o, aclass);
		}
		return result;
	}

	/**
	 * Compute an md5 hash based on the input stream the method closes the given
	 * stream
	 * 
	 * @param stream
	 * @return
	 */
	public static String hashStream(InputStream stream) {
		String md5Str = null;
		try {
			final MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buf = new byte[10240];
			while (stream.read(buf) >= 0) {
				md.update(buf);
			}
			stream.close();
			byte[] res = md.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < res.length; i++) {
				hexString.append(Integer.toHexString(0xFF & res[i]));
			}
			md5Str = hexString.toString();
		} catch (NoSuchAlgorithmException e1) {
			// TODO error management
		} catch (IOException e) {
			// TODO error management
		}
		return md5Str;
	}

	public static ReachableObject getReachableObject(IResource res) {
		try {
			IReachableManager manager = ZigguratInject
					.make(IReachableManager.class);
			IObjectHandler handler = manager.getHandlerFromObject(res);
			if (handler != null) {
				return handler.getFromObject(res);
			}
		} catch (IReachableHandlerException e) {
		}
		return null;
	}

	public static Reachable getReachable(IResource res) {
		ReachableObject ro = getReachableObject(res);
		if (ro == null) {
			return null;
		}
		return ro.getReachable(null);
	}
}
