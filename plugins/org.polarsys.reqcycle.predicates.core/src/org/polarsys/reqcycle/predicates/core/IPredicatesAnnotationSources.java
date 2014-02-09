/*******************************************************************************
 * * Copyright (c) 2013 AtoS
 * * All rights reserved. This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License v1.0
 * * which accompanies this distribution, and is available at
 * * http://www.eclipse.org/legal/epl-v10.html *
 * * Contributors:
 * * Papa Issa Diakhate (AtoS) - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.polarsys.reqcycle.predicates.core;

/**
 * Contains the common EAnnotation sources used into the IPredicate meta model.
 * 
 * @author Papa Issa DIAKHATE
 */
public interface IPredicatesAnnotationSources {

	String EANNOTATION_SOURCE_INPUT = "www.eclipse.org/reqcycle/predicates/userInput";

	String EANNOTATION_SOURCE_SPECIFIC_INPUT = "www.eclipse.org/reqcycle/predicates/specificUserInput";

	String EANNOTATION_SOURCE_ADAPT_INPUT = "www.eclipse.org/reqcycle/predicates/adaptInput";

	String EANNOTATION_SOURCE_INPUT_JAVACLASS_TYPE = "www.eclipse.org/reqcycle/predicates/input_javaclass_type";

}
