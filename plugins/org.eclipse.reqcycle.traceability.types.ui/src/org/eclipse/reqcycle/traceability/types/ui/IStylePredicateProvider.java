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
package org.eclipse.reqcycle.traceability.types.ui;

import org.eclipse.reqcycle.traceability.model.Link;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public interface IStylePredicateProvider {
	Font getFontForRelation(Link link);

	Color getColorForRelation(Link link);
}
