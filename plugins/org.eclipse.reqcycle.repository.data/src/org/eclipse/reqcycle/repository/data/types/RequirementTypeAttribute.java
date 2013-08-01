package org.eclipse.reqcycle.repository.data.types;

import org.eclipse.emf.ecore.EDataType;


public interface RequirementTypeAttribute extends DataTypeAttribute {

	public String getName();

	public EDataType getType();

	public boolean isHidden();

}
