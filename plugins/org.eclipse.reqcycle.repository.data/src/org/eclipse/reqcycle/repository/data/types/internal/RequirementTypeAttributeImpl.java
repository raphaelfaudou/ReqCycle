package org.eclipse.reqcycle.repository.data.types.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;


public class RequirementTypeAttributeImpl implements RequirementTypeAttribute {
	
	private EAttribute eAttribute;
	
	public RequirementTypeAttributeImpl(String name, EDataType type){
		EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		eAttribute.setEType(type);
		eAttribute.setName(name);
		this.eAttribute = eAttribute;
	}
	
	protected RequirementTypeAttributeImpl(EAttribute eAttribute) {
		this.eAttribute = eAttribute;
	}
	
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
