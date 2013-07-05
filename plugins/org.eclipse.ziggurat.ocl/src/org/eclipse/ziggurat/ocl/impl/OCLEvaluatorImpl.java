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
package org.eclipse.ziggurat.ocl.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EvaluationVisitorImpl;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.expressions.ExpressionsFactory;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.expressions.VariableExp;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.options.ParsingOptions;
import org.eclipse.ocl.options.ProblemOption;
import org.eclipse.ziggurat.ocl.OCLEvaluator;
import org.eclipse.ziggurat.ocl.extensions.JavaImplementedOCLOperation;
import org.eclipse.ziggurat.ocl.internal.CustomEnvironmentFactory;
import org.eclipse.ziggurat.ocl.internal.CustomEvaluationEnvironment;
import org.eclipse.ziggurat.ocl.utils.OCLEvaluationUtil;

/**
 * Implementation of the OCLEvaluator
 * 
 * @author omelois
 * 
 */
public class OCLEvaluatorImpl implements OCLEvaluator {

	private static final String STORAGE_SOURCE = "org.eclipse.ziggurat.ocl.storage"; //$NON-NLS-1$

	private OCL ocl;

	private Map<EOperationKey, EOperation> compiledOperations = new HashMap<EOperationKey, EOperation>();

	public OCLEvaluatorImpl() {
		this(new CustomEnvironmentFactory());
	}

