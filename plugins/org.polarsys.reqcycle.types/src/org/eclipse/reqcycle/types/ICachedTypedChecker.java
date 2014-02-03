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
package org.eclipse.reqcycle.types;

import org.eclipse.reqcycle.uri.model.Reachable;

/**
 * 
 * @author tfaure
 *
 */
public interface ICachedTypedChecker {
	boolean is(Reachable reachable, ITypeChecker typeCheckers);
}