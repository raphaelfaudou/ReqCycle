package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;


public interface RequirementType extends DataType{
	
	public void addAttributeType(AttributeType attr);

	public Collection<AttributeType> getAttributeTypes();
	
	//FIXME : remove method when mapping use RequirementType instead of EClass
	public EClass getEClass();
}
