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
package org.polarsys.reqcycle.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;

public interface ILogger {
	public void trace (String message);
	
	public void log (IStatus status);
	
	public void info (String message);
	
	public void warning (String message);
	
	public void error (String message);

	boolean isDebug(String path, Plugin p);
}
