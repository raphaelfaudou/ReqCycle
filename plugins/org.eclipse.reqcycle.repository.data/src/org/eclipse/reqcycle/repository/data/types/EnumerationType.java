package org.eclipse.reqcycle.repository.data.types;

import org.eclipse.emf.ecore.EDataType;



public interface EnumerationType extends DataType {

	public void addEnumeratorType(EnumeratorType enumerator);
	
	public EDataType getEDataType();

}
