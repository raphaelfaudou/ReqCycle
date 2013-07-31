package org.eclipse.reqcycle.repository.data;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;


public interface AttributeType {
	
	public String getName();
	
	public EDataType getType();
	
	public boolean isHidden(); 
	
	//FIXME : remove this method when mapping use AttributeType instead of EAttribute.
	public EAttribute getEAttribute();
}
