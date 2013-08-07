package org.eclipse.reqcycle.repository.data.types.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;

import DataModel.DataModelPackage;
import DataModel.RequirementSection;


public class RequirementTypeImpl implements RequirementType {

	protected EClass eClass;
	
	protected Collection<RequirementTypeAttribute> attributes = new ArrayList<RequirementTypeAttribute>();
	
	public RequirementTypeImpl(String name) {
		eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName(name);
		eClass.getESuperTypes().add(DataModelPackage.Literals.REQUIREMENT_SECTION);
		for(EAttribute eAttribute : eClass.getEAllAttributes()){
			attributes.add(new RequirementTypeAttributeImpl(eAttribute));
		}
	}
	
	public RequirementTypeImpl(EClass eClass){
		this.eClass = eClass;
		for(EAttribute attribute : eClass.getEAllAttributes()) {
			attributes.add(new RequirementTypeAttributeImpl(attribute)); 
		}
	}
	
	@Override
	public void addAttributeType(RequirementTypeAttribute attributeType) {
		attributes.add(attributeType);
		eClass.getEStructuralFeatures().add(((RequirementTypeAttributeImpl)attributeType).getEAttribute());
	}

	@Override
	public String getName() {
		return eClass.getName();
	}
	
	public EClass getEClass(){
		return eClass;
	}

	@Override
	public Collection<RequirementTypeAttribute> getAttributes() {
		return attributes;
	}

	@Override
	public RequirementSection createInstance() {
		EPackage ePackage = eClass.getEPackage();
		if(ePackage != null) {
			return (RequirementSection)ePackage.getEFactoryInstance().create(eClass);
		}
		return null;
	}

}
