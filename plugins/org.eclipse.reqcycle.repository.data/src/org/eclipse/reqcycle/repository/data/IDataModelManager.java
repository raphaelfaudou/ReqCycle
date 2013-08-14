package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataTypePackage;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;

import DataModel.Scope;

public interface IDataModelManager {

	public void discardUnsavedChanges();

	public void save();

	public void addDataTypePackage(DataTypePackage dataTypePackage);
	
	public DataTypePackage createDataTypePackage(String name);
	
	public Scope createScope(String name);
	
	public void addRequirementTypes(DataTypePackage dataTypePackage, RequirementType... dataTypes);
	
	public void addEnumerationTypes(DataTypePackage dataTypePackage, EnumerationType... enumerationTypes);
	
	public void addScopes(DataTypePackage dataTypePackage, Scope... scopes);
	
	public void addDataTypes(DataTypePackage dataTypePackage, DataType... types);

	public DataTypePackage getDataTypePackage(String name);
	
	public Collection<DataTypePackage> getAllDataTypePackages();
	
	public RequirementType getDataType(DataTypePackage dataTypePackage, String name);

	public Collection<RequirementType> getDataTypes(DataTypePackage dataTypePackage);

	public Collection<RequirementType> getAllDataTypes();
	
	public EnumerationType getEnumerationType(DataTypePackage dataTypePackage, String name);
	
	public Collection<EnumerationType> getEnumerationTypes(DataTypePackage dataTypePackage);
	
	public Collection<EnumerationType> getAllEnumerationTypes();
	
	public Scope getScope(DataTypePackage dataTypePackage, String name);
	
	public Scope getScope(String DataModelName, String ScopeName);
	
	public Collection<Scope> getScopes(String name);

	public Collection<Scope> getScopes(DataTypePackage dataTypePackage);
	
	public Collection<Scope> getAllScopes();
	
	public EObject createInstance(RequirementType requirementType);
	
	public DataType createRequirementType(String name);	
	
	public DataType createEnumerationType(String name);

	public EnumeratorType createEnumeratorType(String name);

	public RequirementTypeAttribute createAttributeType(String name, EDataType type);
	
	public RequirementTypeAttribute createAttributeType(String name, EnumerationType type);

	public DataTypePackage getDataModel(Scope s);

	public Scope getAnalysisScope();
	
}

