package org.eclipse.reqcycle.repository.data.types;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import DataModel.Scope;

public interface DataModel {

	public String getName();

	public EObject create(RequirementType type);

	public void add(DataModel dataTypePackage);

	public void add(Scope scope);

	public void add(RequirementType type);

	public void add(EnumerationType type);

	public void add(DataType type);

	public DataModel getDataTypePackage(String name);

	public Collection<DataModel> getSubDataModels();

	public EnumerationType getEnumerationType(String name);

	public RequirementType getDataType(String name);

	public Scope getScope(String name);

	public Collection<RequirementType> getDataTypes();

	public Collection<EnumerationType> getEnumerationTypes();

	public Collection<Scope> getScopes();
	
}
