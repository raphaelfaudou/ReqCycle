/*****************************************************************************
 * Copyright (c) 2013 AtoS.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Anass RADOUANI (AtoS) anass.radouani@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.reqcycle.repository.data.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.reqcycle.repository.data.IDataModelManager;
import org.eclipse.reqcycle.repository.data.types.IAttribute;
import org.eclipse.reqcycle.repository.data.types.IAttributeType;
import org.eclipse.reqcycle.repository.data.types.IDataModel;
import org.eclipse.reqcycle.repository.data.types.IEnumerationType;
import org.eclipse.reqcycle.repository.data.types.IEnumerator;
import org.eclipse.reqcycle.repository.data.types.IRequirementType;
import org.eclipse.reqcycle.repository.data.types.internal.AttributeImpl;
import org.eclipse.reqcycle.repository.data.types.internal.DataModelImpl;
import org.eclipse.reqcycle.repository.data.types.internal.EnumerationTypeImpl;
import org.eclipse.reqcycle.repository.data.types.internal.EnumeratorImpl;
import org.eclipse.reqcycle.repository.data.types.internal.RequirementTypeImpl;
import org.eclipse.ziggurat.configuration.IConfigurationManager;

import ScopeConf.Scope;
import ScopeConf.ScopeConfFactory;

@Singleton
public class DataModelManagerImpl implements IDataModelManager {

	/** EPackage containing possible data types */
	protected IDataModel dataModel;

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
			EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
			ePackage.setName("DataModels");
			ePackage.setNsPrefix("DataModels");
			ePackage.setNsURI(MODEL_NS_URI);
			dataModel = new DataModelImpl(ePackage);
			save();
		}

		registerModel(dataModel);

		for(IDataModel model : ((DataModelImpl)dataModel).getSubDataModels()) {
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
			EPackage ePackage = null;

			if(dataModel instanceof IAdaptable) {
				ePackage = (EPackage)((IAdaptable)dataModel).getAdapter(EPackage.class);
			}

			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, rs);
		} catch (IOException e) {
			// FIXME : use logger
			e.printStackTrace();
		}
	}

	@Override
	public IDataModel createDataModel(String name) {
		IDataModel dataModel = new DataModelImpl(name);
		addDataModel(dataModel);
		return dataModel;
	}

	@Override
	public void addDataModel(IDataModel p) {
		if(p == null) {
			return;
		}
		registerModel(p);
		((DataModelImpl)dataModel).addDataModel(p);
	}

	protected void registerModel(IDataModel dataModel) {
		EPackage ePackage = null;
		if(dataModel instanceof IAdaptable) {
			ePackage = (EPackage)((IAdaptable)dataModel).getAdapter(EPackage.class);
		}

		if(ePackage != null && ePackage.getNsURI() != null) {
			Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);
		}
	}

	@Override
	public void addRequirementTypes(IDataModel dataModel, IRequirementType... types) {
		for(IRequirementType type : types) {
			dataModel.addRequirementType(type);
		}
	}

	@Override
	public void addEnumerationTypes(IDataModel dataModel, IEnumerationType... enumerationTypes) {
		for(IEnumerationType enumerationType : enumerationTypes) {
			dataModel.addEnumerationType(enumerationType);
		}
	}

	@Override
	public IDataModel getDataModel(String name) {
		Assert.isNotNull(dataModel);
		return ((DataModelImpl)dataModel).getSubDataModel(name);
	}

	@Override
	public Collection<IDataModel> getAllDataModels() {
		Assert.isNotNull(dataModel);
		return ((DataModelImpl)dataModel).getSubDataModels();
	}

	@Override
	public Collection<IRequirementType> getAllRequirementTypes() {
		Assert.isNotNull(dataModel);
		Collection<IRequirementType> types = new ArrayList<IRequirementType>();
		for(IDataModel dataModel : getAllDataModels()) {
			types.addAll(dataModel.getRequirementTypes());
		}
		return types;
	}

	@Override
	public Collection<IEnumerationType> getAllEnumerationTypes() {
		Collection<IEnumerationType> enums = new ArrayList<IEnumerationType>();
		for(IDataModel dataModel : getAllDataModels()) {
			enums.addAll(dataModel.getEnumerationTypes());
		}
		return enums;
	}

	@Override
	public IRequirementType createRequirementType(String name) {
		IRequirementType element = new RequirementTypeImpl(name);
		return element;
	}

	@Override
	public IEnumerationType createEnumerationType(String name) {
		IEnumerationType element = new EnumerationTypeImpl(name);
		return element;
	}

	@Override
	public IEnumerator createEnumerator(String name) {
		IEnumerator enumeratorType = new EnumeratorImpl(name);
		return enumeratorType;
	}

	@Override
	public IAttribute createAttribute(String name, IAttributeType type) {
		return new AttributeImpl(name, type);
	}

	@Override
	public IAttribute createAttribute(String name, IEnumerationType enumerationType) {
		IAttributeType attributeType = null;

		if(enumerationType instanceof IAdaptable) {
			attributeType = (IAttributeType)((IAdaptable)enumerationType).getAdapter(IAttributeType.class);
		}

		return new AttributeImpl(name, attributeType);
	}

	@Override
	public Scope createScope(String name) {
		Scope scope = ScopeConfFactory.eINSTANCE.createScope();
		scope.setName(name);
		return scope;
	}

	@Override
	public void addScopes(IDataModel dataModel, Scope... scopes) {
		for(Scope scope : scopes) {
			dataModel.addScope(scope);
		}
	}

	@Override
	public Collection<Scope> getAllScopes() {
		Collection<Scope> scopes = new ArrayList<Scope>();
		for(IDataModel dataModel : getAllDataModels()) {
			scopes.addAll(dataModel.getScopes());
		}
		return scopes;
	}

	@Override
	public IDataModel getDataModel(Scope scope) {
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
	public Collection<Scope> getScopes(String name) {
		ArrayList<Scope> scopes = new ArrayList<Scope>();
		for(IDataModel p : getAllDataModels()) {
			scopes.add(p.getScope(name));
		}
		return scopes;
	}

	@Override
	public Scope getAnalysisScope() {
		Scope scope = dataModel.getScope("Analysis");
		if(scope == null) {
			scope = createScope("Analysis");
			dataModel.addScope(scope);
			save();
		}
		return scope;
	}

	@Override
	public Collection<IDataModel> getDataModel(URI uri) {

		Collection<IDataModel> dataModels = new ArrayList<IDataModel>();
		Resource resource = rs.getResource(uri, true);
		EList<EObject> content = resource.getContents();
		for(EObject eObject : content) {
			if(eObject instanceof EPackage) {
				EList<EPackage> eSubpackages = ((EPackage)eObject).getESubpackages();
				if(eSubpackages != null && !eSubpackages.isEmpty()) {
					for(EPackage ePackage : eSubpackages) {
						dataModels.add(new DataModelImpl(ePackage));
					}
				} else {
					dataModels.add(new DataModelImpl((EPackage)eObject));
				}
			}
		}

		resource.unload();

		return dataModels;
	}

}
