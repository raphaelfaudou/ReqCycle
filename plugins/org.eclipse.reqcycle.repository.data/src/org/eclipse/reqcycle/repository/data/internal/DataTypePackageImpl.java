package org.eclipse.reqcycle.repository.data.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.RequirementType;
import org.eclipse.reqcycle.repository.data.DataTypePackage;
import org.eclipse.reqcycle.repository.data.EnumerationType;


public class DataTypePackageImpl implements DataTypePackage {
	
	private EPackage ePackage;
	
	private Collection<DataTypePackage> packages = new ArrayList<DataTypePackage>();
	
	private Collection<RequirementType> dataTypes = new ArrayList<RequirementType>();
	
	private Collection<EnumerationType> enumerationTypes = new ArrayList<EnumerationType>();
	
	private static final String NS_URI = "http://www.eclipse.org/ReqCycle/DataType";
	
	public DataTypePackageImpl(String name) {
		ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName(name);
		ePackage.setName(name);
		ePackage.setNsPrefix(name);
		ePackage.setNsURI(NS_URI + "/" + name);
	}
	
	public DataTypePackageImpl(EPackage ePackage) {
		this.ePackage = ePackage;
		
		for(EClassifier classifier : ePackage.getEClassifiers()) {
			if(classifier instanceof EClass) {
				dataTypes.add(new DataTypeImpl((EClass)classifier));
			} else if (classifier instanceof EEnum) {
				enumerationTypes.add(new EnumerationTypeImpl((EEnum)classifier));
			}
		}
		
		for(EPackage subPackage : ePackage.getESubpackages()) {
			packages.add(new DataTypePackageImpl(subPackage));
		}
	}

	@Override
	public String getName() {
		return ePackage!=null?ePackage.getName():null;
	}

	EFactory createFactoryInstance() {
		return ePackage.getEFactoryInstance();
	}

	@Override
	public EObject create(RequirementType dataType) {
		return createFactoryInstance().create(((DataTypeImpl)dataType).getEClass());
	}

	@Override
	public void add(DataTypePackage dataTypePackage) {
		ePackage.getESubpackages().add(((DataTypePackageImpl)dataTypePackage).getEPackage());
		packages.add(dataTypePackage);
	}
	
	@Override
	public void add(RequirementType dataType) {
		ePackage.getEClassifiers().add(((DataTypeImpl)dataType).getEClass());
		dataTypes.add(dataType);
	}
	
	public EPackage getEPackage() {
		return ePackage;
	}

	@Override
	public void add(EnumerationType enumerationType) {
		ePackage.getEClassifiers().add(((EnumerationTypeImpl)enumerationType).getEEnum());
		enumerationTypes.add(enumerationType);
	}

	@Override
	public DataTypePackage getDataTypePackage(String name) {
		for(DataTypePackage p : packages) {
			if(name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public Collection<DataTypePackage> getDataTypePackages() {
		return packages;
	}

	@Override
	public EnumerationType getEnumerationType(String name) {
		for(EnumerationType enumerationType : enumerationTypes) {
			if(name.equals(enumerationType.getName())) {
				return enumerationType;
			}
		}
		return null;
	}

	@Override
	public RequirementType getDataType(String name) {
		for(RequirementType dataType : dataTypes) {
			if(name.equals(dataType.getName())) {
				return dataType;
			}
		}
		return null;
	}

	@Override
	public Collection<RequirementType> getAllDataTypes() {
		return dataTypes;
	}

	@Override
	public Collection<EnumerationType> getAllEnumerationTypes() {
		return enumerationTypes;
	}
	
}
