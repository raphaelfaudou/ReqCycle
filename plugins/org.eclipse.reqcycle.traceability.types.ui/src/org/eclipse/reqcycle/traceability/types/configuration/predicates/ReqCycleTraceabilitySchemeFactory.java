package org.eclipse.reqcycle.traceability.types.configuration.predicates;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class ReqCycleTraceabilitySchemeFactory implements Factory {

	@Override
	public Resource createResource(URI uri) {
		try {
			EPackage epackage = ReqCycleDynamicPackage.getEPackage();
			Resource r = new EcoreResourceFactoryImpl().createResource(uri);
			r.getContents().add(epackage);
			return r;

		} catch (EPackageCreationException e) {
		}
		return null;
	}

}
