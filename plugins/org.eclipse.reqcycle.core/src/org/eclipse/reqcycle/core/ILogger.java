package org.eclipse.reqcycle.core;

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
