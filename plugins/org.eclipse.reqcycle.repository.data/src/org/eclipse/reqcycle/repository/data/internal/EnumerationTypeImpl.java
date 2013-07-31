package org.eclipse.reqcycle.repository.data.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.EnumerationType;
import org.eclipse.reqcycle.repository.data.EnumeratorType;


public class EnumerationTypeImpl implements EnumerationType {

	private EEnum eEnum;
	
	private Collection<EnumeratorType> enumerators = new ArrayList<EnumeratorType>();
	
	public EnumerationTypeImpl(String name) {
		eEnum = EcoreFactory.eINSTANCE.createEEnum();
		eEnum.setName(name);
	}
	
	protected EnumerationTypeImpl(EEnum eEnum) {
		this.eEnum = eEnum;
		for(EEnumLiteral eLiteral : eEnum.getELiterals()) {
			enumerators.add(new EnumeratorTypeImpl(eLiteral));
		}
	}
	
	@Override
	public void addEnumeratorType(EnumeratorType enumerator) {
		eEnum.getELiterals().add(((EnumeratorTypeImpl)enumerator).getEEnumLiteral());
		enumerators.add(enumerator);
	}

	@Override
	public String getName() {
		return eEnum.getName();
	}

	protected EEnum getEEnum() {
		return eEnum;
	}

	@Override
	public Collection<EnumeratorType> getEnumerators() {
		return enumerators;
	}
}
