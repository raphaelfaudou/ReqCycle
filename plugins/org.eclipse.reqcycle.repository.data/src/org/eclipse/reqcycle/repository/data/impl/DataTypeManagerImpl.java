package org.eclipse.reqcycle.repository.data.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.impl.internal.RequirementTypeAttributeImpl;
import org.eclipse.reqcycle.repository.data.types.impl.internal.RequirementTypeImpl;
import org.eclipse.reqcycle.repository.data.types.impl.internal.DataTypePackageImpl;
import org.eclipse.reqcycle.repository.data.types.impl.internal.EnumerationTypeImpl;
import org.eclipse.reqcycle.repository.data.types.impl.internal.EnumeratorTypeImpl;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.DataModelFactory;
import DataModel.Scope;

@Singleton
public class DataTypeManagerImpl implements IDataTypeManager {

	/** EPackage containing possible data types */
	private DataTypePackage dataTypePackage;

	/** Configuration Manager */
	@Inject
	private IConfigurationManager confManager;

	/** Configuration ID */
	final static String CONF_ID = "org.eclipse.reqcycle.data.dataTypes";

	/**
	 * Constructor
	 */
	DataTypeManagerImpl() {
		if(confManager == null) {
			confManager = ZigguratInject.make(IConfigurationManager.class);
		}
		initTypes();
	}

	private void initTypes() {
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, true);
		if(conf instanceof EPackage) {
			dataTypePackage = new DataTypePackageImpl((EPackage)conf);
		} else {
			dataTypePackage = new DataTypePackageImpl("DataTypes");
		}
	}

	public void discardUnsavedChanges() {
		initTypes();
	}


	public void saveTypes() {
		try {
			EPackage ePackage = ((DataTypePackageImpl)dataTypePackage).getEPackage();
			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID);
		} catch (IOException e) {
			// TODO : use logger
			e.printStackTrace();
		}
	}

	public EObject createInstance(RequirementType dataType) {
		return dataTypePackage.create(dataType);
	}

	public DataTypePackage createDataTypePackage(String name) {
		DataTypePackage dataTypePackage = new DataTypePackageImpl(name);
		this.dataTypePackage.add(dataTypePackage);
		return dataTypePackage;
	}

	public void addDataTypePackage(DataTypePackage p) {
		dataTypePackage.add(p);
	}

	public void addDataType(DataTypePackage dataTypePackage, DataType type){
		dataTypePackage.add(type);
	}
	
	public void addRequirementType(DataTypePackage dataTypePackage, RequirementType Type) {
		dataTypePackage.add(Type);
	}

	public void addRequirementTypes(DataTypePackage dataTypePackage, Collection<RequirementType> types) {
		for(RequirementType dataType : types) {
			dataTypePackage.add(dataType);
		}
	}

	public void addEnumerationType(DataTypePackage dataTypePackage, EnumerationType enumerationType) {
		dataTypePackage.add(enumerationType);
	}

	public void addEnumerationTypes(DataTypePackage dataTypePackage, Collection<EnumerationType> types) {
		for(EnumerationType enumerationType : types) {
			dataTypePackage.add(enumerationType);
		}
	}

	public DataTypePackage getDataTypePackage(String name) {
		Assert.isNotNull(dataTypePackage);
		return dataTypePackage.getDataTypePackage(name);
	}

	public Collection<DataTypePackage> getAllDataTypePackages() {
		Assert.isNotNull(dataTypePackage);
		return dataTypePackage.getDataTypePackages();
	}

	public RequirementType getDataType(DataTypePackage dataTypePackage, String name) {
		return dataTypePackage.getDataType(name);
	}

	public Collection<RequirementType> getDataTypes(DataTypePackage dataTypePackage) {
		return dataTypePackage.getDataTypes();
	}

	public Collection<RequirementType> getAllDataTypes() {
		Assert.isNotNull(dataTypePackage);
		Collection<RequirementType> types = new ArrayList<RequirementType>();
		for(DataTypePackage p : getAllDataTypePackages()) {
			types.addAll(getDataTypes(p));
		}
		return types;
	}

	public EnumerationType getEnumerationType(DataTypePackage dataTypePackage, String name) {
		return dataTypePackage.getEnumerationType(name);
	}


	public Collection<EnumerationType> getEnumerationTypes(DataTypePackage dataTypePackage) {
		return dataTypePackage.getEnumerationTypes();
	}

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

	@Override
	public Scope createScope(String name) {
		Scope scope = DataModelFactory.eINSTANCE.createScope();
		scope.setName(name);
		return scope;
	}

	@Override
	public void addScope(DataTypePackage dataTypePackage, Scope scope) {
		dataTypePackage.add(scope);
	}

	@Override
	public void addScopes(DataTypePackage dataTypePackage, Collection<Scope> scopes) {
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

}
