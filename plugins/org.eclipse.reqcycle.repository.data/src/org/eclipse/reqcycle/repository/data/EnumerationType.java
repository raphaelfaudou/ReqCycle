package org.eclipse.reqcycle.repository.data;

import java.util.Collection;


public interface EnumerationType extends DataType {

	public void addEnumeratorType(EnumeratorType enumerator);

	public Collection<EnumeratorType> getEnumerators();
	
}
