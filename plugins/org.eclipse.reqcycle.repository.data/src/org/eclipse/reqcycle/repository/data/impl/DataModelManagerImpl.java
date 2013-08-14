package org.eclipse.reqcycle.repository.data.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.internal.DataTypePackageImpl;
import org.eclipse.reqcycle.repository.data.types.internal.EnumerationTypeImpl;
import org.eclipse.reqcycle.repository.data.types.internal.EnumeratorTypeImpl;
import org.eclipse.reqcycle.repository.data.types.internal.RequirementTypeAttributeImpl;
import org.eclipse.reqcycle.repository.data.types.internal.RequirementTypeImpl;
import org.eclipse.ziggurat.configuration.IConfigurationManager;

import DataModel.DataModelFactory;
import DataModel.Scope;

@Singleton
public class DataModelManagerImpl implements IDataModelManager {

	/** EPackage containing possible data types */
	protected DataTypePackage dataTypePackage;

	/** Configuration Manager */
	@Inject
	IConfigurationManager confManager;

	/** Configuration ID */
	final static String CONF_ID = "org.eclipse.reqcycle.data.dataTypes";

	@Inject
	@Named("confResourceSet")
	protected ResourceSet rs;

	/**
	 * Constructor
	 */
	@Inject
	DataModelManagerImpl(@Named("confResourceSet") ResourceSet rs, IConfigurationManager confManager) {
		this.rs = rs;
		this.confManager = confManager;
		
		initTypes();
	}

	protected void initTypes() {
		
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, rs, true);
		if(conf instanceof EPackage) {
			dataTypePackage = new DataTypePackageImpl((EPackage)conf);
		} else {
			dataTypePackage = new DataTypePackageImpl("DataTypes");
			save();
		}
	}

	@Override
	public void discardUnsavedChanges() {
		initTypes();
	}

	@Override
	public void save() {
		try {
			EPackage ePackage = ((DataTypePackageImpl)dataTypePackage).getEPackage();
			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, rs);
		} catch (IOException e) {
			// FIXME : use logger
			e.printStackTrace();
		}
	}

	@Override
	public EObject createInstance(RequirementType dataType) {
		return dataTypePackage.create(dataType);
	}

	@Override
	public DataTypePackage createDataTypePackage(String name) {
		DataTypePackage dataTypePackage = new DataTypePackageImpl(name);
		this.dataTypePackage.add(dataTypePackage);
		return dataTypePackage;
	}

	@Override
	public void addDataTypePackage(DataTypePackage p) {
		dataTypePackage.add(p);
	}

	@Override
	public void addDataTypes(DataTypePackage dataTypePackage, DataType... types){
		for(DataType type : types) {
			dataTypePackage.add(type);
		}
	}
	
	@Override
	public void addRequirementTypes(DataTypePackage dataTypePackage, RequirementType... types) {
		for(RequirementType dataType : types) {
			dataTypePackage.add(dataType);
		}
	}

	@Override
	public void addEnumerationTypes(DataTypePackage dataTypePackage, EnumerationType... enumerationTypes) {
		for(EnumerationType enumerationType : enumerationTypes) {
			dataTypePackage.add(enumerationType);
		}
	}

	@Override
	public DataTypePackage getDataTypePackage(String name) {
		Assert.isNotNull(dataTypePackage);
		return dataTypePackage.getDataTypePackage(name);
	}

	@Override
	public Collection<DataTypePackage> getAllDataTypePackages() {
		Assert.isNotNull(dataTypePackage);
		return dataTypePackage.getDataTypePackages();
	}

	@Override
	public RequirementType getDataType(DataTypePackage dataTypePackage, String name) {
		return dataTypePackage.getDataType(name);
	}

	@Override
	public Collection<RequirementType> getDataTypes(DataTypePackage dataTypePackage) {
		return dataTypePackage.getDataTypes();
	}

	@Override
	public Collection<RequirementType> getAllDataTypes() {
		Assert.isNotNull(dataTypePackage);
		Collection<RequirementType> types = new ArrayList<RequirementType>();
		for(DataTypePackage p : getAllDataTypePackages()) {
			types.addAll(getDataTypes(p));
		}
		return types;
	}

	@Override
	public EnumerationType getEnumerationType(DataTypePackage dataTypePackage, String name) {
		return dataTypePackage.getEnumerationType(name);
	}

	@Override
	public Collection<EnumerationType> getEnumerationTypes(DataTypePackage dataTypePackage) {
		return dataTypePackage.getEnumerationTypes();
	}

	@Override
	public Collection<EnumerationType> getAllEnumerationTypes() {
		Collection<EnumerationType> enums = new ArrayList<EnumerationType>();
		for(DataTypePackage p : getAllDataTypePackages()) {
			enums.addAll(getEnumerationTypes(p));
		}
		return enums;
	}

	@Override
	public RequirementType createRequirementType(String name) {
		RequirementType element = new RequirementTypeImpl(name);
		return element;
	}

	@Override
	public EnumerationType createEnumerationType(String name) {
		EnumerationType element = new EnumerationTypeImpl(name);
		return element;
	}

	@Override
	public EnumeratorType createEnumeratorType(String name) {
		EnumeratorType enumeratorType = new EnumeratorTypeImpl(name);
		return enumeratorType;
	}

	@Override
	public RequirementTypeAttribute createAttributeType(String name, EDataType type) {
		RequirementTypeAttribute attributeType = new RequirementTypeAttributeImpl(name, type);
		return attributeType;
	}

	public RequirementTypeAttribute createAttributeType(String name, EnumerationType type) {
		return createAttributeType(name, type.getEDataType());
	}
	
	@Override
	public Scope createScope(String name) {
		Scope scope = DataModelFactory.eINSTANCE.createScope();
		scope.setName(name);
		return scope;
	}

	@Override
	public void addScopes(DataTypePackage dataTypePackage, Scope... scopes) {
		for(Scope scope : scopes) {
			dataTypePackage.add(scope);
		}
	}

	@Override
	public Scope getScope(DataTypePackage dataTypePackage, String name) {
		return dataTypePackage.getScope(name);
	}

	@Override
	public Collection<Scope> getScopes(DataTypePackage dataTypePackage) {
		return dataTypePackage.getScopes();
	}

	@Override
	public Collection<Scope> getAllScopes() {
		Collection<Scope> scopes = new ArrayList<Scope>();
		for(DataTypePackage p : getAllDataTypePackages()) {
			scopes.addAll(getScopes(p));
		}
		return scopes;
	}

	@Override
	public DataTypePackage getDataModel(Scope scope) {
		EObject container = scope.eContainer();
		if(container instanceof EAnnotation) {
			EAnnotation annotation = (EAnnotation)container;
			container = annotation.eContainer();
			if(container instanceof EPackage) {
				EPackage p = (EPackage)container;
				return getDataTypePackage(p.getName());
			}
		}
		return null;
	}

	@Override
	public Scope getScope(String DataModelName, String ScopeName) {
		DataTypePackage model = getDataTypePackage(DataModelName);
		if(model == null) {
			return null;
		}
		
		return model.getScope(ScopeName);
	}
	
	@Override
	public Collection<Scope> getScopes(String name) {
		ArrayList<Scope> scopes = new ArrayList<Scope>();
		for(DataTypePackage p : getAllDataTypePackages()) {
			scopes.add(p.getScope(name));
		}
		return scopes;
	}

	@Override
	public Scope getAnalysisScope() {
		Scope scope = dataTypePackage.getScope("Analysis");
		if(scope == null) {
			scope = createScope("Analysis");
			dataTypePackage.add(scope);
			save();
		}
		return scope;
	}

}
