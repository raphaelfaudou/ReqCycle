package org.eclipse.reqcycle.repository.data.types.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;

import DataModel.RequirementSection;
import DataModel.Scope;


public class DataTypePackageImpl implements DataTypePackage {
	
	private EPackage ePackage;
	
	private Collection<DataTypePackage> subPackages = new ArrayList<DataTypePackage>();
	
	private Collection<Scope> scopes = new ArrayList<Scope>();
	
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
				dataTypes.add(new RequirementTypeImpl((EClass)classifier));
			} else if (classifier instanceof EEnum) {
				enumerationTypes.add(new EnumerationTypeImpl((EEnum)classifier));
			}
		}
		
		for(EPackage subPackage : ePackage.getESubpackages()) {
			subPackages.add(new DataTypePackageImpl(subPackage));
		}
		
		EAnnotation scopeEAnnotation = ePackage.getEAnnotation("SCOPES");
		if(scopeEAnnotation != null) {
			for(EObject content : scopeEAnnotation.getContents()) {
				if(content instanceof Scope) {
					scopes.add((Scope)content);
				}
			}
		}
	}

	@Override
	public String getName() {
		return ePackage!=null?ePackage.getName():null;
	}

	EFactory createFactoryInstance() {
		return ePackage.getEFactoryInstance();
	}

	//FIXME : Continue
	@Override
	public RequirementSection create(RequirementType dataType) {
//		return (RequirementSection)createFactoryInstance().create(((RequirementTypeImpl)dataType).getEClass());
		EClass eclass = ((RequirementTypeImpl)dataType).getEClass();
		for(DataTypePackage p : getDataTypePackages()) {
			EPackage pac = ((DataTypePackageImpl)p).getEPackage();
			if (pac.getEClassifiers().contains(eclass)){
				return (RequirementSection)((DataTypePackageImpl)p).getEPackage().getEFactoryInstance().create(eclass);
			}
		}
		return null;
	}

	@Override
	public void add(DataTypePackage dataTypePackage) {
		ePackage.getESubpackages().add(((DataTypePackageImpl)dataTypePackage).getEPackage());
		subPackages.add(dataTypePackage);
	}
	
	@Override
	public void add(RequirementType dataType) {
		ePackage.getEClassifiers().add(((RequirementTypeImpl)dataType).getEClass());
		dataTypes.add(dataType);
	}
	
	public EPackage getEPackage() {
		return ePackage;
	}

	@Override
	public void add(EnumerationType enumerationType) {
		ePackage.getEClassifiers().add(enumerationType.getEDataType());
		enumerationTypes.add(enumerationType);
	}

	@Override
	public DataTypePackage getDataTypePackage(String name) {
		for(DataTypePackage p : subPackages) {
			if(name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public Collection<DataTypePackage> getDataTypePackages() {
		return subPackages;
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
	public Collection<RequirementType> getDataTypes() {
		return dataTypes;
	}

	@Override
	public Collection<EnumerationType> getEnumerationTypes() {
		return enumerationTypes;
	}

	@Override
	public void add(Scope scope) {
		
		EAnnotation scopeEAnnotation = ePackage.getEAnnotation("SCOPES");
		if(scopeEAnnotation == null) {
			scopeEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			scopeEAnnotation.setSource("SCOPES");
			ePackage.getEAnnotations().add(scopeEAnnotation);
		}
		scopes.add(scope);
		scopeEAnnotation.getContents().add(scope);
	}

	@Override
	public Scope getScope(String name) {
		for(Scope scope : scopes) {
			if(name.equals(scope.getName())) {
				return scope;
			}
		}
		return null;
	}

	@Override
	public Collection<Scope> getScopes() {
		return scopes;
	}

	@Override
	public void add(DataType type) {
		if(type instanceof RequirementType) {
			add((RequirementType)type);
		} else if (type instanceof EnumerationType) {
			add((EnumerationType)type);
		}
	}
	
}
