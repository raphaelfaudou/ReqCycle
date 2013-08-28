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
import org.eclipse.reqcycle.repository.data.types.DataModel;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;

import DataModel.RequirementSection;
import DataModel.Scope;


public class DataModelImpl implements DataModel {
	
	protected EPackage ePackage;
	
	protected Collection<DataModel> subPackages = new ArrayList<DataModel>();
	
	protected Collection<Scope> scopes = new ArrayList<Scope>();
	
	protected Collection<RequirementType> dataTypes = new ArrayList<RequirementType>();
	
	protected Collection<EnumerationType> enumerationTypes = new ArrayList<EnumerationType>();
	
	protected static final String NS_URI = "http://www.eclipse.org/ReqCycle/CustomDataModels";
	
	public DataModelImpl() {
		ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName("");
		ePackage.setName("");
		ePackage.setNsPrefix("");
		ePackage.setNsURI(NS_URI);
	}

	public DataModelImpl(String name) {
		ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName(name);
		ePackage.setName(name);
		ePackage.setNsPrefix(name);
		ePackage.setNsURI(NS_URI + "/" + name);
	}
	
	public DataModelImpl(EPackage ePackage) {
		this.ePackage = ePackage;
		
		for(EClassifier classifier : ePackage.getEClassifiers()) {
			if(classifier instanceof EClass) {
				dataTypes.add(new RequirementTypeImpl((EClass)classifier));
			} else if (classifier instanceof EEnum) {
				enumerationTypes.add(new EnumerationTypeImpl((EEnum)classifier));
			}
		}
		
		for(EPackage subPackage : ePackage.getESubpackages()) {
			subPackages.add(new DataModelImpl(subPackage));
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
		for(DataModel p : getSubDataModels()) {
			EPackage pac = ((DataModelImpl)p).getEPackage();
			if (pac.getEClassifiers().contains(eclass)){
				return (RequirementSection)((DataModelImpl)p).getEPackage().getEFactoryInstance().create(eclass);
			}
		}
		return null;
	}

	@Override
	public void add(DataModel dataModel) {
		ePackage.getESubpackages().add(((DataModelImpl)dataModel).getEPackage());
		subPackages.add(dataModel);
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
	public DataModel getDataTypePackage(String name) {
		if (name == null) {
			return null;
		}
		for(DataModel p : subPackages) {
			if(name.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public Collection<DataModel> getSubDataModels() {
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
