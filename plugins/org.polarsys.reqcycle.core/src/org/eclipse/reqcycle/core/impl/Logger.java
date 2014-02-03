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
package org.eclipse.reqcycle.core.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Singleton;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.reqcycle.core.ILogger;

@Singleton
public class Logger implements ILogger {

	@Override
	public void log(IStatus status) {
		
	}

	@Override
	public void info(String message) {
		
	}

	@Override
	public void warning(String message) {
		
	}

	@Override
	public void error(String message) {
	}

	@Override
	public boolean isDebug(String path, Plugin p) {
		String debugOption = Platform.getDebugOption(path);
		return (p.isDebugging() && "true".equalsIgnoreCase(debugOption));
	}

	@Override
	public void trace(String message) {
		Date time = Calendar.getInstance().getTime();
		String format = new SimpleDateFormat("hh:mm:ss:SS").format(time);
		System.out.println(String.format("%s\t: %s",format, message));
	}

}
