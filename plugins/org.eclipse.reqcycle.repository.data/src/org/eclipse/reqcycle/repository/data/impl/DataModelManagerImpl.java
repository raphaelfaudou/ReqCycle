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
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.reqcycle.repository.data.IDataManager;
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

import RequirementSourceConf.RequirementSource;
import ScopeConf.Scope;
import ScopeConf.ScopeConfFactory;
import ScopeConf.Scopes;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Singleton
public class DataModelManagerImpl implements IDataModelManager {

	/** EPackage containing possible data types */
	protected IDataModel dataModel;

	/** Configuration Manager */
	@Inject
	IConfigurationManager confManager;

	/** Configuration ID */
	final static String CONF_ID = "org.eclipse.reqcycle.data.dataTypes";

	final static String SCOPES_CONF_ID = "org.eclipse.reqcycle.data.scopes";

	@Inject
	@Named("confResourceSet")
	protected ResourceSet rs;

	protected Scopes scopes;

	@Inject
	protected IDataManager dataManager;

	/**
	 * Constructor
	 */
	@Inject
	DataModelManagerImpl(@Named("confResourceSet") ResourceSet rs, IConfigurationManager confManager, IDataManager dataManager) {
		this.rs = rs;
		this.confManager = confManager;
		this.dataManager = dataManager;

		initTypes();
		initScopes();
	}

	protected void initScopes() {
		Collection<EObject> conf = confManager.getConfiguration(null, null, SCOPES_CONF_ID, rs, true);
		EObject element = null;
		if(conf != null && !conf.isEmpty()) {
			element = conf.iterator().next();
		}
		if(element instanceof Scopes) {
			scopes = ((Scopes)element);
		} else {
			scopes = ScopeConfFactory.eINSTANCE.createScopes();
			saveScopes();
		}
	}

	protected void initTypes() {
		Collection<EObject> conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, rs, true);
		EObject element = null;
		if(conf != null && !conf.isEmpty()) {
			element = conf.iterator().next();
		}
		EPackage ePackage;
		if(element instanceof EPackage) {
			ePackage = (EPackage)element;
			dataModel = new DataModelImpl(ePackage);
		} else {
			ePackage = EcoreFactory.eINSTANCE.createEPackage();
			ePackage.setName("DataModels");
			ePackage.setNsPrefix("DataModels");
			ePackage.setNsURI(MODEL_NS_URI);
			dataModel = new DataModelImpl(ePackage);
			saveDataModels();
		}
		registerDataModels(ePackage);
	}

	@Override
	public void discardUnsavedChanges() {
		initTypes();
	}

	@Override
	public void save() {
		saveDataModels();
		saveScopes();
	}

	protected void saveScopes() {
		try {
			confManager.saveConfiguration(scopes, null, null, SCOPES_CONF_ID, rs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void saveDataModels() {
		try {
			EPackage ePackage = null;
			if(dataModel instanceof IAdaptable) {
				ePackage = (EPackage)((IAdaptable)dataModel).getAdapter(EPackage.class);
			}
			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID, rs);
			registerDataModels(ePackage);
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
		if(getDataModel(p.getName()) != null) {
			throw new RuntimeException("A data model with the same name already exists.");
		}
		((DataModelImpl)dataModel).addDataModel(p);
	}


	protected void registerDataModels(EPackage ePackage) {
		Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);
		for(EPackage p : ePackage.getESubpackages()) {
			Registry.INSTANCE.put(p.getNsURI(), p);
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
	public Scope createScope(String name, IDataModel dataModel) {
		Scope scope = ScopeConfFactory.eINSTANCE.createScope();
		scope.setName(name);
		scope.setDataModelURI(dataModel.getDataModelURI());
		return scope;
	}

	@Override
	public void addScopes(IDataModel dataModel, Scope... scopes) {
		for(Scope scope : scopes) {
			this.scopes.getScopes().add(scope);
		}
	}

	@Override
	public Collection<Scope> getAllScopes() {
		return scopes.getScopes();
	}

	@Override
	public Collection<Scope> getScopes(final IDataModel dataModel) {
		return Collections2.filter(scopes.getScopes(), new Predicate<Scope>() {

			@Override
			public boolean apply(Scope arg0) {
				if(arg0.getDataModelURI().equals(dataModel.getDataModelURI())) {
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public Scope getScope(String name, IDataModel dataModel) {
		for(Scope s : scopes.getScopes()) {
			if(s.getDataModelURI().equals(dataModel.getDataModelURI()) && s.getName().equals(name)) {
				return s;
			}
		}
		return null;
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

	@Override
	public boolean isUsed(IDataModel dataModel) {
		dataManager.load();
		String dataModelURI = dataModel.getDataModelURI();
		Set<RequirementSource> sources = dataManager.getRequirementSources();
		for(RequirementSource requirementSource : sources) {
			if(dataModelURI.equals(requirementSource.getDataModelURI())) {
				//				return true;
			}
		}
		for(IRequirementType type : dataModel.getRequirementTypes()) {
			EClass eClass = null;
			if(type instanceof IAdaptable) {

				eClass = (EClass)((IAdaptable)type).getAdapter(EClass.class);
				ECrossReferenceAdapter c = ECrossReferenceAdapter.getCrossReferenceAdapter(eClass);
				if(c == null) {
					c = new ECrossReferenceAdapter();
				}
				c.setTarget(rs);

				Collection<Setting> settings = c.getInverseReferences(eClass, true);
				for(Setting s : settings) {
					System.out.println(s);
				}
			}
		}

		for(Scope scope : dataModel.getScopes()) {

			ECrossReferenceAdapter c = ECrossReferenceAdapter.getCrossReferenceAdapter(scope);
			if(c == null) {
				c = new ECrossReferenceAdapter();
			}
			c.setTarget(rs);

			Collection<Setting> settings = c.getInverseReferences(scope, true);
			for(Setting s : settings) {
				System.out.println(s);
			}
		}
		return false;
	}


	@Override
	public boolean isEmpty(IDataModel dataModel) {
		return dataModel.getEnumerationTypes().isEmpty() && dataModel.getRequirementTypes().isEmpty() && dataModel.getScopes().isEmpty();
	}

}
