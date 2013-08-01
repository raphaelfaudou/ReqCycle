package org.eclipse.reqcycle.repository.data.types;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import DataModel.Scope;

public interface DataTypePackage {

	public String getName();

	public EObject create(RequirementType type);


	public void add(DataTypePackage dataTypePackage);

	public void add(Scope scope);

	public void add(RequirementType type);

	public void add(EnumerationType type);

	public void add(DataType type);


	public DataTypePackage getDataTypePackage(String name);

	public Collection<DataTypePackage> getDataTypePackages();

	public EnumerationType getEnumerationType(String name);

	public RequirementType getDataType(String name);

	public Scope getScope(String name);

	public Collection<RequirementType> getDataTypes();

	public Collection<EnumerationType> getEnumerationTypes();

	public Collection<Scope> getScopes();

}
