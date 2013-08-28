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
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataModel;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.internal.DataModelImpl;
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
	protected DataModel dataModel;

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
			dataModel = new DataModelImpl((EPackage)conf);
		} else {
			dataModel = new DataModelImpl();
			save();
		}
		
		registerModel(dataModel);
		
		for (DataModel model : dataModel.getSubDataModels()) {
			registerModel(model);
		}
	}

	@Override
	public void discardUnsavedChanges() {
		initTypes();
	}

	@Override
	public void save() {
		try {
			EPackage ePackage = ((DataModelImpl)dataModel).getEPackage();
			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, rs);
		} catch (IOException e) {
			// FIXME : use logger
			e.printStackTrace();
		}
	}

	@Override
	public EObject createInstance(RequirementType dataType) {
		return dataModel.create(dataType);
	}

	@Override
	public DataModel createDataModel(String name) {
		DataModel dataModel = new DataModelImpl(name);
		addDataModel(dataModel);
		return dataModel;
	}

	@Override
	public void addDataModel(DataModel p) {
		if(p == null) {
			return;
		}
		registerModel(p);
		dataModel.add(p);
	}

	private void registerModel(DataModel dataModel) {
		EPackage p = ((DataModelImpl)dataModel).getEPackage();
		if(p != null && p.getNsURI() != null) {
			Registry.INSTANCE.put(p.getNsURI(), p);
		}
	}

	@Override
	public void addDataTypes(DataModel dataModel, DataType... types){
		for(DataType type : types) {
			dataModel.add(type);
		}
	}
	
	@Override
	public void addRequirementTypes(DataModel dataModel, RequirementType... types) {
		for(RequirementType dataType : types) {
			dataModel.add(dataType);
		}
	}

	@Override
	public void addEnumerationTypes(DataModel dataModel, EnumerationType... enumerationTypes) {
		for(EnumerationType enumerationType : enumerationTypes) {
			dataModel.add(enumerationType);
		}
	}

	@Override
	public DataModel getDataModel(String name) {
		Assert.isNotNull(dataModel);
		return dataModel.getDataTypePackage(name);
	}

	@Override
	public Collection<DataModel> getAllDataModels() {
		Assert.isNotNull(dataModel);
		return dataModel.getSubDataModels();
	}

	@Override
	public RequirementType getDataType(DataModel dataModel, String name) {
		return dataModel.getDataType(name);
	}

	@Override
	public Collection<RequirementType> getDataTypes(DataModel dataModel) {
		return dataModel.getDataTypes();
	}

	@Override
	public Collection<RequirementType> getAllDataTypes() {
		Assert.isNotNull(dataModel);
		Collection<RequirementType> types = new ArrayList<RequirementType>();
		for(DataModel p : getAllDataModels()) {
			types.addAll(getDataTypes(p));
		}
		return types;
	}

	@Override
	public EnumerationType getEnumerationType(DataModel dataModel, String name) {
		return dataModel.getEnumerationType(name);
	}

	@Override
	public Collection<EnumerationType> getEnumerationTypes(DataModel dataModel) {
		return dataModel.getEnumerationTypes();
	}

	@Override
	public Collection<EnumerationType> getAllEnumerationTypes() {
		Collection<EnumerationType> enums = new ArrayList<EnumerationType>();
		for(DataModel p : getAllDataModels()) {
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
	public void addScopes(DataModel dataModel, Scope... scopes) {
		for(Scope scope : scopes) {
			dataModel.add(scope);
		}
	}

	@Override
	public Scope getScope(DataModel dataModel, String name) {
		return dataModel.getScope(name);
	}

	@Override
	public Collection<Scope> getScopes(DataModel dataModel) {
		return dataModel.getScopes();
	}

	@Override
	public Collection<Scope> getAllScopes() {
		Collection<Scope> scopes = new ArrayList<Scope>();
		for(DataModel p : getAllDataModels()) {
			scopes.addAll(getScopes(p));
		}
		return scopes;
	}

	@Override
	public DataModel getDataModel(Scope scope) {
		EObject container = scope.eContainer();
		if(container instanceof EAnnotation) {
			EAnnotation annotation = (EAnnotation)container;
			container = annotation.eContainer();
			if(container instanceof EPackage) {
				EPackage p = (EPackage)container;
				return getDataModel(p.getName());
			}
		}
		return null;
	}

	@Override
	public Scope getScope(String DataModelName, String ScopeName) {
		DataModel model = getDataModel(DataModelName);
		if(model == null) {
			return null;
		}
		
		return model.getScope(ScopeName);
	}
	
	@Override
	public Collection<Scope> getScopes(String name) {
		ArrayList<Scope> scopes = new ArrayList<Scope>();
		for(DataModel p : getAllDataModels()) {
			scopes.add(p.getScope(name));
		}
		return scopes;
	}

	@Override
	public Scope getAnalysisScope() {
		Scope scope = dataModel.getScope("Analysis");
		if(scope == null) {
			scope = createScope("Analysis");
			dataModel.add(scope);
			save();
		}
		return scope;
	}

}
