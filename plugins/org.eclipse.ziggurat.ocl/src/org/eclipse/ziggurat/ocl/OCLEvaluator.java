/*******************************************************************************
 * Copyright (c) 2013 Atos.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Atos - initial API and implementation
 ******************************************************************************/
package org.eclipse.ziggurat.ocl;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ziggurat.ocl.extensions.JavaImplementedOCLOperation;

/**
 * An OCL evaluator that can evaluate OCL operations and queries by passing it 
 * strings representing the OCL code.
 */
public interface OCLEvaluator {

	/**
	 * Gets the OCL.
	 * @return
	 */
	public OCL getOCL();
	
	/**
	 * Returns true if the OCL Environment is dirty : EOperations were defined in it with compilations errors. 
	 * The operations should be re-compiled.
	 * @return
	 */
	public boolean isDirty();
	
	/**
	 * Compiles an operation defined in ocl, in the OCL environment. The context on which the operation is applicable
	 * is computed from the metamodel prefix and the classifier name.
	 */
	public EOperation compileOperation(String operationDef, String metaModelPrefix, String eClassifierContext) throws ParserException;
	
	
	/**
	 * Compiles an operation defined in ocl, in the OCL environment.
	 */
	public EOperation compileOperation(String operationDef, EClassifier eClassifierContext) throws ParserException;
	
	/**
	 * Compiles an operation defined in Java, in the OCL environment.
	 * @param operation
	 * @return
	 */
	public EOperation compileOperation(JavaImplementedOCLOperation operation);

	/**
	 * Finds an operation that was compiled in the OCL environment of this evaluator, and that 
	 * is compatible with the given context.
	 */
	public EOperation getCompiledOperation(String operationName, Object context);
	
	/**
	 * Evaluates an operation on a given context, with arguments that should match the operation's parameter.
	 */
	public Object evaluateOperation(EOperation eOperation, EObject context, Object[] args);

	/**
	 * Evaluates a query on a given context. 
	 */
	public Object evaluateQuery(String queryBody, Object context) throws ParserException; 

	/**
	 * Evaluates a query on a given context, with global variables that will be erased after the evaluation of the query. 
	 */
	public Object evaluateQuery(String queryBody, Object context, Map<String, ? extends Object> globalVars) throws ParserException;
	
	
	/**
	 * Computes a context classifier from a metamodel prefix and a context name.
	 */
	public EClassifier lookupEClassifier(String metamodelPrefix, String eClassifierContext);
	
	/**
	 * Sets the extent map of the OCL environment, responsible for the result of the "allInstances" operation execution.
	 */
	public void setOclExtentMap(Map<EClass, ? extends Set<? extends EObject>> extentMap);
	
	/**
	 * Retrieves the extent map of the OCL environment.
	 */
	public Map<EClass, ? extends Set<? extends EObject>> getOclExtentMap();
}
