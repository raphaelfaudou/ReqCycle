package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;

public interface IDataTypeManager {

	public void saveTypes();
	
	public void addType(EClassifier eClassifier);
	
	public void removeType(EClassifier eClassifier);
	
	public EClassifier getType(String name);
	
	public boolean isAvailable(String name);

	public Collection<EClassifier> getTypes();
	
	public Collection<EClassifier> getEEnums();
	
	public void loadTypes();
}
