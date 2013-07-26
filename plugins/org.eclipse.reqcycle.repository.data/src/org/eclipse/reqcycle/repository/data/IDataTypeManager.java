package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;

public interface IDataTypeManager {

    public void saveTypes();

    public void addType(EClassifier eClassifier);

    public void removeType(EClassifier eClassifier);

    public EClassifier getType(String name);

    public boolean isAvailable(String name);

    public Collection<EClass> getTypes();

    public Collection<EEnum> getEEnums();

    public void loadTypes();
}
