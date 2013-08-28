package org.eclipse.reqcycle.repository.data;

import java.util.Collection;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.reqcycle.repository.data.types.RequirementTypeAttribute;
import org.eclipse.reqcycle.repository.data.types.DataType;
import org.eclipse.reqcycle.repository.data.types.DataModel;
import org.eclipse.reqcycle.repository.data.types.EnumerationType;
import org.eclipse.reqcycle.repository.data.types.EnumeratorType;
import org.eclipse.reqcycle.repository.data.types.RequirementType;

import DataModel.Scope;

public interface IDataModelManager {

	public void discardUnsavedChanges();

	public void save();

	public void addDataModel(DataModel dataModel);
	
	public DataModel createDataModel(String name);
	
	public Scope createScope(String name);
	
	public void addRequirementTypes(DataModel dataModel, RequirementType... dataTypes);
	
	public void addEnumerationTypes(DataModel dataModel, EnumerationType... enumerationTypes);
	
	public void addScopes(DataModel dataModel, Scope... scopes);
	
	public void addDataTypes(DataModel dataModel, DataType... types);

	public DataModel getDataModel(String name);
	
	public Collection<DataModel> getAllDataModels();
	
	public RequirementType getDataType(DataModel dataModel, String name);

	public Collection<RequirementType> getDataTypes(DataModel dataModel);

	public Collection<RequirementType> getAllDataTypes();
	
	public EnumerationType getEnumerationType(DataModel dataModel, String name);
	
	public Collection<EnumerationType> getEnumerationTypes(DataModel dataModel);
	
	public Collection<EnumerationType> getAllEnumerationTypes();
	
	public Scope getScope(DataModel dataModel, String name);
	
	public Scope getScope(String DataModelName, String ScopeName);
	
	public Collection<Scope> getScopes(String name);

	public Collection<Scope> getScopes(DataModel dataModel);
	
	public Collection<Scope> getAllScopes();
	
	public EObject createInstance(RequirementType requirementType);
	
	public DataType createRequirementType(String name);	
	
	public DataType createEnumerationType(String name);

	public EnumeratorType createEnumeratorType(String name);

	public RequirementTypeAttribute createAttributeType(String name, EDataType type);
	
	public RequirementTypeAttribute createAttributeType(String name, EnumerationType type);

	public DataModel getDataModel(Scope s);

	public Scope getAnalysisScope();
	
}

