package org.eclipse.reqcycle.repository.data.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.AttributeType;


public class AttributeTypeImpl implements AttributeType {
	
	private EAttribute eAttribute;
	
	public AttributeTypeImpl(String name, EDataType dataType){
		EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		eAttribute.setEType(dataType);
		eAttribute.setName(name);
		this.eAttribute = eAttribute;
	}
	
	protected AttributeTypeImpl(EAttribute eAttribute) {
		this.eAttribute = eAttribute;
	}
	
	//FIXME : change visibility to protected
	public EAttribute getEAttribute() {
		return eAttribute;
	}
	
	@Override
	public String getName() {
		return eAttribute!=null?eAttribute.getName():null;
	}
	
	@Override
	public EDataType getType() {
		return eAttribute.getEAttributeType();
	}
	
	@Override
	public boolean isHidden() {
		if (eAttribute.getEAnnotation("hidden") != null) {
			return true;
		}
		return false;
	}
	
}
