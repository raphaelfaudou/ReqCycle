package org.eclipse.reqcycle.repository.data.types;

import org.eclipse.emf.ecore.EClass;

public interface RequirementType extends DataType {

	public void addAttributeType(RequirementTypeAttribute attr);

	//FIXME : remove method when mapping use RequirementType instead of EClass
	public EClass getEClass();
}
