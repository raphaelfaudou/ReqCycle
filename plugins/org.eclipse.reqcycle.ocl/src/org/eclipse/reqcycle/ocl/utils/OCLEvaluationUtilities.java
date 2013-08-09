package org.eclipse.reqcycle.ocl.utils;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.utilities.BaseResource;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ClassifierContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DefOperationCS;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.ziggurat.ocl.OCLEvaluator;
import org.eclipse.ziggurat.ocl.ZigguratOCLPlugin;


public class OCLEvaluationUtilities {

	public static OCLEvaluator compileOCL(ResourceSet resourceSet, URI oclURI) throws Exception {
		BaseResource resource = (BaseResource)resourceSet.getResource(oclURI, true);
		return compileOCL(resource);
	}

	public static OCLEvaluator compileOCL(BaseResource resource) throws Exception {
		OCLEvaluator evaluator = ZigguratOCLPlugin.createOCLEvaluator();

		Collection<DefOperationCS> operations = OCLSourceUtilities.getOperations(resource);
		for(DefOperationCS operation : operations) {
			compileOperation(evaluator, operation);
		}
		return evaluator;
	}

	private static void compileOperation(OCLEvaluator evaluator, DefOperationCS operationCS) throws Exception {
		ClassifierContextDeclCS classifierContextDecl = operationCS.getClassifierContextDecl();
		Element pivot = classifierContextDecl.getPivot();
		String classifierString = pivot.toString();
		String[] split = classifierString.split("::");
		EClassifier classifier = evaluator.lookupEClassifier(Arrays.asList(split));
		String[] defNameExpression = operationCS.toString().split("def:", 0);
		String defExpression = defNameExpression[1];
		evaluator.compileOperation(defExpression, classifier);
	}


	public static boolean isDataType(OCLEvaluator evaluator, EObject eObject, RequirementType type) {
		String operationName = OCLSourceUtilities.getOperationRequiredName(type);
		EOperation eOperation = evaluator.getCompiledOperation(operationName, eObject);
		if(eOperation != null) {
			Object result = evaluator.evaluateOperation(eOperation, eObject, new Object[0]);
			if(result instanceof Boolean) {
				return (Boolean)result;
			}
		}
		return false;
	}

	public static Object getAttributeValue(OCLEvaluator evaluator, EObject eObject, RequirementTypeAttribute attribute) {
		String operationName = OCLSourceUtilities.getOperationRequiredName(attribute);
		EOperation eOperation = evaluator.getCompiledOperation(operationName, eObject);
		if(eOperation != null) {
			Object result = evaluator.evaluateOperation(eOperation, eObject, new Object[0]);
			if(attribute.getType().isInstance(result)) {
				return result;
			} else {
				Object converted = convertResult(result, attribute);
				if (attribute.getType().isInstance(converted)) {
					return converted;
				}
			}
		}
		return null;
	}
	
	protected static Object convertResult(Object result, RequirementTypeAttribute attribute){
		String instanceClass = attribute.getType().getInstanceClassName();
		if ("float".equalsIgnoreCase(instanceClass) && result instanceof Double){ //$NON-NLS-1$
			return ((Double) result).floatValue();
		}
		return null;
	}

}