	public OCLEvaluatorImpl(EcoreEnvironmentFactory factory) {
		ocl = OCL.newInstance(factory);
		ParsingOptions.setOption(ocl.getEnvironment(), ParsingOptions.implicitRootClass(ocl.getEnvironment()), EcorePackage.Literals.EOBJECT);
		ParsingOptions.setOption(ocl.getEnvironment(), ProblemOption.CLOSURE_ITERATOR, ProblemHandler.Severity.OK);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING, true);
	}

	public OCL getOCL() {
		return this.ocl;
	}

	public EOperation getCompiledOperation(String operationName, Object context){
		if (context instanceof EObject){
			for (EOperationKey key : this.compiledOperations.keySet()){
				EClassifier keyClassifier = key.classifier;
				if (keyClassifier.isInstance(context)){
					return this.compiledOperations.get(key);
				}
			}
			
		}
		return null;
	}
	
	/**
	 * Changes the extent map of the OCL, responsible for the result of the "allInstances" operation execution.
	 */
	public void setOclExtentMap(Map<EClass, ? extends Set<? extends EObject>> extentMap) {
		ocl.setExtentMap(extentMap);
	}

	public Map<EClass, ? extends Set<? extends EObject>> getOclExtentMap() {
		return ocl.getExtentMap();
	}

	public EClassifier lookupEClassifier(String metamodelPrefix, String eClassifierContext) {
		Helper oclHelper = getOCL().createOCLHelper();
		List<String> lookupParameters = Arrays.asList(metamodelPrefix, eClassifierContext);
		EClassifier eClassifier = oclHelper.getOCL().getEnvironment().lookupClassifier(lookupParameters);
		return eClassifier;
	}

	public Object evaluateQuery(String queryBody, Object context) throws ParserException {
		return evaluateQuery(queryBody, context, null);
	}

	public Object evaluateQuery(String queryBody, Object context, Map<String, ? extends Object> globalVars) throws ParserException {
		if(globalVars != null) {
			for(Entry<String, ?> entry : globalVars.entrySet()) {
				// create a variable declaring our global application context
				// object
				Variable<EClassifier, EParameter> var = ExpressionsFactory.eINSTANCE.createVariable();
				var.setName(entry.getKey());

				var.setType(OCLEvaluationUtil.getClassifier(entry.getValue()));
				// add it to the global OCL environment
				getOCL().getEnvironment().addElement(var.getName(), var, true);
			}
		}

		Helper oclHelper = getOCL().createOCLHelper();
		oclHelper.setContext(OCLEvaluationUtil.getClassifier(context));
		Object evalResult = null;

		// Parsing the body of the query.
		OCLExpression oclExpression;
		try {
			oclExpression = oclHelper.createQuery(queryBody);
			if(globalVars != null) {
				for(Entry<String, ?> entry : globalVars.entrySet()) {
					getOCL().getEvaluationEnvironment().add(entry.getKey(), entry.getValue());
				}
			}
			evalResult = getOCL().evaluate(context, oclExpression);
		} finally {
			// Clearing the variables
			getOCL().getEnvironment().getVariables().clear();
			if(globalVars != null) {
				for(Entry<String, ?> entry : globalVars.entrySet()) {
					getOCL().getEvaluationEnvironment().remove(entry.getKey());
				}
			}
		}
		return evalResult;
	}

	public EOperation compileOperation(JavaImplementedOCLOperation operation) {
		String name = operation.getName();
		if(operation != null && name != null && !name.isEmpty()) {
			if(getOCL().getEvaluationEnvironment() instanceof CustomEvaluationEnvironment) {
				CustomEvaluationEnvironment evaluationEnvironment = (CustomEvaluationEnvironment)getOCL().getEvaluationEnvironment();
				evaluationEnvironment.addCustomJavaOperation(operation);
				EClassifier eClassifier = operation.getEClassifier();
				List<Variable<EClassifier, EParameter>> parameters = operation.getParameters();
				return getOCL().getEnvironment().defineOperation(eClassifier, name, operation.getType(), parameters, org.eclipse.ocl.ecore.EcoreFactory.eINSTANCE.createConstraint());
			}
		}
		return null;
	}

	public EOperation compileOperation(String operationDef, EClassifier eClassifier) throws ParserException {
		Helper oclHelper = getOCL().createOCLHelper();
		// adding the operation and its context to the environment.
		oclHelper.setContext(eClassifier);
		EOperation eOperation;
		eOperation = oclHelper.defineOperation(operationDef);
		oclHelper.setOperationContext(eClassifier, eOperation);
		setContextOperation(eClassifier, eOperation);
		compiledOperations.put(new EOperationKey(eOperation.getName(), eClassifier), eOperation);
		return eOperation;
	}

	public EOperation compileOperation(String operationDef, String metaModelPrefix, String eClassifierContext) throws ParserException {
		Helper oclHelper = getOCL().createOCLHelper();
		List<String> lookupParameters = Arrays.asList(metaModelPrefix, eClassifierContext);
		EClassifier eClassifier = oclHelper.getOCL().getEnvironment().lookupClassifier(lookupParameters);

		// adding the operation and its context to the environment.
		return compileOperation(operationDef, eClassifier);
	}

	/**
	 * Used to store the context of an eOperation that is compiled by this evaluator.
	 */
	protected void setContextOperation(EClassifier eclass, EOperation op) {
		EAnnotation annot = op.getEAnnotation(STORAGE_SOURCE);
		if(annot == null) {
			annot = EcoreFactory.eINSTANCE.createEAnnotation();
			annot.setSource(STORAGE_SOURCE);
			op.getEAnnotations().add(annot);
		}
		annot.getReferences().clear();
		annot.getReferences().add(eclass);
	}

	/**
	 * Used to retrieve the context of an eOperation that was compiled in this evaluator.
	 */
	protected EClassifier getContextOperation(EOperation op) {
		EAnnotation annot = op.getEAnnotation(STORAGE_SOURCE);
		return (EClassifier)annot.getReferences().get(0);
	}

	public Object evaluateOperation(EOperation eOperation, EObject context, Object[] args) {
		EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> evaluationEnvironment = getOCL().getEvaluationEnvironment();
		// Clearing the evaluation environment of remaining variables.
		evaluationEnvironment.clear();

		// Addition of the "self" parameter.
		evaluationEnvironment.add("self", context); //$NON-NLS-1$
		// Addition of other parameters.
		for(int i = 0; i < args.length; i++) {
			EParameter p = eOperation.getEParameters().get(i);
			evaluationEnvironment.add(p.getName(), args[i]);
		}
		// Creating an evaluation visitor, in charge of evaluating the
		// expression.
		Map<EClass, ? extends Set<? extends EObject>> extentMap = getOCL().getExtentMap();
		if(extentMap == null) {
			// Retrieving the default dynamic extent map implementation
			extentMap = evaluationEnvironment.createExtentMap(context);
		}
		EvaluationVisitorImpl visitor = new EvaluationVisitorImpl(getOCL().getEnvironment(), evaluationEnvironment, extentMap);
		OperationCallExp<EClassifier, Object> expression = getExpression(eOperation);
		Object result = null;
		// Checking that the operation is applicable on the context object.
		if(getContextOperation(eOperation).isInstance(context)) {
			result = expression.accept(visitor);
		}
		// Clearing the evaluation environment of variables.
		evaluationEnvironment.clear();
		return result;
	}

	protected static VariableExp<EClassifier, EParameter> getVariable(String name, EClassifier type) {
		Variable<EClassifier, EParameter> self = ExpressionsFactory.eINSTANCE.createVariable();
		self.setName(name);
		self.setType(type);
		VariableExp<EClassifier, EParameter> var = ExpressionsFactory.eINSTANCE.createVariableExp();
		var.setName(name);
		var.setReferredVariable(self);
		return var;
	}

	protected static OperationCallExp<EClassifier, Object> getExpression(EOperation eOperation) {
		OperationCallExp<EClassifier, Object> result = ExpressionsFactory.eINSTANCE.createOperationCallExp();
		result.setReferredOperation(eOperation);
		VariableExp<EClassifier, EParameter> var = getVariable(Environment.SELF_VARIABLE_NAME, eOperation.getEContainingClass());
		var.setType(eOperation.getEContainingClass());
		// Adding parameters to the operation call exp.
		for(EParameter arg : eOperation.getEParameters()) {
			VariableExp<EClassifier, EParameter> v = getVariable(arg.getName(), arg.getEType());
			result.getArgument().add(v);
		}
		result.setSource(var);
		result.setType(eOperation.getEType());
		return result;
	}

	protected OCL createOCL(List<String> listPackages) {
		EcoreEnvironmentFactory factory = new EcoreEnvironmentFactory(EPackage.Registry.INSTANCE);
		Environment<EPackage, EClassifier, EOperation, EStructuralFeature, EEnumLiteral, EParameter, EObject, CallOperationAction, SendSignalAction, Constraint, EClass, EObject> pckContext = factory.createPackageContext(this.ocl.getEnvironment(), listPackages);
		OCL newOcl = OCL.newInstance(pckContext);
		return newOcl;
	}

	private class EOperationKey {

		protected String name;

		protected EClassifier classifier;

		public EOperationKey(String name, EClassifier classifier) {
			this.name = name;
			this.classifier = classifier;
		}


		@Override
		public boolean equals(Object obj) {
			if(obj instanceof EOperationKey) {
				return (name != null && name.equals(((EOperationKey)obj).name)) && (classifier != null && classifier.equals(((EOperationKey)obj).classifier));
			}
			return false;
		}

		@Override
		public int hashCode() {
			if(name != null) {
				return name.hashCode();
			}
			return super.hashCode();
		}

	}

}
