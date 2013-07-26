package org.eclipse.reqcycle.repository.data.impl;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.reqcycle.core.ILogger;
import org.eclipse.reqcycle.repository.data.IDataTypeManager;
import org.eclipse.ziggurat.configuration.IConfigurationManager;
import org.eclipse.ziggurat.inject.ZigguratInject;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Singleton
public class DataTypeManagerImpl implements IDataTypeManager {

	/** EPackage containing possible data types */
	private EPackage ePackage;
	
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
		confManager = ZigguratInject.make(IConfigurationManager.class);
		EObject conf = loadEpackage();
		if(conf instanceof EPackage) {
			ePackage = (EPackage)conf;
		} else {
			initEpackage();
		}
		
		//TODO : remove ePackage init
		if (ePackage.getEClassifiers().isEmpty()){
			ePackage.getEClassifiers().addAll(getInitClassifiers(ePackage.eResource() != null ? ePackage.eResource().getResourceSet() : null));
			saveTypes();
		}
		
	}

	//FIXME : remove method
	private Collection<? extends EClassifier> getInitClassifiers(ResourceSet rs) {
		if(rs == null) {
			rs = new ResourceSetImpl();
		}
		
		Resource resource = rs.getResource(URI.createPlatformPluginURI("/org.eclipse.reqcycle.repository.data/model/CustomDataModel.ecore", true), true);
		EList<EObject> contents = resource.getContents();
		if(contents.size()>0) {
			EObject content = contents.get(0);
			if(content instanceof EPackage) {
				return ((EPackage)content).getEClassifiers();
				
			}
		}
		return null;
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
			confManager.saveConfiguration(ePackage, null, IConfigurationManager.Scope.WORKSPACE, CONF_ID);
		} catch (IOException e) {
			e.printStackTrace();
			//TODO : use logger
		}
	}
	
	public void addType(EClassifier eClassifier) {
		Assert.isNotNull(eClassifier);
		ePackage.getEClassifiers().add(eClassifier);
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
	
    public Collection<EClass> getTypes() {
        Collection<EClassifier> eClasses = Collections2.filter(ePackage.getEClassifiers(), new Predicate<EClassifier>() {

            @Override
            public boolean apply(EClassifier arg0) {
                return arg0 instanceof EClass;
            }
        });
        
        return Collections2.transform(eClasses, new Function<EClassifier, EClass>() {
            @Override
            public EClass apply(EClassifier arg0) {
                return (EClass)arg0;
            }
        });
    }

    public void addTypes(Collection<EClassifier> types) {
        ePackage.getEClassifiers().addAll(types);
    }

    @Override
    public Collection<EEnum> getEEnums() {

        Collection<EClassifier> eEnums = Collections2.filter(ePackage.getEClassifiers(), new Predicate<EClassifier>() {
            @Override
            public boolean apply(EClassifier arg0) {
                return arg0 instanceof EEnum;
            }
        });
        
        return Collections2.transform(eEnums, new Function<EClassifier, EEnum>() {
            @Override
            public EEnum apply(EClassifier arg0) {
                return (EEnum)arg0;
            }
        });
    }
}
