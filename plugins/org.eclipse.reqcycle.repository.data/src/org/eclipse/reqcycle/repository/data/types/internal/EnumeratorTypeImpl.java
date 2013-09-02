package org.eclipse.reqcycle.repository.data.types.internal;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;


public class EnumeratorTypeImpl implements EnumeratorType, IAdaptable {

	protected EEnumLiteral eEnumLiteral;
	
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

	/**
	 * @deprecated use getAdapter
	 */
	protected EEnumLiteral getEEnumLiteral() {
		return eEnumLiteral;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if(adapter == EEnumLiteral.class) {
			return eEnumLiteral;
		}
		return null;
	}
	
}
