package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

public interface DataTypePackage {
	
	public String getName();

	public EObject create(RequirementType dataType);

	public void add(DataTypePackage dataTypePackage);

	public void add(RequirementType dataType);
	
	public void add(EnumerationType enumerationType);
	
	public DataTypePackage getDataTypePackage(String name);

	public Collection<DataTypePackage> getDataTypePackages();

	public EnumerationType getEnumerationType(String name);

	public RequirementType getDataType(String name);

	public Collection<RequirementType> getAllDataTypes();

	public Collection<EnumerationType> getAllEnumerationTypes();
	
}
