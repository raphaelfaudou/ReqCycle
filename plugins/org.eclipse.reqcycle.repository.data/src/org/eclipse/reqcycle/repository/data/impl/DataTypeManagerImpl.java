package org.eclipse.reqcycle.repository.data.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Singleton
public class DataTypeManagerImpl implements IDataTypeManager {

	/** EPackage containing possible data types */
	private EPackage ePackage;
	private EPackage ePackageBak;
	
	/** Configuration Manager */
	@Inject
	private IConfigurationManager confManager;
	
	/** Configuration ID */
	final static String CONF_ID =  "org.eclipse.reqcycle.data.dataTypes";
	
	@Inject
	private ILogger logger;

	
	/**
	 * Constructor
	 */
	DataTypeManagerImpl() {
		if(confManager == null) {
			confManager = ZigguratInject.make(IConfigurationManager.class);
		}
		loadTypes();
	}

	public void loadTypes() {
		if(ePackageBak != null) {
			ePackage = ePackageBak;
			saveTypes();
			return;
		}
		
		EObject conf = loadEpackage();
		if(conf instanceof EPackage) {
			ePackage = (EPackage)conf;
			ePackageBak = EcoreUtil.copy(ePackage);
		} else {
			initEpackage();
		}
	}

	private EObject loadEpackage() {
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, CONF_ID);
		return conf;
	}

	private void initEpackage() {
		ePackage = EcoreFactory.eINSTANCE.createEPackage();
		ePackage.setName("DataTypes");
		ePackage.setNsPrefix("DataTypes");
		ePackage.setNsURI("http://www.eclipse.org/ReqCycle/DataType");
	}

	public void saveTypes() {
		try {
			ePackageBak = EcoreUtil.copy(ePackage);
			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID);
		} catch (IOException e) {
			//TODO : use logger
			e.printStackTrace();
		}
	}
	
	public void addType(EClassifier eClassifier) {
		Assert.isNotNull(eClassifier);
		ePackage.getEClassifiers().add(eClassifier);
		EObject conf = confManager.getConfiguration(null, IConfigurationManager.Scope.WORKSPACE, CONF_ID);
	}
	
	public void removeType(EClassifier eClassifier) {
		Assert.isNotNull(ePackage);
		ePackage.getEClassifiers().remove(eClassifier);
	}
	
	public boolean isAvailable(String name) {
		Assert.isNotNull(ePackage);
		return ePackage.getEClassifier(name) == null;
	}
	
	public EClassifier getType(String name) {
		Assert.isNotNull(ePackage);
		return ePackage.getEClassifier(name);
	}

	public EObject create(EClass eClass) {
		Assert.isNotNull(ePackage);
		return ePackage.getEFactoryInstance().create(eClass);
	}
	
	public Collection<EClassifier> getTypes() {
		return Collections2.filter(ePackage.getEClassifiers(), new Predicate<EClassifier>() {
			@Override
			public boolean apply(EClassifier arg0) {
				return arg0 instanceof EClass;
			}
		});
	}

	public void addTypes(Collection<EClassifier> types) {
		ePackage.getEClassifiers().addAll(types);
	}

	@Override
	public Collection<EClassifier> getEEnums() {
		return Collections2.filter(ePackage.getEClassifiers(), new Predicate<EClassifier>() {
			@Override
			public boolean apply(EClassifier arg0) {
				return arg0 instanceof EEnum;
			}
		});
	}
	
}
