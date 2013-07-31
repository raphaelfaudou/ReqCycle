package org.eclipse.reqcycle.repository.data.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.AttributeType;
import org.eclipse.reqcycle.repository.data.RequirementType;

import DataModel.DataModelPackage;


public class DataTypeImpl implements RequirementType {

	private EClass eClass;
	
	private Collection<AttributeType> attributes = new ArrayList<AttributeType>();
	
	public DataTypeImpl(String name) {
		eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		eClass.getESuperTypes().add(DataModelPackage.Literals.REQUIREMENT_SECTION);
		for(EAttribute eAttribute : eClass.getEAllAttributes()){
			attributes.add(new AttributeTypeImpl(eAttribute));
		}
	}
	
	protected DataTypeImpl(EClass eClass){
		this.eClass = eClass;
		for(EAttribute attribute : eClass.getEAllAttributes()) {
			attributes.add(new AttributeTypeImpl(attribute)); 
		}
	}
	
	@Override
	public void addAttributeType(AttributeType attributeType) {
		attributes.add(attributeType);
		eClass.getEStructuralFeatures().add(((AttributeTypeImpl)attributeType).getEAttribute());
	}

	@Override
	public String getName() {
		return eClass.getName();
	}
	
	//FIXME : set to protected when RequirementType is used in mapping instead of EClass
	public EClass getEClass(){
		return eClass;
	}

	@Override
	public Collection<AttributeType> getAttributeTypes() {
		return attributes;
	}

}
