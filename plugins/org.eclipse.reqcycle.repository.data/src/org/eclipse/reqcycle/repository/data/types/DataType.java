package org.eclipse.reqcycle.repository.data.types;

import java.util.Collection;


public interface DataType {

	public String getName();

	public Collection<? extends DataTypeAttribute> getAttributes();

}
