package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

public interface IDataTypeManager {

	public void undoUnsavedChanges();

	public void saveTypes();

	public void addDataTypePackage(DataTypePackage p);
	
	public DataTypePackage createDataTypePackage(String name);
	
	public void addDataType(DataTypePackage p, RequirementType dataType);
	
	public void addDataTypes(DataTypePackage p, Collection<RequirementType> types);
	
	public void addEnumerationType(DataTypePackage p, EnumerationType enumerationType);

	public void addEnumerationTypes(DataTypePackage p, Collection<EnumerationType> types);

	public DataTypePackage getDataTypePackage(String name);
	
	public Collection<DataTypePackage> getAllDataTypePackages();
	
	public RequirementType getDataType(DataTypePackage p, String name);

	public Collection<RequirementType> getDataTypes(DataTypePackage p);

	public Collection<RequirementType> getAllDataTypes();
	
	public EnumerationType getEnumerationType(DataTypePackage p, String name);
	
	public Collection<EnumerationType> getEnumerationTypes(DataTypePackage p);
	
	public Collection<EnumerationType> getAllEnumerationTypes();
	
//	public boolean isAvailable(EPackage ePackage, String name);

	public EObject createInstance(RequirementType dataType);
	
//	public Collection<DataType> getUncategorizedDataTypes();
//	
//	public Collection<EnumerationType> getUncategorizedEnumerationTypes();

	public DataType createDataType(String name);	
	
	public DataType createEnumerationType(String name);

	public EnumeratorType createEnumeratorType(String name);

	public AttributeType createAttributeType(String name, EDataType type);
}
