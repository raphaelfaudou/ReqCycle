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
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.IScopeManager;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import DataModel.Contained;
import DataModel.DataModelFactory;
import DataModel.Scope;
import ScopesConf.Scopes;
import ScopesConf.ScopesConfFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Singleton
public class ScopeManagerImpl implements IScopeManager {

	private @Inject
	ILogger logger = ZigguratInject.make(ILogger.class);

	private static final String SCOPE_MODEL_ECORE = "org.eclipse.reqcycle.repository.data/model/DataModel.ecore";

	private static final String CUSTOM_DATA_MODEL = "org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore";
	
	private Scopes scopes;

	private String id = "org.eclipse.reqcycle.repository.scopes";

	private @Inject
	static IConfigurationManager confManager;



	ScopeManagerImpl() {
		Scopes confScopes = null;
		confManager = ZigguratInject.make(IConfigurationManager.class);
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, id);
		if(conf instanceof Scopes) {
			confScopes = (Scopes)conf;
		} else {
			confScopes = ScopesConfFactory.eINSTANCE.createScopes();
		}

		ResourceSet resourceSet = new ResourceSetImpl();

		scopes = confScopes;

		//FIXME : customer and system scope has been deleted from DataModel, scope are added by the user and saved in the workspace
//		for(EClassifier eClassifier :  getScopes(URI.createPlatformPluginURI(SCOPE_MODEL_ECORE, true), resourceSet)) {
//			if(!contains(scopes.getScopes(), eClassifier)) {
//				scopes.getScopes().add((Scope)DataModelFactory.eINSTANCE.create((EClass)eClassifier));
//			}
//		}

//		for(EClassifier eClassifier :  getScopes(URI.createPlatformPluginURI(CUSTOM_DATA_MODEL, true), resourceSet)) {
//			if(!contains(scopes.getScopes(), eClassifier)) {
//				scopes.getScopes().add((Scope)CustomDataModelFactory.eINSTANCE.create((EClass)eClassifier));
//			}
//		}
		if(scopes.getScopes().isEmpty()) {
			Scope scopeCustomer = DataModelFactory.eINSTANCE.createScope();
			Scope scopeSystem = DataModelFactory.eINSTANCE.createScope();
			scopeCustomer.setName("Customer");
			scopeSystem.setName("System");
			scopes.getScopes().add(scopeSystem);
			scopes.getScopes().add(scopeCustomer);
			save();
			
		}
		
	}


	/**
	 * @param eClassifiers
	 * @param uri
	 * @param resourceSet
	 * @return
	 * @deprecated Scope has to be added with a user wizard
	 */
	//TODO : remove this method
	private Collection<EClassifier> getScopes(URI uri, ResourceSet resourceSet) {
		Collection<EClassifier> eClassifiers = null;
		Resource attributeModelResource = resourceSet.getResource(uri, true);

		EList<EObject> modelContent = attributeModelResource.getContents();

		if(modelContent.size() > 0) {
			EObject rootElement = modelContent.get(0);
			if(rootElement instanceof EPackage) {
				EList<EClassifier> classifiers = ((EPackage)rootElement).getEClassifiers();
				eClassifiers = Collections2.filter(classifiers, new Predicate<EClassifier>() {
					@Override
					public boolean apply(EClassifier arg0) {
						//TODO : trouver un moyen de verifier qu'ils sont des sous types de Scope
						if(arg0 instanceof EClass) {
							EList<EClass> superTypes = ((EClass)arg0).getEAllSuperTypes();
							for(EClass superType : superTypes) {
								if("Scope".equals(superType.getName())) {
									return true;
								}
							}
						}
						return false;
					}
				});
			}
		}
		return eClassifiers;
	}


	/**
	 * @param scopes2
	 * @param eClassifier
	 * @return
	 * @deprecated to remove
	 */
	//TODO remove method
	private boolean contains(EList<Scope> scopes, EClassifier eClassifier) {
		for(Scope scope : scopes) {
			if(scope.eClass().getName().equals(eClassifier.getName())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public Collection<Scope> getAllScopes() {
		return scopes.getScopes();
	}

	@Override
	public void addToScope(Scope scope, Contained element) {
		element.getScopes().add(scope);
	}

	public void addToScope(Scope scope, Collection<Contained> elements) {
		for(Contained contained : elements) {
			addToScope(scope, contained);
		}
	}
	
	@Override
	public void removeFromScopes(Contained contained) {
		contained.getScopes().clear();
	}
	
	public void removeFromScope(Scope scope, Contained contained) {
		contained.getScopes().remove(scope);
	}
	
	public void save() {
		try {
			confManager.saveConfiguration(scopes, null, IConfigurationManager.Scope.WORKSPACE, id);
		} catch (IOException e) {
			//FIXME use logger
			e.printStackTrace();
		}
	}
	

}
