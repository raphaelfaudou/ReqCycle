/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Olivier Melois (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.ziggurat.collect.exceptions;

public class CannotPickException extends Exception {

	private static final long serialVersionUID = 1065892189117187335L;

	public CannotPickException() {
	}

	public CannotPickException(String message) {
		super(message);
	}

	public CannotPickException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotPickException(Throwable cause) {
		super(cause);
	}
}
