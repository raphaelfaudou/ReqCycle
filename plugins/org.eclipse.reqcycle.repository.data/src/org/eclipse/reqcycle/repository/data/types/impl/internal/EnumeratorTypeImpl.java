package org.eclipse.reqcycle.repository.data.types.impl.internal;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;


public class EnumeratorTypeImpl implements EnumeratorType {

	private EEnumLiteral eEnumLiteral;
	
	public EnumeratorTypeImpl(String name) {
		eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
		eEnumLiteral.setName(name);
	}
	
	protected EnumeratorTypeImpl(EEnumLiteral eEnumLiteral) {
		this.eEnumLiteral = eEnumLiteral;
	}

	@Override
	public String getName() {
		return eEnumLiteral.getName();
	}

	protected EEnumLiteral getEEnumLiteral() {
		return eEnumLiteral;
	}
	
}
